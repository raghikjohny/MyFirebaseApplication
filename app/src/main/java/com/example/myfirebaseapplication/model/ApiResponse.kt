package com.example.myfirebaseapplication.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("cod")
    @Expose
    val cod: String,
    @SerializedName("message")
    @Expose
    val message: Int,
    @SerializedName("cnt")
    @Expose
    val cnt: Int,
    @SerializedName("list")
    @Expose
    val list: kotlin.collections.List<List>,
    @SerializedName("city")
    @Expose
    val city: City
)

