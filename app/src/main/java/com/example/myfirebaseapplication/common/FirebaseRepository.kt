package com.example.myfirebaseapplication.common


import android.content.ContentValues
import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.myfirebaseapplication.model.ProfilePic
import com.example.myfirebaseapplication.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.util.concurrent.Flow


class FirebaseRepository {
    var db = FirebaseFirestore.getInstance()

    fun addUser(user: User) {
        db.collection("users").document(user.email).set(user)
            .addOnSuccessListener { documentReference ->
                Log.d(
                    TAG,
                    "DocumentSnapshot added with ID: $documentReference"
                )
            }
            .addOnFailureListener { e -> Log.w(TAG, "Error adding document", e) }
    }

    fun addImageToFirestore(userPic: ProfilePic) {
        try {
            db.collection("profile").document(userPic.email).set(userPic)
                .addOnSuccessListener {
                }
                .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error adding document", e) }
        } catch (e: Exception) {
            Log.d("error", e.toString())
        }
    }
}