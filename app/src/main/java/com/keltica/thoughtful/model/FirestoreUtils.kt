package com.keltica.thoughtful.model

import android.util.Log
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.Exception

/**
 * ContactFirestoreUtils is an object that provides utility
 * functions for Contact
 * */
object FirestoreUtils {

    object Contact {
        private val mFirestoreCollection = Firebase.firestore.collection("contacts")
        private const val TAG = "FirestoreUtils.Contact"

        /**
         * Sends a ContactModel to Google Firebase Firestore for persistent storage
         * @param contact the ContactModel to send to the database
         * */
        fun addContactToFirestore(contact: ContactModel) =
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    mFirestoreCollection.add(contact).await()
                    withContext(Dispatchers.Main) {
                        Log.d(TAG, "Updated Firestore with ${contact.displayName}")
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Log.d(TAG, "Unable to add contact")
                    }
                }
            }

        /**
         * Retrieves all contacts from the database into a QuerySnapshot
         * @return an ArrayList<ContactModel> data from online DB
         * uses Coroutine Dispatchers.IO Scope for query
         */
        fun retrieveAllContactsFromFirestore(): ArrayList<ContactModel> {
            val listOfContacts = arrayListOf<ContactModel>()
            var querySnapshot: QuerySnapshot
            //start new Coroutine for background work
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    querySnapshot = mFirestoreCollection.get().await()
                    for (document in querySnapshot.documents) {
                        val contact = document.toObject<ContactModel>()
                        listOfContacts.add(contact!!)
                    }
                } catch (e: Exception) {
                    Log.d(TAG, "Unable to get a Contact from the QuerySnapshot")
                }
            }
            return listOfContacts
        }

    /**
     * Subscribes to Firestore's real-time data updates
     */
    fun subscribeToRealtimeFirestoreContactData() : ArrayList<ContactModel> {
        val contactCollection = arrayListOf<ContactModel>()
       mFirestoreCollection.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
            firebaseFirestoreException?.let {
                Log.d(TAG, "Exception: ${it.message}")
                return@addSnapshotListener
            }
            querySnapshot.let {
                if (it != null) {
                    for (document in it) {
                        val contact = document.toObject<ContactModel>()
                        contactCollection.add(contact)
                    }
                }
            }
        }
        return contactCollection
    }
}
}

