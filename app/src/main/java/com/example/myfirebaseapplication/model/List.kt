package com.example.myfirebaseapplication.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlin.collections.List

data class List(
    @SerializedName("dt")
    @Expose
    val dt: Int,
    @SerializedName("main")
    @Expose
    val main: Main,
    @SerializedName("weather")
    @Expose
    val weather:kotlin.collections.List<Weather>,
    @SerializedName("clouds")
    @Expose
    val clouds: Clouds,
    @SerializedName("wind")
    @Expose
    var wind: Wind,
    @SerializedName("visibility")
    @Expose
    val visibility: Int,
    @SerializedName("pop")
    @Expose
    val pop: Double,
    @SerializedName("rain")
    @Expose
    var rain: Rain,

    @SerializedName("sys")
@Expose
var sys: Sys,
@SerializedName("dt_txt")
@Expose
var dtTxt: String
    )




