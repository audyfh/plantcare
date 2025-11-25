package com.example.plantcare.data.network.weather

import com.example.plantcare.BuildConfig
import com.example.plantcare.data.network.weather.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface WeatherService {

    companion object {
        const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    }

    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String = "metric",
        @Query("appid") apiKey: String = BuildConfig.OPENWEATHER_API_KEY
    ) : Response<WeatherResponse>
}
