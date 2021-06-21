package com.keltica.thoughtful.model.repository

import android.content.Context
import com.keltica.thoughtful.model.ContactModel
import com.keltica.thoughtful.model.ContactUtils
import com.keltica.thoughtful.model.FirestoreUtils

class ContactRepository(context: Context?) {

     var mContactCollection: ArrayList<ContactModel>

    init
    {
        mContactCollection = FirestoreUtils.Contact.retrieveAllContactsFromFirestore()
        if (mContactCollection.size <= 0) {
            mContactCollection = ContactUtils.getContacts(context)
        }
    }
}