package com.example.plantcare.di

import com.cloudinary.Cloudinary
import com.example.plantcare.BuildConfig
import com.example.plantcare.data.network.PlantService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.HashMap

private const val BASE_URL = "https://trefle.io/api/v1/"
val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(PlantService::class.java)
    }

    single{
        val config : MutableMap<String,String> = HashMap()
        config["cloud_name"] = "dn7h4flk4"
        config["api_key"] = "258893955281917"
        config["api_secret"] = BuildConfig.CLOUDINARY_API_KEY
        Cloudinary(config)
    }
}