package com.keltica.thoughtful

import android.database.Cursor
import android.net.Uri
import android.provider.ContactsContract
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ContactCollectionTest {

    @Test
    fun cursorNotNull() {
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