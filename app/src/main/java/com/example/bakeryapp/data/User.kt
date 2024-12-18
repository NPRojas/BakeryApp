package com.example.bakeryapp.data

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(
    var id: String,
    var points: Int = 0,
) {

    companion object {

    }
}
