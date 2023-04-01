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
        "Toronto,CA", "India/Kolkata",
        "London,uk"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        listAdapter = WeatherListAdapter(arrayListOf(), this)

        val extras = intent.extras
        if (extras != null) {
            binding.tvName.text = extras.getString("Name")
            binding.tvBiography.text = extras.getString("Description")
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
            binding.showError=false
        //    listAdapter?.updateAcroList(it.list)
            Log.d("apiiiiii", it.toString())
        })
        viewModel.error.observe(this@HomeActivity) {
            binding.showError = it
        }
        viewModel.loading.observe(this@HomeActivity, Observer {
            binding.loading = it
        })
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
       // binding.tvSelectedCity.text = courses[p2]
        apiSearch(courses[p2])
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}