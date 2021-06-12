package com.keltica.thoughtful.model

import android.graphics.Bitmap
import android.net.Uri
import java.util.*
import kotlin.collections.ArrayList


data class ContactModel(
    val ID: Int,
    val displayName: String,
    val phoneNumber: String,
    val photoUri: Uri,

) {
    // Variables
    var favorites: ArrayList<FavoriteModel>? = null

 override fun toString(): String {
     return "DisplayName = $displayName's ID = $ID PhoneNumber = $phoneNumber PhotoUri = $photoUri "
 }
}