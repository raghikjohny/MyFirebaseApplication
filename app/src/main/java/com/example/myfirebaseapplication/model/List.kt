package com.example.myfirebaseapplication.model

data class List(
    val dt: Int,
    val main: Main,
    val weather:kotlin.collections.List<Weather>,
    val clouds: Wind,
    val visibility: Int,
    val pop: Int
    )
