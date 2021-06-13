package com.keltica.thoughtful.model

import kotlin.collections.ArrayList


data class ContactModel(
    val ID: Int,
    val displayName: String?,
    val phoneNumber: String?,
    val photoIDString: String?,

    ) {
    // Variables
    var favorites: ArrayList<FavoriteModel>? = null

 override fun toString(): String {
     return "DisplayName = $displayName's ID = $ID PhoneNumber = $phoneNumber PhotoUri = $photoIDString "
 }
}