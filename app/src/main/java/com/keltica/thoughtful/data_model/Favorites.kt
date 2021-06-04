package com.keltica.thoughtful.data_model

import java.util.*

data class Favorites(var contactAssociation: Contacts) {

    lateinit var favDict: Dictionary<String, String>
    var color: String? = null
    var song: String? = null
    var recArtist: String? = null
    var food: String? = null


}