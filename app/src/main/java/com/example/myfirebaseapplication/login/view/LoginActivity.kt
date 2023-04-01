package com.example.myfirebaseapplication.login.view

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myfirebaseapplication.R
import com.example.myfirebaseapplication.common.ProjectEventListners
import com.example.myfirebaseapplication.databinding.ActivityLoginBinding
import com.example.myfirebaseapplication.home.view.HomeActivity
import com.example.myfirebaseapplication.login.viewmodel.LoginViewModel
import com.example.myfirebaseapplication.registration.view.RegistrationActivity
import com.google.firebase.FirebaseApp

class LoginActivity : AppCompatActivity() {
    lateinit var viewModel: LoginViewModel
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        doLogin()
        observeLogin()
    }

    private fun observeLogin() {
       viewModel.userLiveData.observe(this@LoginActivity, Observer {
           val intent = Intent(this@LoginActivity, HomeActivity::class.java)
           intent.putExtra("Name",it.name)
           intent.putExtra("Description",it.biography)
           startActivity(intent)
       })
    }

    private fun doLogin() {
        binding.eventListener = object : ProjectEventListners.LoginEvents {
            override fun login() {
                viewModel.doLogin(binding.etEmail.text.toString(), binding.etPassword.text.toString())
            }

            override fun navToSignUp() {
                val intent = Intent(this@LoginActivity, RegistrationActivity::class.java)
                startActivity(intent)
            }

        }
    }
}