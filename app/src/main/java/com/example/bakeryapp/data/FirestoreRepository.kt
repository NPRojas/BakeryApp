package com.example.bakeryapp.data

import android.util.Log
import com.google.firebase.firestore.firestoreSettings
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirestoreRepository {
    private val firestoredb = Firebase.firestore

    init {
        firestoredb.useEmulator("10.0.2.2", 8080)
        firestoredb.firestoreSettings = firestoreSettings {
            isPersistenceEnabled = false
        }
    }

    fun addUser(user: User) {
        firestoredb.collection("users")
            .document(user.id).set(user)
            .addOnSuccessListener {
                Log.d("LEMON", "Document added with ID: ${user.id}")

            }
            .addOnFailureListener { e ->
                Log.w("LEMON", "Error adding document", e)
            }
    }

    fun retriveUser(U) {

    }

    fun retriveRewardPoints(UserID: String) {
        // try to retrive only the points by users
        firestoredb.collection("users").document(UserID)
            .get()
            .addOnSuccessListener { result ->
                val points = result.get("points")
                Log.d("LEMON", "$points")
            }
            .addOnFailureListener { exception ->
                Log.w("LEMON", "Error getting documents.", exception)
            }

    }

    fun updatePoints(UserID : String, updatedPoints : Int){
        val user = firestoredb.collection("users").document(UserID)
            .update("points", updatedPoints )
            .addOnSuccessListener { Log.d("LEMON", "DocumentSnapshot successfully updated!") }
            .addOnFailureListener { e -> Log.w("LEMON", "Error updating document", e) }
    }
}