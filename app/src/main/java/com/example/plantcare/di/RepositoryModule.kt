package com.example.plantcare.di


import com.example.plantcare.data.network.PlantRepositoryImpl
import com.example.plantcare.domain.repository.PlantRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<PlantRepository>{
        PlantRepositoryImpl(get())
    }
}

