package com.example.plantcare.di

import com.cloudinary.Cloudinary
import com.example.plantcare.BuildConfig
import com.example.plantcare.data.network.plantlist.PlantService
import com.example.plantcare.data.network.weather.WeatherService
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.HashMap

private const val BASE_URL = "https://trefle.io/api/v1/"
val networkModule = module {
    single(named("PLANT_API")) {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>(named("PLANT_API")).create(PlantService::class.java)
    }

    single(named("WEATHER_API")) {
        Retrofit.Builder()
            .baseUrl(WeatherService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single{
        get<Retrofit>(named("WEATHER_API")).create(WeatherService::class.java)
    }

    single{
        val config : MutableMap<String,String> = HashMap()
        config["cloud_name"] = "dn7h4flk4"
        config["api_key"] = "258893955281917"
        config["api_secret"] = BuildConfig.CLOUDINARY_API_KEY
        Cloudinary(config)
    }
}