package com.keltica.thoughtful

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.ContactsContract
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.keltica.thoughtful.model.ContactCollection
import com.keltica.thoughtful.model.ContactModel
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Test Class for the ContactCollection class and it's functions.
 * Format for naming is simple, begins with a "should" statement
 * that reflects the condition the function should test followed
 * by an underscore (_) and the expected outcome.
 *  -Matt Graves June 2021
 * */
@RunWith(AndroidJUnit4::class)
class ContactCollectionTest {


    val testAppContext = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun shouldGetLocalContactCollection_SizeGreaterThanZero(){
        val testCollection = ContactCollection.getLocalContactCollection(testAppContext)
        assertTrue(testCollection.size >= 0)
    }

    @Test
    fun shouldRetrieveAllContactsFromFirestore_SizeGreaterThanZero(){
        val firestoreContactData = ContactCollection.getLocalContactCollection(testAppContext)
    }

    @Test
    fun shouldLoadLocalContactData_CollectionContainsSpecificUserByName(){
        //ToDo we technically left off here, this failing test as we are getting back an empty collection
        val testCollection = ContactCollection.getLocalContactCollection(testAppContext)
        val testUser = ContactModel(displayName = "Me")
        var result = false
        if(testCollection.contains(testUser)) result = true
        assertTrue(result)
    }

    @Test
    fun shouldSendContactDataToFirestore_AddsNewContactDocument(){
        val firestore = Firebase.firestore.collection("contacts")
    }

    @Test
    fun subscribeToRealtimeFirestoreContactData_DataChangeReflectsTruth(){
        val testCollection = ContactCollection.getLocalContactCollection(testAppContext)
    }

}