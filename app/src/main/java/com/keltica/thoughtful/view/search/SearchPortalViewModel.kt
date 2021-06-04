package com.keltica.thoughtful.view.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchPortalViewModel :ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is search portal Fragment"
    }
    val text: LiveData<String> = _text
}