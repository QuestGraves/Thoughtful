package com.keltica.thoughtful

import android.content.Context
import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.keltica.thoughtful.model.ContactModel
import com.keltica.thoughtful.model.ContactUtils
import com.keltica.thoughtful.model.FirestoreUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Exception

/**
 * Test Class for the ContactCollection class and it's functions.
 * Format for naming is simple, begins with a "should" statement
 * that reflects the condition the function should test followed
 * by an underscore (_) and the expected outcome.
 *  -Matt Graves June 2021
 * */
@RunWith(AndroidJUnit4::class)
class ContactUtilsTest {
private val TAG = "ContactCollectionTest"

    val testAppContext: Context? = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun shouldGetLocalContactCollection_SizeGreaterThanZero(){
        val testCollection = ContactUtils.getContacts(testAppContext)
        assertTrue(testCollection.size > 0)
    }

    @Test
    fun shouldLoadLocalContactData_CollectionContainsSpecificUserByName(){
        //ToDo we technically left off here, this failing test as we are getting back an empty collection
        var testCollection: ArrayList<ContactModel> = arrayListOf()
        //start new Coroutine for background work
        CoroutineScope(Dispatchers.Default).launch {
            try {
             testCollection = ContactUtils.getContacts(testAppContext)
                Log.d(TAG, "Fired off Coroutine Scope Default")
            } catch (e: Exception) {
                Log.d(TAG, "Message: $e")
            }
        }
        val testUser = ContactModel(
            ID = -1,
            displayName = "Me",
            phoneNumber = "(815) 847-3722",
            photo = "content://com.android.contacts/contacts/8702/photo")
        var result = false
        if(testCollection.contains(testUser)) result = true
        assertTrue(result)
    }

    @Test
    fun shouldSubscribeToRealtimeFirestoreContactData_SizeGreaterThanZero(){
        val firestoreContactData = FirestoreUtils.Contact.subscribeToRealtimeFirestoreContactData()
        assertTrue(firestoreContactData.size>0)
    }

}