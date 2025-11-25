package com.example.plantcare.data.network.weather

import com.example.plantcare.data.network.weather.model.Clouds
import com.example.plantcare.data.network.weather.model.WeatherResponse
import com.example.plantcare.domain.model.WeatherDomain

fun WeatherResponse.toDomain() : WeatherDomain {
    return WeatherDomain(
        base = base,
        clouds = clouds,
        cod = cod,
        coord = coord,
        dt = dt,
        id = id,
        main = main,
        name = name,
        sys = sys,
        timezone = timezone,
        visibility = visibility,
        weather = weather ?: emptyList(),
        wind = wind
    )
}