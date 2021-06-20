package com.keltica.thoughtful.view.contact

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.keltica.thoughtful.model.ContactUtils
import com.keltica.thoughtful.model.ContactModel


class ChoseContactViewModel : ViewModel() {

    private val contactCollection: ArrayList<ContactModel> = ContactUtils.subscribeToRealtimeFirestoreContactData()

        private val _nameText = MutableLiveData<String>().apply {


        }
         private val _phoneText = MutableLiveData<String>().apply {
             value = "This is ChoseContact Fragment"
        }
         private val _photoText = MutableLiveData<String>().apply {
            value = "This is ChoseContact Fragment"
        }





    //ToDo: Look into LiveData/ViewModel again, still a bit hazy on observer in this scenario

    val nameText: LiveData<String> = _nameText

}