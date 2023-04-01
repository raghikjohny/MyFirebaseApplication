package com.example.myfirebaseapplication.common


import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.myfirebaseapplication.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase


class FirebaseRepository {
    var db = FirebaseFirestore.getInstance()

    fun addUser(user: User){
        db.collection("users").document(user.email).set(user)
            .addOnSuccessListener { documentReference ->
                Log.d(
                    TAG,
                    "DocumentSnapshot added with ID: $documentReference"
                )
            }
            .addOnFailureListener { e -> Log.w(TAG, "Error adding document", e) }
    }

}