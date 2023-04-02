package com.example.myfirebaseapplication.details.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.myfirebaseapplication.R
import com.example.myfirebaseapplication.common.AppConstants
import com.example.myfirebaseapplication.common.ProjectEventListners
import com.example.myfirebaseapplication.databinding.ActivityDetailsBinding
import com.example.myfirebaseapplication.databinding.ActivityHomeBinding
import com.example.myfirebaseapplication.details.viewmodel.DetailViewModel
import com.example.myfirebaseapplication.home.viewmodel.HomeViewModel

class DetailsActivity : AppCompatActivity() {
    lateinit var viewModel: DetailViewModel
    lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)
        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        val extras = intent.extras
        if (extras != null) {
            binding.tvTempValue.text = extras.getString(AppConstants.TEMPARATURE)
            binding.tvPressureValue.text = extras.getString(AppConstants.PRESSURE)
            binding.tvHumidityValue.text = extras.getString(AppConstants.HUMIDITY)
            binding.tvWeatherDescriptionValue.text = extras.getString(AppConstants.DESCRIPTION)
        }
    }

}