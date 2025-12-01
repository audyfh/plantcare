package com.example.plantcare.data.local.repository

import com.example.plantcare.data.local.dao.MyPlantDao
import com.example.plantcare.data.local.dao.ScheduleDao
import com.example.plantcare.data.local.mapper.toDomain
import com.example.plantcare.data.local.mapper.toEntity
import com.example.plantcare.domain.model.MyPlant
import com.example.plantcare.domain.model.Schedule
import com.example.plantcare.domain.repository.MyGardenRepository
import com.example.plantcare.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MyGardenRepositoryImpl(
    private val myPlantDao: MyPlantDao,
    private val scheduleDao: ScheduleDao
) : MyGardenRepository {
    override suspend fun addPlant(plant: MyPlant): Resource<Boolean> {
        try {
            val plantEntity = plant.toEntity()
            myPlantDao.insertPlant(plantEntity)
            return Resource.Success(true)
        } catch (e: Exception) {
            return Resource.Error(e.message)
        }
    }

    override suspend fun deletePlant(plant: MyPlant): Resource<Boolean> {
        try {
            val plantEntity = plant.toEntity()
            myPlantDao.deletePlant(plantEntity)
            return Resource.Success(true)
        } catch (e: Exception) {
            return Resource.Error(e.message)
        }
    }

    override suspend fun updatePlant(plant: MyPlant): Resource<Boolean> {
        try {
            val plantEntity = plant.toEntity()
            myPlantDao.updatePlant(plantEntity)
            return Resource.Success(true)
        } catch (e: Exception) {
            return Resource.Error(e.message)
        }
    }

    override fun getAllPlants(): Flow<List<MyPlant>> {
       return myPlantDao.getAllPlants().map {
           it.map { entity ->
               entity.toDomain()
           }
       }
    }

    override suspend fun getPlantById(id: Int): Resource<MyPlant> {
        try {
            val data = myPlantDao.getPlant(id)
            val plant = data.toDomain()
            return Resource.Success(plant)
        } catch (e: Exception) {
            return Resource.Error(e.message)
        }
    }

    override fun getAllSchedule(): Flow<List<Schedule>> {
        return scheduleDao.getAllSchedule().map {
            it.map { entity ->
                entity.toDomain()
            }
        }
    }

    override suspend fun addSchedule(schedule: Schedule): Resource<Boolean> {
        try {
            val schedule = schedule.toEntity()
            scheduleDao.insertSchedule(schedule)
            return Resource.Success(true)
        } catch (e: Exception) {
            return Resource.Error(e.message)
        }
    }


}