package com.example.plantcare.domain.repository

import com.example.plantcare.domain.model.MyPlant
import com.example.plantcare.domain.model.Schedule
import com.example.plantcare.util.Resource
import kotlinx.coroutines.flow.Flow

interface MyGardenRepository {

    suspend fun addPlant(plant: MyPlant) : Resource<Boolean>
    suspend fun deletePlant(plant: MyPlant) : Resource<Boolean>
    suspend fun updatePlant(plant: MyPlant) : Resource<Boolean>
    fun getAllPlants(): Flow<List<MyPlant>>
    suspend fun getPlantById(id: Int): Resource<MyPlant>
    fun getAllSchedule() : Flow<List<Schedule>>
    suspend fun addSchedule(schedule: Schedule) : Resource<Boolean>

}

