package com.example.fooddeliveryapp.data.source

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirebaseFactory {

    companion object {
        val firestore by lazy { Firebase.firestore}
    }

}