package com.example.myfirebaseapplication.home.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfirebaseapplication.R
import com.example.myfirebaseapplication.common.AppConstants
import com.example.myfirebaseapplication.databinding.ActivityHomeBinding
import com.example.myfirebaseapplication.home.adapter.WeatherListAdapter
import com.example.myfirebaseapplication.home.viewmodel.HomeViewModel
import com.example.myfirebaseapplication.network.ApiClient
import com.example.myfirebaseapplication.network.ApiInterface
import com.example.myfirebaseapplication.network.NetworkUtil


class HomeActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    lateinit var viewModel: HomeViewModel
    lateinit var binding: ActivityHomeBinding
    var listAdapter: WeatherListAdapter? = null

    var courses = arrayOf(
        "Toronto,CA", "Kolkata,India",
        "London,uk"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        listAdapter = WeatherListAdapter(arrayListOf(), this)

        val extras = intent.extras
        if (extras != null) {
            binding.tvName.text = extras.getString(AppConstants.NAME)
            binding.tvBiography.text = extras.getString(AppConstants.DESCRIPTION)
        }
        binding.coursesspinner.setOnItemSelectedListener(this)

        val ad: ArrayAdapter<*> = ArrayAdapter<Any?>(
            this,
            android.R.layout.simple_spinner_item,
            courses
        )

        ad.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )
        binding.coursesspinner.setAdapter(ad)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = listAdapter
        }
    }

    //calling api call from viewModel
    private fun apiSearch(city: String) {
        if (NetworkUtil.checkConnectionType(this@HomeActivity)) {
            var apiClient = ApiClient.apiClient.create(ApiInterface::class.java)
            viewModel.searchWeather(city, apiClient)
            observeData()
        } else {
            binding.loading = false
            binding.showError = true
        }
    }

    //observing the response,error from viewModel
    private fun observeData() {
        viewModel.api_response.observe(this@HomeActivity, Observer {
            binding.loading = false
            binding.showError = false
            listAdapter?.updateAcroList(it.list)
            binding.isShown = true
        })
        viewModel.error.observe(this@HomeActivity) {
            binding.isShown = false
            binding.showError = it
        }
        viewModel.loading.observe(this@HomeActivity, Observer {
            binding.isShown = false
            binding.loading = it
        })
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        apiSearch(courses[p2])
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}