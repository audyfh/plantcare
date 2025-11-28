package com.example.plantcare.di

import androidx.room.Room
import com.example.plantcare.data.local.PlantDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            PlantDatabase::class.java,
            "MyPlantDB",
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single{
        get<PlantDatabase>().myPlantDao
    }

    single{
        get<PlantDatabase>().plantDiagnosisDao
    }

    single {
        get<PlantDatabase>().scheduleDao
    }
}