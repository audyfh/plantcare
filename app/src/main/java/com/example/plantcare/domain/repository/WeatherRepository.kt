package com.example.plantcare.domain.repository

import com.example.plantcare.domain.model.WeatherDomain
import com.example.plantcare.util.Resource

interface WeatherRepository {

    suspend fun getCurrentWeather(lat: Double, lon: Double): Resource<WeatherDomain>
}