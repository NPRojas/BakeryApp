package com.example.bakeryapp.data

import android.util.Log
import com.google.firebase.firestore.firestoreSettings
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

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

    suspend fun getPoints(userID: String): Int {
        return suspendCoroutine { continuation ->
            firestoredb.collection("users").document(userID)
                .get()
                .addOnSuccessListener { result ->
                    val points: Int
                    if (result.exists()) {
                        // Get the points from the user and convert to Int
                        points = result.get("points").toString().toInt()
                    } else {
                        // Create a user and retrieve its points
                        val user = User(userID)
                        addUser(user)
                        points = retrieveRewardPoints(userID).toString().toInt()
                    }
                    continuation.resume(points)
                }
                .addOnFailureListener { exception ->
                    continuation.resumeWithException(exception)
                }
        }
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

    suspend fun updatePoints(userID : String, updatedPoints : Int){
        return suspendCoroutine {
            firestoredb.collection("users").document(userID)
                .update("points", updatedPoints )
                .addOnSuccessListener { Log.d("LEMON", "DocumentSnapshot successfully updated!")}
                .addOnFailureListener { e -> Log.w("LEMON", "Error updating document", e) }
        }
    }
}