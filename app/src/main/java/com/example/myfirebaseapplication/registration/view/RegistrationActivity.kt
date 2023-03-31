package com.example.myfirebaseapplication.registration.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.myfirebaseapplication.R
import com.example.myfirebaseapplication.registration.viewmodel.RegistrationViewModel

class RegistrationActivity : AppCompatActivity() {
    lateinit var viewModel: RegistrationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        viewModel = ViewModelProvider(this).get(RegistrationViewModel::class.java)

    }
}