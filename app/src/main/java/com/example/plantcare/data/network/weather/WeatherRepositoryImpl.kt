package com.example.plantcare.data.network.weather

import android.util.Log
import com.example.plantcare.domain.model.WeatherDomain
import com.example.plantcare.domain.repository.WeatherRepository
import com.example.plantcare.util.Resource

class WeatherRepositoryImpl(
    private val weatherService: WeatherService
) : WeatherRepository {

    override suspend fun getCurrentWeather(
        lat: Double,
        lon: Double
    ): Resource<WeatherDomain> {
        try {
            val data = weatherService.getCurrentWeather(lat, lon)
            if (data.isSuccessful) {
                data.body()?.let {
                    return Resource.Success(it.toDomain())
                }
            } else {
                return Resource.Error(data.message())

            }
        } catch (e: Exception) {
            return Resource.Error(e.message)
        }
        return Resource.Error("Something went wrong")
    }
}