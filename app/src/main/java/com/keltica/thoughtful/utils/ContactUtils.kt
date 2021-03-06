package com.keltica.thoughtful.utils

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.ContactsContract
import android.util.Log
import com.keltica.thoughtful.models.ContactModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.lang.NullPointerException

/**
 * Utility object to handle fetching and updating the device ContactProvider
 * */
object ContactUtils {

    private const val TAG = "ContactUtils"

    /**
     * Will try to load the contacts from the ContactsProvider using an
     * asynchronous call to the Content Provider
     * @return ArrayList<ContactModel> a collection of Contacts
     * */
      suspend fun getContacts(context: Context?): ArrayList<ContactModel> {
        return loadContactsFromPersistence(context)
    }


    /*
     * loadContactsFromPersistence will load contacts from either Firestore
     * or the local Contacts ContentProvider if they haven't already
     * */
    private suspend fun loadContactsFromPersistence(context: Context?): ArrayList<ContactModel> {

        if (context == null) {
            throw NullPointerException("$TAG: argument context is null...")
        }
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
                                id = cursor.getString(contactIDIndex).toInt(),
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
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Log.d(TAG, "Unable to add contact")
                    }
                }
                cursor.close()
            }
            return contactsFromProviderList
        }
    }
