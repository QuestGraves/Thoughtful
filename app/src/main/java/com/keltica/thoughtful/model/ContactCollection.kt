package com.keltica.thoughtful.model

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.ContactsContract
import android.util.Log
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


object ContactCollection {

    private val TAG = "ContactCollection"

    //Data Collection Reference - Firestore
    private val mContactFirestore = Firebase.firestore.collection("contacts")
    private var mLocalCollection: ArrayList<ContactModel>

    //Use Firestore's Snapshot of Contact data to populate the Collection
    init {
        mLocalCollection = retrieveAllContactsFromFirestore()
    }

    /**
     * Public function checks the local collection to make sure it has data then
     * will load the contacts from the ContactsProvider if empty.
     * @return ArrayList<ContactModel> a collection of Contacts
     * */
    fun getLocalContactCollection(context: Context?): ArrayList<ContactModel> {
        if(mLocalCollection.size <=0) loadContactCollectionFromProvider(context)
        return mLocalCollection
    }

    /**
     * Subscribes to Firestore's real-time data updates
     * with snapshot of Query
     */
    fun subscribeToRealtimeFirestoreContactData() : ArrayList<ContactModel> {
        mContactFirestore.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
            firebaseFirestoreException?.let {
                Log.d(TAG, "Exception: ${it.message}")
                return@addSnapshotListener
            }
            querySnapshot.let {
                if (it != null) {
                    for (document in it) {
                        val contact = document.toObject<ContactModel>()
                        if (mLocalCollection.contains(contact)) mLocalCollection.remove(contact)
                        mLocalCollection.add(contact)
                    }
                }
            }
        }
        return mLocalCollection
    }

    /*
     * getContactCollection(context) will load contacts from the provider and
     * update firestore
     * */
    private fun loadContactCollectionFromProvider(context: Context?): ArrayList<ContactModel> {

        if (context == null) {
            throw NullPointerException("getContext: argument context is null...")
        }
        //If we have a collection in Firestore return it
        // ...maybe do a check against sizing??
        if (mLocalCollection.size >= 0) return mLocalCollection
        val contactsFromProviderList: ArrayList<ContactModel> = arrayListOf()

        //init URI
        val uri: Uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI

        //Sort arguments
        val sort: String = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC"
        //init cursor
        val cursor: Cursor? = context.contentResolver.query(uri, null, null, null, sort)
        Log.i(TAG, "CONTACT_PROVIDER CURSOR num of Contacts: ${cursor!!.count}")

        //Create index numbers for each of the columns we want data from
        val contactIDIndex = cursor.getColumnIndex(ContactsContract.Contacts._ID)
        val contactNameIndex =
            cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
        val contactPhoneIndex =
            cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
        //ToDo Need to process this...maybe into Bitmap? think about where.
        val contactPhotoIndex =
            cursor.getColumnIndex(ContactsContract.CommonDataKinds.Photo.PHOTO_URI)

        //Take the work off of the main thread to process Contacts from the Cursor.
        CoroutineScope(Dispatchers.Default).launch {
            try {
                while (cursor.moveToNext()) {
                    //grab and populate a ContactModel
                    val contact =
                        ContactModel(
                            ID = cursor.getString(contactIDIndex).toInt(),
                            displayName = cursor.getString(contactNameIndex),
                            phoneNumber = if (cursor.getString(contactPhoneIndex) == null) "" else cursor.getString(
                                contactPhoneIndex
                            ),
                            photo = if (cursor.getString(contactPhotoIndex) == null) "" else cursor.getString(
                                contactPhotoIndex
                            )
                        )
                    //Add ContactModel to List
                    contactsFromProviderList.add(contact)
                    //Send ContactModel to Firestore (This will move to ViewModel as the main driver once this class tests out)
                    sendContactDataToFirestore(contact)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.d(TAG, "Unable to add contact")
                }
            }
        }

        return contactsFromProviderList
    }


    /*
     * Sends a ContactModel to Google Firebase Firestore for persistent storage
     * @param contact the ContactModel to send to the database
     * */
    private fun sendContactDataToFirestore(contact: ContactModel) =
        CoroutineScope(Dispatchers.IO).launch {
            try {
                mContactFirestore.add(contact).await()
                withContext(Dispatchers.Main) {
                    Log.d(TAG, "Updated Firestore with ${contact.displayName}")
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
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
        var querySnapshot: QuerySnapshot
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

}

