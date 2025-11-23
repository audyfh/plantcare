package com.example.plantcare.di


import com.example.plantcare.data.local.repository.MyPlantRepositoryImpl
import com.example.plantcare.data.network.PlantRepositoryImpl
import com.example.plantcare.data.network.cloudinary.CloudinaryRepositoryImpl
import com.example.plantcare.domain.repository.CloudinaryRepository
import com.example.plantcare.domain.repository.MyPlantRepository
import com.example.plantcare.domain.repository.PlantRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<PlantRepository>{
        PlantRepositoryImpl(get())
    }

    single<MyPlantRepository>{
        MyPlantRepositoryImpl(get())
    }

    single<CloudinaryRepository> {
        CloudinaryRepositoryImpl(get(), get())
    }
}

