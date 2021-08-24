package com.keltica.thoughtful.repositories

import com.keltica.thoughtful.models.ContactModel
import com.keltica.thoughtful.models.FavoriteModel

class FavoriteCollection(contact: ContactModel) {

    private val favoriteModel = FavoriteModel(contact)


    private var favoriteCollection = mapOf(
        favoriteModel.color to "",
        favoriteModel.song to "",
        favoriteModel.recArtist to "",
        favoriteModel.food to "")

    fun getFavorites(): Map<String, String> {
        return favoriteCollection
    }
}