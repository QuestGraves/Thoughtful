package com.keltica.thoughtful

import com.keltica.thoughtful.model.ContactModel
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


/**
 * Test Class for the ContactModel class and it's functions.
 * Each test begins with "should" followed by brief description
 * of the behaviour being tested, followed by an underscore (_)
 * then the property identifier and type under test, followed by
 * another underscore then finally the expected outcome of the test..
 * Ex: fun shouldSetPropertyIdentifierToFour_PropertyIdentifier_EqualsFour()
 *  -Matt Graves June 2021
 * */
@RunWith(JUnit4::class)
class ContactModelUnitTest {

    //use a single ContactModel for the whole bit...
    private lateinit var mTestContact: ContactModel
    
    @Before
    fun setupTests(){
        //init a dum, empty
        mTestContact = ContactModel(-1,"","","")
    }
    //Base test case
    @Test
    fun shouldReturnContactModelObject_ContactModel_NotNull(){
        assertThat(mTestContact).isNotNull()
    }
    //ID
    @Test
    fun shouldHaveIntPropertyForID_IdInt_NotNull(){
        assertThat(mTestContact.id).isNotNull()
    }
    @Test
    fun shouldSetIdIntProperty_IdInt_One(){
        mTestContact.id = 1
        assertThat(mTestContact.id).isEqualTo(1)
    }
    @Test
    fun shouldSetIdIntProperty_IdInt_NotThree(){
        mTestContact.id = 1
        assertThat(mTestContact.id).isNotEqualTo(3)
    }
    @Test
    fun shouldHaveDefaultValueForID_IdInt_negativeOne(){
        assertThat(mTestContact.id).isEqualTo(-1)
    }
    //DisplayName
    @Test
    fun shouldHaveDisplayNameString_DisplayNameString_NotNull(){
        mTestContact.displayName = "TestName"
        assertThat(mTestContact.displayName).isNotNull()
    }
    @Test
    fun shouldSetDisplayNameString_DisplayNameString_TestName(){
        mTestContact.displayName = "TestName"
        assertThat(mTestContact.displayName).isEqualTo("TestName")
    }
    @Test
    fun shouldHaveDefaultDisplayName_DisplayName_OpenString(){
        assertThat(mTestContact.displayName).isEqualTo("")
    }
    //PhoneNumber Property
    @Test
    fun shouldHavePhoneString_PhoneString_NotNull(){
        mTestContact.phoneNumber = ""
        assertThat(mTestContact.phoneNumber).isNotNull()
    }
    @Test
    fun shouldSetPhoneString_PhoneString_SpecificNumber(){
        mTestContact.phoneNumber = "123-234-3456"
        assertThat(mTestContact.phoneNumber).isEqualTo("123-234-3456")
    }
    @Test
    fun shouldHaveDefaultPhoneString_PhoneString_OpenString(){
        assertThat(mTestContact.phoneNumber).isEqualTo("")
    }
    //Photo Property
    @Test
    fun shouldHavePhotoStringProperty_PhotoString_NotNull(){
        mTestContact.phoneNumber = "555-555-5555"
        assertThat(mTestContact.phoneNumber).isNotNull()
    }
    @Test
    fun shouldSetPhotoStringProperty_PhotoString_SpecificString(){
        mTestContact.photo = "content://test.uri"
        assertThat(mTestContact.photo).isEqualTo("content://test.uri")
    }
    @Test
    fun shouldHaveDefaultPhotoString_PhotoString_OpenString(){
        assertThat(mTestContact.photo).isEqualTo("")
    }
    //ToString() Function
    @Test
    fun shouldListIDProperty_ToStringFunction_ContainsID(){
        val toStringResult = mTestContact.toString()
        assertThat(toStringResult.contains(mTestContact.id.toString()))
    }
    @Test
    fun shouldHaveDefaultToStringOverride_ToString_NotNull(){
        assertThat(mTestContact.toString()).isNotNull()
    }
    @Test
    fun shouldListDisplayNameProperty_ToStringFunction_ContainsName(){
        val toStringResult = mTestContact.toString()
        assertThat(toStringResult.contains(mTestContact.displayName))
    }

    @Test
    fun shouldListPhoneProperty_ToStringFunction_ContainsPhone(){
        val toStringResult = mTestContact.toString()
        assertThat(toStringResult.contains(mTestContact.phoneNumber))
    }

    @Test
    fun shouldListPhotoProperty_ToStringFunction_ContainsPhoto(){
        val toStringResult = mTestContact.toString()
        assertThat(toStringResult.contains(mTestContact.photo))
    }
}