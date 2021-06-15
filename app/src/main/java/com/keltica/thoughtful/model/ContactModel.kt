package com.keltica.thoughtful.model

import java.sql.Blob
import kotlin.collections.ArrayList


data class ContactModel(
    val ID: Int = -1,
    val displayName: String = "",
    val phoneNumber: String = "",
    val photo: String = "",

    ) {
    // Variables
    //var favorites: ArrayList<FavoriteModel>? = null

 override fun toString(): String {
     return "DisplayName = $displayName's ID = $ID PhoneNumber = $phoneNumber PhotoUri = $photo "
 }
}