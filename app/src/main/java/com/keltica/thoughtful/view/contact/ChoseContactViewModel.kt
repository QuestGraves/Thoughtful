package com.keltica.thoughtful.view.contact

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.keltica.thoughtful.model.ContactCollection
import com.keltica.thoughtful.model.ContactModel


class ChoseContactViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is ChoseContact Fragment"
    }
    private val _contactCollection: MutableLiveData<ArrayList<ContactModel>> = TODO()
    //ToDo: Look into LiveData/ViewModel again, still a bit hazy on observer in this scenario

    val text: LiveData<String> = _text
    val contactCollection: LiveData<ArrayList<ContactModel>> = _contactCollection
}