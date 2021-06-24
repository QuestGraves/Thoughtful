package com.keltica.thoughtful.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.keltica.thoughtful.model.ContactModel

interface IContactRepository {

    fun addContact(contactModel: ContactModel)

    fun getAllContacts(context: Context?)

    fun getContactByName(displayName: String): ContactModel

    fun getContactByID(id: Int): ContactModel

    fun observeContactByName(contactModel: ContactModel): LiveData<ContactModel>

    fun observeContactByID(contactModel: ContactModel): LiveData<ContactModel>

    fun removeContactByName(displayName: String)

}