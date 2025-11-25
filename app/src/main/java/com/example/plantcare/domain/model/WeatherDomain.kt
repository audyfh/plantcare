package com.example.plantcare.domain.model

import com.example.plantcare.data.network.weather.model.Clouds
import com.example.plantcare.data.network.weather.model.Coord
import com.example.plantcare.data.network.weather.model.Main
import com.example.plantcare.data.network.weather.model.Sys
import com.example.plantcare.data.network.weather.model.Weather
import com.example.plantcare.data.network.weather.model.Wind


data class WeatherDomain(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)
