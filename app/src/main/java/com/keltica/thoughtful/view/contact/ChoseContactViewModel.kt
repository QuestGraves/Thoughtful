package com.keltica.thoughtful.view.contact

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.keltica.thoughtful.model.ContactModel
import com.keltica.thoughtful.model.ContactUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking


class ChoseContactViewModel : ViewModel() {


        private val _nameText = MutableLiveData<String>().apply {
            value = "This is name text"
        }
        private val _phoneText = MutableLiveData<String>().apply {
            value = "This is phone text"
        }
        private val _photoText = MutableLiveData<String>().apply {
            value = "This is photo text"
        }
    //Exposed observable.
    val mNameText: LiveData<String> = _nameText
    val mPhoneText: LiveData<String> = _phoneText
    val mPhotoText: LiveData<String> = _photoText




    //ToDo: Look into LiveData/ViewModel again, still a bit hazy on observer in this scenario

    val nameText: LiveData<String> = _nameText

}