package com.example.myfirebaseapplication.network

import com.example.myfirebaseapplication.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("weather?")
    suspend fun weatherResponse(@Query("q") q: String,@Query("appid") appid:String): Response<ApiResponse>
}