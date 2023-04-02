package com.example.myfirebaseapplication.registration.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfirebaseapplication.common.FirebaseRepository
import com.example.myfirebaseapplication.common.Response
import com.example.myfirebaseapplication.model.ProfilePic
import com.example.myfirebaseapplication.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class RegistrationViewModel : ViewModel() {
    lateinit var fireBaseRepo: FirebaseRepository
    private lateinit var auth: FirebaseAuth
    var userLiveData = MutableLiveData<User>()
    var error = MutableLiveData<String>()
    var db = FirebaseFirestore.getInstance()


    fun doSingUp(user: User) {
        fireBaseRepo = FirebaseRepository()
        auth = Firebase.auth
        if (user.email.isNotEmpty() && user.password.isNotEmpty() && user.biography.isNotEmpty() &&
            user.name.isNotEmpty()
        ) {
            auth.createUserWithEmailAndPassword(user.email, user.password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        userLiveData.postValue(user)
                        fireBaseRepo.addUser(user)
                    } else {
                        error.postValue("User not added")
                    }
                }
        } else {
            error.postValue("Please enter user details")
        }
    }

    fun addImageToFirestore(userPic: ProfilePic) {
        fireBaseRepo.addImageToFirestore(userPic)
    }

}