package com.example.myfirebaseapplication.home.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirebaseapplication.R
import com.example.myfirebaseapplication.common.AppConstants
import com.example.myfirebaseapplication.common.ProjectEventListners
import com.example.myfirebaseapplication.databinding.ItemWeatherRecycleLayoutBinding
import com.example.myfirebaseapplication.details.view.DetailsActivity
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
        val item = weatherList[position]
        holder.bindingView.dataModel = item
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

        init {
            bindingView.eventListener=object :ProjectEventListners.OnRecyclerViewClicked{
                override fun onClick() {
                    val intent =Intent(context,DetailsActivity::class.java)
                    intent.putExtra(AppConstants.TEMPARATURE,bindingView?.dataModel?.main?.temp?.toString())
                    intent.putExtra(AppConstants.PRESSURE,bindingView?.dataModel?.main?.pressure?.toString())
                    intent.putExtra(AppConstants.HUMIDITY,bindingView?.dataModel?.main?.humidity?.toString())
                    intent.putExtra(AppConstants.DESCRIPTION, bindingView?.dataModel!!.weather[0]?.description)
                 context.startActivity(intent)

                }
            }
        }
    }
}