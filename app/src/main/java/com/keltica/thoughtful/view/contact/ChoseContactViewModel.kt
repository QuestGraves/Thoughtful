package com.keltica.thoughtful.view.contact

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.keltica.thoughtful.model.ContactModel
import com.keltica.thoughtful.model.ThoughtfulDatabase
import com.keltica.thoughtful.repository.ContactRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ChoseContactViewModel(application: Application) : AndroidViewModel(application) {

        private val readAllContactData: LiveData<List<ContactModel>>
        private val repository: ContactRepository

        init {
            val contactDao = ThoughtfulDatabase.getDatabase(application).contactDao()
            repository = ContactRepository(contactDao)
            readAllContactData = repository.readAllRoomContactData

        }

    fun addContact(contact: ContactModel){
        viewModelScope.launch(Dispatchers.IO){
            repository.addContact(contact)
        }
    }


}