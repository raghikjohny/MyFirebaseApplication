package com.example.myfirebaseapplication.login.viewmodel

import android.content.ContentValues
import android.content.Intent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfirebaseapplication.common.FirebaseRepository
import com.example.myfirebaseapplication.login.view.LoginActivity
import com.example.myfirebaseapplication.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class LoginViewModel : ViewModel() {
    lateinit var fireBaseRepo: FirebaseRepository
    private lateinit var auth: FirebaseAuth
    var userLiveData = MutableLiveData<User>()
    var db = FirebaseFirestore.getInstance()

    fun doLogin(email: String, password: String) {
        fireBaseRepo = FirebaseRepository()
        auth = Firebase.auth
        auth.signInWithEmailAndPassword(email.toString(), password.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    fetchUser(email)
                    Log.d("succes", auth.currentUser.toString())
                } else {
                    //TODO
                }
            }
    }

    fun fetchUser(email: String?) {
        db.collection("users").document(email.toString()).get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    var user = User(
                        document.data?.get("name").toString(),
                        document.data?.get("biography").toString(),"",
                        document.data?.get("email").toString(),
                        document.data?.get("image").toString()
                    )
                    userLiveData.postValue(user)
                    Log.d(ContentValues.TAG, "DocumentSnapshot data: ${document.data}")
                } else {
                    Log.d(ContentValues.TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "get failed with ", exception)
            }
    }

}