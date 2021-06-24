package com.keltica.thoughtful.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.keltica.thoughtful.model.ContactModel

class ContactRepository: IContactRepository {

    override fun addContact(contactModel: ContactModel) {
        TODO("Not yet implemented")
    }

    override fun getAllContacts(context: Context?) {
        TODO("Not yet implemented")
    }

    override fun getContactByName(displayName: String): ContactModel {
        TODO("Not yet implemented")
    }

    override fun getContactByID(id: Int): ContactModel {
        TODO("Not yet implemented")
    }

    override fun observeContactByName(contactModel: ContactModel): LiveData<ContactModel> {
        TODO("Not yet implemented")
    }

    override fun observeContactByID(contactModel: ContactModel): LiveData<ContactModel> {
        TODO("Not yet implemented")
    }

    override fun removeContactByName(displayName: String) {
        TODO("Not yet implemented")
    }
}