package com.keltica.thoughtful.data_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

data class Contact(
    val name: String,
) {
    // Variables
    var favorites: MutableLiveData<LiveData<Favorites>>? = null

    //init
    fun init(){
        // favorites = ToDo() initialize data.
    }
    //Functions
}