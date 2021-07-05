package com.keltica.thoughtful

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import com.keltica.thoughtful.model.ContactModel
import com.keltica.thoughtful.util.ContactUtils
import kotlinx.coroutines.*
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Test Class for the ContactUtils' object's functions.
 * Each test begins with "should" followed by brief description
 * of the behaviour being tested, followed by an underscore (_)
 * then the property identifier and type under test, followed by
 * another underscore, then finally the expected outcome of the test.
 * Ex: fun shouldSetPropertyIdentifierToFour_PropertyIdentifier_EqualsFour()
 *  -Matt Graves June 2021
 * */
@RunWith(AndroidJUnit4::class)
class ContactUtilsAndroidTest {

    private val mTestAppContext: Context? =
        InstrumentationRegistry.getInstrumentation().targetContext
    private lateinit var mTestContactCollection: ArrayList<ContactModel>

    @ExperimentalCoroutinesApi
    @Before
    fun setupCollection() = runBlockingTest {
        mTestContactCollection = ContactUtils.getContacts(mTestAppContext)
    }


    @Test
    fun shouldHaveCollectionData_ArrayList_SizeGreaterZero() {
        val result = mTestContactCollection.size
        assertThat(result).isGreaterThan(0)
    }

}