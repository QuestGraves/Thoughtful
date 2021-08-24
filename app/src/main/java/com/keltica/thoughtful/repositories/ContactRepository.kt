package com.keltica.thoughtful.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import com.keltica.thoughtful.db.ContactDao
import com.keltica.thoughtful.models.ContactModel
import com.keltica.thoughtful.utils.FirestoreUtils

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