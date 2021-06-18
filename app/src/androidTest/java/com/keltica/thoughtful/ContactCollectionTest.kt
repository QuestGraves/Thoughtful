package com.keltica.thoughtful

import android.database.Cursor
import android.net.Uri
import android.provider.ContactsContract
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.keltica.thoughtful.model.ContactModel
import org.junit.Assert
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



    @Test
    fun shouldReturnCursor_NotNull() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val contentResolver = appContext.contentResolver
        //init URI
        val uri: Uri = ContactsContract.Contacts.CONTENT_URI
        //Sort arguments
        val sort: String = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME  + " ASC"
        val cursor: Cursor? = appContext.contentResolver.query(uri, null, null, null, sort)
        Assert.assertNotNull( cursor)
    }

}