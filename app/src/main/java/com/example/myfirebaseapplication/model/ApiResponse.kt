package com.example.myfirebaseapplication.model

data class ApiResponse(
    val cod: String,
    val message: Int,
    val cnt: Int,
    val list: kotlin.collections.List<List>,
    val city: City
)

