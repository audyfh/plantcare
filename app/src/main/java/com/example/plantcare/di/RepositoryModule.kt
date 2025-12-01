package com.example.plantcare.di


import com.example.plantcare.data.local.repository.MyGardenRepositoryImpl
import com.example.plantcare.data.network.ai.AiRepositoryImpl
import com.example.plantcare.data.network.plantlist.PlantRepositoryImpl
import com.example.plantcare.data.network.cloudinary.CloudinaryRepositoryImpl
import com.example.plantcare.data.network.weather.WeatherRepositoryImpl
import com.example.plantcare.domain.repository.AiRepository
import com.example.plantcare.domain.repository.CloudinaryRepository
import com.example.plantcare.domain.repository.MyGardenRepository
import com.example.plantcare.domain.repository.PlantRepository
import com.example.plantcare.domain.repository.WeatherRepository
import com.example.plantcare.util.location.LocationRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {

    single<PlantRepository>{
        PlantRepositoryImpl(get())
    }

    single<MyGardenRepository>{
        MyGardenRepositoryImpl(get(),get())
    }

    single<CloudinaryRepository> {
        CloudinaryRepositoryImpl(get(), get())
    }

    single<WeatherRepository> {
        WeatherRepositoryImpl(get())
    }

    single{
        LocationRepository(androidContext())
    }

    single<AiRepository>{
        AiRepositoryImpl(get())
    }
}

