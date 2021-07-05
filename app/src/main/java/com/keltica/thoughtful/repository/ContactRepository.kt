package com.keltica.thoughtful.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.keltica.thoughtful.model.ContactDao
import com.keltica.thoughtful.model.ContactModel
import com.keltica.thoughtful.util.FirestoreUtils

class ContactRepository(private val contactDao: ContactDao){


    val readAllRoomContactData: LiveData<List<ContactModel>> = contactDao.readAllContactData()
    val readAllFirestoreContactData: List<ContactModel> = FirestoreUtils.retrieveAllContactsFromFirestore()

    suspend fun addContact(contactModel: ContactModel) {
        contactDao.addContact(contactModel)
        //also add code for
    }

  fun getAllContacts(context: Context?) {
        TODO("Not yet implemented")
    }

    fun getContactByName(displayName: String): ContactModel {
        TODO("Not yet implemented")
    }

}