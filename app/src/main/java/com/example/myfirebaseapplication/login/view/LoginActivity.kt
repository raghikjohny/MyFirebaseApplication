package com.example.myfirebaseapplication.login.view

import android.app.Application
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myfirebaseapplication.R
import com.example.myfirebaseapplication.common.AppConstants
import com.example.myfirebaseapplication.common.ProjectEventListners
import com.example.myfirebaseapplication.databinding.ActivityLoginBinding
import com.example.myfirebaseapplication.home.view.HomeActivity
import com.example.myfirebaseapplication.login.viewmodel.LoginViewModel
import com.example.myfirebaseapplication.registration.view.RegistrationActivity
import com.google.firebase.FirebaseApp

class LoginActivity : AppCompatActivity() {
    lateinit var viewModel: LoginViewModel
    lateinit var binding: ActivityLoginBinding
    var image: String = ""
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
            intent.putExtra(AppConstants.NAME, it.name)
            intent.putExtra(AppConstants.DESCRIPTION, it.biography)
            intent.putExtra("image", image)
            startActivity(intent)
        })
        viewModel.userPicLiveData.observe(this@LoginActivity, Observer {

            image = it.image
        })
        viewModel.error.observe(this@LoginActivity, Observer {
            if (it.isNotEmpty()) {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun doLogin() {
        binding.eventListener = object : ProjectEventListners.LoginEvents {
            override fun login() {
                viewModel.doLogin(
                    binding.etEmail.text.toString(),
                    binding.etPassword.text.toString()
                )
            }
            override fun navToSignUp() {
                val intent = Intent(this@LoginActivity, RegistrationActivity::class.java)
                startActivity(intent)
            }

        }
    }
}