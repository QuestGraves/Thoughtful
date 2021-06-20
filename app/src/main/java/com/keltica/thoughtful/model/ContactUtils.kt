package com.keltica.thoughtful.model

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.ContactsContract
import android.util.Log

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.lang.NullPointerException


object ContactUtils {

    private const val TAG = "ContactCollection"

    /**
     * Public function checks the local collection to make sure it has data then
     * will load the contacts from the ContactsProvider if empty.
     * @return ArrayList<ContactModel> a collection of Contacts
     * */
    fun getContacts(context: Context?): ArrayList<ContactModel> {
        return loadContactsFromPersistence(context)
    }

    /*
     * loadContactsFromPersistence will load contacts from either Firestore
     * or the local Contacts ContentProvider if they haven't already
     * */
    private fun loadContactsFromPersistence(context: Context?): ArrayList<ContactModel> {

        if (context == null) {
            throw NullPointerException("$TAG: argument context is null...")
        }

        val contactCollection = FirestoreUtils.Contact.retrieveAllContactsFromFirestore()
        //If we have anything in the collection from Firestore return it
        if (contactCollection.size > 0) return contactCollection

        else{ //Load the contacts from ContactProvider
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
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Log.d(TAG, "Unable to add contact")
                    }
                }
            }
            return contactsFromProviderList
        }
    }
}

