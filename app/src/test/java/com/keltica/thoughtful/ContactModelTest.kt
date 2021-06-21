package com.keltica.thoughtful

import com.keltica.thoughtful.model.ContactModel
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


/**
 * Test Class for the ContactModel class and it's functions.
 * Format for naming is simple, begins with a "should" statement
 * that reflects the condition the function should test followed
 * by an underscore (_) and the expected outcome.
 *  -Matt Graves June 2021
 * */
@RunWith(JUnit4::class)
class ContactModelTest {

    //Base test case
    @Test
    fun shouldReturnContactModelObject_ContactModel_NotNull(){
        val contactModelObject: ContactModel = ContactModel()
        assertThat(contactModelObject).isNotNull()
    }
    //ID
    @Test
    fun shouldHaveIntPropertyForID_IdInt_NotNull(){
        val contactModelObject: ContactModel = ContactModel()
        assertThat(contactModelObject.ID).isNotNull()
    }
    @Test
    fun shouldSetIdIntProperty_IdInt_One(){
        val contactModelObject: ContactModel = ContactModel()
        contactModelObject.ID = 1
        assertThat(contactModelObject.ID).isEqualTo(1)
    }
    @Test
    fun shouldSetIdIntProperty_IdInt_NotThree(){
        val contactModelObject: ContactModel = ContactModel()
        contactModelObject.ID = 1
        assertThat(contactModelObject.ID).isNotEqualTo(3)
    }
    @Test
    fun shouldHaveDefaultValueForID_IdInt_negativeOne(){
        val contactModelObject: ContactModel = ContactModel()
        assertThat(contactModelObject.ID).isEqualTo(-1)
    }
    //DisplayName
    @Test
    fun shouldHaveDisplayNameString_DisplayNameString_NotNull(){
        val contactModelObject: ContactModel = ContactModel()
        contactModelObject.displayName = "TestName"
        assertThat(contactModelObject.displayName).isNotNull()
    }
    @Test
    fun shouldSetDisplayNameString_DisplayNameString_TestName(){
        val contactModelObject: ContactModel = ContactModel()
        contactModelObject.displayName = "TestName"
        assertThat(contactModelObject.displayName).isEqualTo("TestName")
    }
    @Test
    fun shouldHaveDefaultDisplayName_DisplayName_OpenString(){
        val contactModelObject: ContactModel = ContactModel()
        assertThat(contactModelObject.displayName).isEqualTo("")
    }
    //PhoneNumber Property
    @Test
    fun shouldHavePhoneString_PhoneString_NotNull(){
        val contactModelObject: ContactModel = ContactModel()
        contactModelObject.phoneNumber = ""
        assertThat(contactModelObject.phoneNumber).isNotNull()
    }
    @Test
    fun shouldSetPhoneString_PhoneString_SpecificNumber(){
        val contactModelObject: ContactModel = ContactModel()
        contactModelObject.phoneNumber = "123-234-3456"
        assertThat(contactModelObject.phoneNumber).isEqualTo("123-234-3456")
    }
    @Test
    fun shouldHaveDefaultPhoneString_PhoneString_OpenString(){
        val contactModelObject: ContactModel = ContactModel()
        assertThat(contactModelObject.phoneNumber).isEqualTo("")
    }
    //Photo Property
    @Test
    fun shouldHavePhotoStringProperty_PhotoString_NotNull(){
        val contactModelObject: ContactModel = ContactModel()
        contactModelObject.phoneNumber = "555-555-5555"
        assertThat(contactModelObject.phoneNumber).isNotNull()
    }
    @Test
    fun shouldSetPhotoStringProperty_PhotoString_SpecificString(){
        val contactModelObject: ContactModel = ContactModel()
        contactModelObject.photo = "content://test.uri"
        assertThat(contactModelObject.photo).isEqualTo("content://test.uri")
    }
    @Test
    fun shouldHaveDefaultPhotoString_PhotoString_OpenString(){
        val contactModelObject: ContactModel = ContactModel()
        assertThat(contactModelObject.photo).isEqualTo("")
    }
    //ToString() Function
    @Test
    fun shouldListIDProperty_ToStringFunction_ContainsID(){
        val contactModelObject: ContactModel = ContactModel()
        val toStringResult = contactModelObject.toString()
        assertThat(toStringResult.contains(contactModelObject.ID.toString()))
    }
    @Test
    fun shouldHaveDefaultToStringOverride_ToString_NotNull(){
        val contactModelObject: ContactModel = ContactModel()
        assertThat(contactModelObject.toString()).isNotNull()
    }
    @Test
    fun shouldListDisplayNameProperty_ToStringFunction_ContainsName(){
        val contactModelObject: ContactModel = ContactModel()
        val toStringResult = contactModelObject.toString()
        assertThat(toStringResult.contains(contactModelObject.displayName))
    }

    @Test
    fun shouldListPhoneProperty_ToStringFunction_ContainsPhone(){
        val contactModelObject: ContactModel = ContactModel()
        val toStringResult = contactModelObject.toString()
        assertThat(toStringResult.contains(contactModelObject.phoneNumber))
    }

    @Test
    fun shouldListPhotoProperty_ToStringFunction_ContainsPhoto(){
        val contactModelObject: ContactModel = ContactModel()
        val toStringResult = contactModelObject.toString()
        assertThat(toStringResult.contains(contactModelObject.photo))
    }
}