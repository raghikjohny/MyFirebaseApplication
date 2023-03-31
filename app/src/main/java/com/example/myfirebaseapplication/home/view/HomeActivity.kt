package com.example.myfirebaseapplication.home.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.myfirebaseapplication.R
import com.example.myfirebaseapplication.home.viewmodel.HomeViewModel

class HomeActivity : AppCompatActivity() {
    lateinit var viewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
    }
}