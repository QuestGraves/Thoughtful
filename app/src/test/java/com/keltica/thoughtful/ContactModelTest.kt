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

    @Test
    fun shouldReturnContactModelObject_ContactNotNull(){
        val contactModelObject: ContactModel = ContactModel()
        Assert.assertNotNull(contactModelObject)
    }
    @Test
    fun shouldHaveDisplayNameStringProperty_DisplayNameStringPropertyNotNull(){
        val contactModelObject: ContactModel = ContactModel()
        contactModelObject.displayName = "TestName"
        Assert.assertEquals("TestName", contactModelObject.displayName)
    }
    @Test
    fun shouldHavePhoneStringProperty_PhoneStringPropertyValue(){
        val contactModelObject: ContactModel = ContactModel()
        contactModelObject.phoneNumber = "123-234-3456"
        Assert.assertEquals("123-234-3456", contactModelObject.phoneNumber)
    }
    @Test
    fun shouldHavePhotoURIStringProperty_PhotoURIStringPropertyValue(){
        val contactModelObject: ContactModel = ContactModel()
        contactModelObject.photo = "TestPhoto.uri"
        Assert.assertEquals("TestPhoto.uri", contactModelObject.photo)
    }

}