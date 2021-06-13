package com.keltica.thoughtful.model

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.ContactsContract
import android.util.Log
import androidx.core.database.getStringOrNull


class ContactCollection {

    private val TAG = "ContactCollection"
    private val CONTACTS_LOADER_ID: Int = 1



 fun getContacts(context: Context?): ArrayList<ContactModel> {
     if(context==null){
         println("Friendly Message: Your View is missing things because this context is null, from: $this")
     }
     //grab our ContentResolver object from the Android context passed in, allowing us to decide elsewhere which lifecycle to hold onto.
     val contentResolver = context?.contentResolver
        //create an empty list to hold the contacts
        val list = arrayListOf<ContactModel>()
        //init URI
        val uri: Uri = ContactsContract.Contacts.CONTENT_URI
        //Sort arguments
        val sort: String = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME  + " ASC"
        //init cursor
        val cursor: Cursor? = context!!.contentResolver.query(uri, null, null, null, sort)

        Log.i(TAG, "CONTACT_PROVIDER CURSOR num of Contacts: ${cursor!!.count}" )

//REFACTOR THIS!!! just at a loss on how to get this quite yet without this little hack...

     var contactIDIndex:Int
     var contactNameIndex:Int
     var contactPhoneIndex: Int
     var contactPhotoIndex: Int

        while(cursor.moveToNext()){
            //perform (currently redundant) checks to grab column indices
            contactIDIndex = cursor.getColumnIndex(ContactsContract.Contacts._ID)

            contactNameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)

            contactPhoneIndex =
                cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
            Log.d(TAG, "Let's see what we have for a phone column...$contactPhoneIndex")

            contactPhotoIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Photo.PHOTO_ID)

            //grab and populate a ContactModel
            val contact =
                ContactModel(
                    ID = cursor.getString(contactIDIndex).toInt(),
                    displayName = cursor.getString(contactNameIndex),
                    phoneNumber = "", //if(cursor.getString(contactPhoneIndex)==null) "" else cursor.getString(contactPhoneIndex),
                    photoIDString = cursor.getString(contactPhotoIndex),
                )
            list.add(contact)
        }
     return list
    }
}

