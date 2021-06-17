package com.keltica.thoughtful.model

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.ContactsContract
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.lang.NullPointerException


object ContactCollection{

    private val TAG = "ContactCollection"
    //Data Collection Reference - Firestore
    private val mContactFirestore = Firebase.firestore.collection("contacts")
    private var mLocalCollection: ArrayList<ContactModel>

    init {
        mLocalCollection = retrieveAllContactsFromFirestore()
    }
    /**
     * @return ArrayList<ContactModel> a collection of Contacts
     * */
    fun getContactCollection(): ArrayList<ContactModel> {
        return mLocalCollection
    }
  fun getContactsFromProvider(context: Context?): ArrayList<ContactModel> {
     // ToDo 1) Refactor photo impl, load the photos into a hashtable for lookup in the adapter? Review some ideas before using a library but many exist for this...
     // ToDo 2) Selection screen to import some or all.
     if(context==null){
         throw NullPointerException("getContext: argument context is null...")
     }
     //grab our ContentResolver object from the Android context passed in, allowing us to decide elsewhere which lifecycle to hold onto.
     val contentResolver = context.contentResolver
        //init URI
        val uri: Uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
     //make temp list to hold contacts
      val contactList: ArrayList<ContactModel> = arrayListOf()
        //Sort arguments
        val sort: String = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME  + " ASC"
        //init cursor
        val cursor: Cursor? = context.contentResolver.query(uri, null, null, null, sort)
        Log.i(TAG, "CONTACT_PROVIDER CURSOR num of Contacts: ${cursor!!.count}" )

    val contactIDIndex = cursor.getColumnIndex(ContactsContract.Contacts._ID)
    val contactNameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
    val contactPhoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
    val contactPhotoIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Photo.PHOTO_URI)
     //Take the work off of the main thread to process Contacts from the Cursor.
     CoroutineScope(Dispatchers.Default).launch{
         try {
             while(cursor.moveToNext()){
                 //grab and populate a ContactModel
                 val contact =
                     ContactModel(
                         ID = cursor.getString(contactIDIndex).toInt(),
                         displayName = cursor.getString(contactNameIndex),
                         phoneNumber = if(cursor.getString(contactPhoneIndex)==null) "" else cursor.getString(contactPhoneIndex),
                         photo= if(cursor.getString(contactPhotoIndex) == null) "" else cursor.getString(contactPhotoIndex)
                     )
                 //Add ContactModel to List
                 contactList.add(contact)
                 //Send ContactModel to Firestore (This will move to ViewModel as the main driver once this class tests out)
                 sendContactDataToFirestore(contact)
             }
         }catch(e: Exception){
             withContext(Dispatchers.Main){
                 Log.d(TAG, "Unable to add contact")
             }
         }
     }
     return contactList
    }

    /**
     * Sends a ContactModel to Google Firebase Firestore for persistent storage
     * @param contact the ContactModel to send to the database
     * */
    private fun sendContactDataToFirestore(contact: ContactModel) = CoroutineScope(Dispatchers.IO).launch{
        try {
            mContactFirestore.add(contact).await()
            withContext(Dispatchers.Main){
                Log.d(TAG, "Updated Firestore with ${contact.displayName}")
            }
        }catch(e: Exception){
            withContext(Dispatchers.Main){
                Log.d(TAG, "Unable to add contact")
            }
        }
    }

    /*
     * Retrieves all contacts from the database into a QuerySnapshot
     * @return an ArrayList<ContactModel> data from online DB
     * uses Coroutine Dispatchers.IO Scope for query
     */
    private fun retrieveAllContactsFromFirestore(): ArrayList<ContactModel> {
        val listOfContacts = arrayListOf<ContactModel>()
        var querySnapshot : QuerySnapshot
        //start new Coroutine for background work
        CoroutineScope(Dispatchers.IO).launch {
            try {
                 querySnapshot = mContactFirestore.get().await()
                for (document in querySnapshot.documents) {
                    val contact = document.toObject<ContactModel>()
                    listOfContacts.add(contact!!)
                }
            } catch (e: Exception) {
                Log.d(TAG, "Unable to get a Contact from the QuerySnapshot")
            }
        }
        return listOfContacts
    }

    //This idea is to call this from ViewModel, then observe the collection for changes with LiveData
     fun subscribeToRealtimeFirestoreContactData(){
        mLocalCollection = arrayListOf<ContactModel>()
        val mutableLiveData : MutableLiveData<ContactModel> = MutableLiveData()

        mContactFirestore.addSnapshotListener{ querySnapshot, firebaseFirestoreException ->
            firebaseFirestoreException?.let {
                Log.d(TAG, "Exception: ${it.message}")
                return@addSnapshotListener
            }
            querySnapshot.let{
                if (it != null) {
                    for(document in it) {
                        val contact = document.toObject<ContactModel>()
                        if(mLocalCollection.contains(contact)) mLocalCollection.remove(contact)
                        mLocalCollection.add(contact)
                    }
                }
            }
            mLocalCollection.forEach{
                mutableLiveData.value = it
            }
        }
   
    }
}

