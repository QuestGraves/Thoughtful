package com.keltica.thoughtful.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.keltica.thoughtful.models.ContactModel

/**
 * Data access object for the contact information.
 * Uses LiveData for easy observation in the view.
 * */
@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addContact(contact: ContactModel)

    @Query("SELECT * FROM contacts ORDER BY id ASC")
    fun readAllContactData(): LiveData<List<ContactModel>>

    @Query("SELECT id, display_name, phone_number, photo FROM contacts ORDER BY display_name ASC")
    fun getContactByName(): LiveData<ContactModel>
}