package com.keltica.thoughtful.model

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore

import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.lang.NullPointerException


class ContactCollection{

    private val TAG = "ContactCollection"



 fun getContacts(context: Context?): ArrayList<ContactModel> {
     // ToDo 1) Refactor photo impl, load the photos into a hashtable for lookup in the adapter?

     if(context==null){
         throw NullPointerException("getContext: argument context is null...")
     }
     //grab our ContentResolver object from the Android context passed in, allowing us to decide elsewhere which lifecycle to hold onto.
     val contentResolver = context.contentResolver
        //create an empty list to hold the contacts
        val list = arrayListOf<ContactModel>()
        //init URI
        val uri: Uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        //Sort arguments
        val sort: String = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME  + " ASC"
        //init cursor
        val cursor: Cursor? = context.contentResolver.query(uri, null, null, null, sort)
        Log.i(TAG, "CONTACT_PROVIDER CURSOR num of Contacts: ${cursor!!.count}" )
//Look into implementing the CursorLoader etc per documentation as it's Asynchronous, may be able to resolve current issue.
    val contactIDIndex = cursor.getColumnIndex(ContactsContract.Contacts._ID)
    val contactNameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
    val contactPhoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
    val contactPhotoIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Photo.PHOTO_URI)

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
                 list.add(contact)
                 updateFirestoreContactData(contact)
             }
             withContext(Dispatchers.Main){
                 Log.d(TAG, "Added contact to Firestore on $this")
             }
         }catch(e: Exception){
             withContext(Dispatchers.Main){
                 Log.d(TAG, "Unable to add contact")
             }
         }
     }

     return list
    }
    //FIRESTORE - Remote Persistence
    //Data Collection Reference - Firestore
    private val contactFirestore = Firebase.firestore.collection("contacts")

    private fun updateFirestoreContactData(contact: ContactModel) = CoroutineScope(Dispatchers.IO).launch{
        try {
            contactFirestore.add(contact)
            withContext(Dispatchers.Main){
                Log.d(TAG, "Added $contact to Firestore")
            }
        }catch(e: Exception){
            withContext(Dispatchers.Main){
                Log.d(TAG, "Unable to add contact")
            }
        }
    }
}

