package com.keltica.thoughtful

import com.keltica.thoughtful.model.ContactModel
import org.junit.Assert
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
        Assert.assertNotNull(contactModelObject)
    }
    //ID
    @Test
    fun shouldHaveIntPropertyForID_IdInt_NotNull(){
        val contactModelObject: ContactModel = ContactModel()
        Assert.assertNotNull(contactModelObject.ID)
    }
    @Test
    fun shouldSetIdIntProperty_IdInt_One(){
        val contactModelObject: ContactModel = ContactModel()
        contactModelObject.ID = 1
        Assert.assertEquals(1, contactModelObject.ID)
    }
    @Test
    fun shouldHaveDefaultValueForID_IdInt_negativeOne(){
        val contactModelObject: ContactModel = ContactModel()
        Assert.assertEquals(-1, contactModelObject.ID)
    }
    //DisplayName
    @Test
    fun shouldHaveDisplayNameString_DisplayNameString_NotNull(){
        val contactModelObject: ContactModel = ContactModel()
        contactModelObject.displayName = "TestName"
        Assert.assertNotNull( contactModelObject.displayName)
    }
    @Test
    fun shouldSetDisplayNameString_DisplayNameString_TestName(){
        val contactModelObject: ContactModel = ContactModel()
        contactModelObject.displayName = "TestName"
        Assert.assertEquals("TestName", contactModelObject.displayName)
    }
    @Test
    fun shouldHaveDefaultDisplayName_DisplayName_OpenString(){
        val contactModelObject: ContactModel = ContactModel()
        Assert.assertEquals("", contactModelObject.displayName)
    }
    //PhoneNumber Property
    @Test
    fun shouldHavePhoneString_PhoneString_NotNull(){
        val contactModelObject: ContactModel = ContactModel()
        contactModelObject.phoneNumber = ""
        Assert.assertNotNull( contactModelObject.phoneNumber)
    }
    @Test
    fun shouldSetPhoneString_PhoneString_SpecificNumber(){
        val contactModelObject: ContactModel = ContactModel()
        contactModelObject.phoneNumber = "123-234-3456"
        Assert.assertEquals("123-234-3456", contactModelObject.phoneNumber)
    }
    @Test
    fun shouldHaveDefaultPhoneString_PhoneString_OpenString(){
        val contactModelObject: ContactModel = ContactModel()
        Assert.assertEquals("", contactModelObject.phoneNumber)
    }
    //Photo Property
    @Test
    fun shouldHavePhotoStringProperty_PhotoString_NotNull(){
        val contactModelObject: ContactModel = ContactModel()
        contactModelObject.phoneNumber = ""
        Assert.assertNotNull( contactModelObject.phoneNumber)
    }
    @Test
    fun shouldSetPhotoStringProperty_PhotoString_SpecificString(){
        val contactModelObject: ContactModel = ContactModel()
        contactModelObject.photo = "content://test.uri"
        Assert.assertEquals("content://test.uri", contactModelObject.photo)
    }
    @Test
    fun shouldHaveDefaultPhotoString_PhotoString_OpenString(){
        val contactModelObject: ContactModel = ContactModel()
        Assert.assertEquals("", contactModelObject.photo)
    }
    //ToString() Function
    @Test
    fun shouldHaveDefaultToStringOverride_ToString_NotNull(){
        val contactModelObject: ContactModel = ContactModel()
        Assert.assertNotNull( contactModelObject.toString())
    }
    @Test
    fun shouldListDisplayNameProperty_ToStringFunction_DisplayName(){
        val contactModelObject: ContactModel = ContactModel()
        val toStringResult = contactModelObject.toString()
        Assert.assertTrue(toStringResult.contains(contactModelObject.displayName))
    }

}