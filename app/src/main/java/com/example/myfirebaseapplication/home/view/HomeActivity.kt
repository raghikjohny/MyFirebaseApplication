package com.example.myfirebaseapplication.home.view

import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.myfirebaseapplication.R
import com.example.myfirebaseapplication.databinding.ActivityHomeBinding
import com.example.myfirebaseapplication.home.viewmodel.HomeViewModel

class HomeActivity : AppCompatActivity() {
    lateinit var viewModel: HomeViewModel
    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val extras = intent.extras
        if (extras != null) {
           binding.tvName.text= extras.getString("Name")
           binding.tvBiography.text= extras.getString("Description")
        }
    }
}