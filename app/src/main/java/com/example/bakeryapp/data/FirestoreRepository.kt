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

    fun getPoints(userID: String): Int? {
        var points: Int? = null
        firestoredb.collection("users").document(userID)
            .get()
            .addOnSuccessListener { result ->
                if (result.exists()) {
                    // get the points from the user
                    // convert points from Any to Int
                    points = result.get("points").toString().toInt()
                } else {
                    // create a user and retrieve its points
                    val user = User(userID)
                    addUser(user)
                     points = retrieveRewardPoints(userID).toString().toInt()
                }
            }
        return points
    }

    private fun retrieveRewardPoints(userID: String) : Int? {
        // try to retrieve only the points by users
        var points: Int? = null
        firestoredb.collection("users").document(userID)
            .get()
            .addOnSuccessListener { result ->
                points = result.get("points").toString().toInt()
                Log.d("LEMON", "$points")
            }
            .addOnFailureListener { exception ->
                Log.w("LEMON", "Error getting documents.", exception)
            }
        return points

    }

    fun updatePoints(UserID : String, updatedPoints : Int){
        val user = firestoredb.collection("users").document(UserID)
            .update("points", updatedPoints )
            .addOnSuccessListener { Log.d("LEMON", "DocumentSnapshot successfully updated!") }
            .addOnFailureListener { e -> Log.w("LEMON", "Error updating document", e) }
    }
}