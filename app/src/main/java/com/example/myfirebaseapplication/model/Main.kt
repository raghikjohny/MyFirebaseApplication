package com.example.myfirebaseapplication.model

data class Main (
    val temp: Double,
    val feelsLike: Int,
    val tempMin: Double,
    val tempMax: Double,
    val pressure: Int,
    val seaLevel: Int,
    val grndLevel: Int,
    val humidity: Int,
    val tempKf: Int
)