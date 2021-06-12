package com.keltica.thoughtful.model

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.ContactsContract
import java.io.InputStream
import java.lang.NullPointerException


class ContactCollection {

    private val CONTACTS_LOADER_ID: Int = 1



 fun getContacts(context: Context?): ArrayList<ContactModel> {
     if(context==null){
         println("Friendly Message: Your View is missing things because this context is null, from: $this")
     }
        //create an empty list to hold the contacts
        val list = arrayListOf<ContactModel>()

        //grab our ContentResolver object from the Android context passed in, allowing us to decide elsewhere which lifecycle to hold onto.
        val contentResolver = context?.contentResolver

        // We'll also need a cursor object for random read-write the database
        val cursor =
            contentResolver?.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null)
        if (cursor?.count!! >= 0)
            //Here we go cycling through our cursor...
            while (cursor.moveToNext()) {
                val id = cursor.getColumnIndex(ContactsContract.Contacts._ID)
                if ((cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    val cursorInfo: Cursor? = contentResolver.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                        arrayOf(id.toString()),
                        null
                    )
                    val inputStream: InputStream? =
                        ContactsContract.Contacts.openContactPhotoInputStream(
                            context.contentResolver,
                            ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, id.toLong())
                        )
                    val person: Uri = ContentUris.withAppendedId(
                        ContactsContract.Contacts.CONTENT_URI,
                        id.toLong()
                    )
                    val pURI: Uri = Uri.withAppendedPath(
                        person,
                        ContactsContract.Contacts.Photo.CONTENT_DIRECTORY
                    )
                    var photo: Bitmap? = null
                    if (inputStream != null) {
                        photo = BitmapFactory.decodeStream(inputStream)
                    }
                    while (cursorInfo!!.moveToNext()) {

                        val info =
                            ContactModel(
                                ID = id,
                                displayName = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME).toString(),
                                phoneNumber = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER).toString(),
                                photoUri = pURI
                            )
                        list.add(info)
                    }
                    cursorInfo.close()
                }
            }
        return list
    }
}
