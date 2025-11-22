package com.example.plantcare.domain.repository

import com.example.plantcare.domain.model.MyPlant
import com.example.plantcare.util.Resource
import kotlinx.coroutines.flow.Flow

interface MyPlantRepository {

    suspend fun addPlant(plant: MyPlant) : Resource<Boolean>
    suspend fun deletePlant(plant: MyPlant) : Resource<Boolean>
    suspend fun updatePlant(plant: MyPlant) : Resource<Boolean>
    suspend fun getAllPlants(): Flow<Resource<List<MyPlant>>>
    suspend fun getPlantById(id: Int): Resource<MyPlant>
}

