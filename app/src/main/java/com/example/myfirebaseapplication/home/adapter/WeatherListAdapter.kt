package com.example.myfirebaseapplication.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirebaseapplication.R
import com.example.myfirebaseapplication.databinding.ItemWeatherRecycleLayoutBinding
import com.example.myfirebaseapplication.model.List

class WeatherListAdapter(var weatherList: java.util.ArrayList<List>, val context: Context) :
    RecyclerView.Adapter<WeatherListAdapter.WeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view: ItemWeatherRecycleLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater
                .from(parent.context), R.layout.item_weather_recycle_layout, parent, false
        )
        return WeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val item = weatherList[position].main
        holder.bindingView.dataModel = item.temp.toString()
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    fun updateAcroList(weatherResult: kotlin.collections.List<List>) {
        clearList()
       weatherList.addAll(weatherResult)
        notifyDataSetChanged()
    }

    fun clearList() {
        weatherList.clear()
    }

    inner class WeatherViewHolder(val bindingView: ItemWeatherRecycleLayoutBinding) :
        RecyclerView.ViewHolder(bindingView.root) {
    }

}