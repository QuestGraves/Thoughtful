package com.keltica.thoughtful.view.contact

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChoseContactViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is ChoseContact Fragment"
    }
    val text: LiveData<String> = _text
}