package com.keltica.thoughtful.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents the base model for our contact information and
 * used for the Room database table for contacts.
 * */
@Entity(tableName = "contacts")
data class ContactModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int ,
    @ColumnInfo(name = "display_name")
    var displayName: String ,
    @ColumnInfo(name = "phone_number")
    var phoneNumber: String,
    @ColumnInfo(name = "photo")
    var photo: String ,

    ) {
    // Variables
    //var favorites: ArrayList<FavoriteModel>? = null

 override fun toString(): String {
     return "DisplayName = $displayName's ID = $id PhoneNumber = $phoneNumber PhotoUri = $photo "
 }
}