package com.example.myfirebaseapplication.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    //api.openweathermap.org/data/2.5/forecast?q={city name}&appid={API key}

    val apiClient: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}