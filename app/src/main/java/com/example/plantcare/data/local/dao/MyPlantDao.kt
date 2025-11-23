package com.example.plantcare.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.plantcare.data.local.model.MyPlantEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MyPlantDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlant(plantEntity: MyPlantEntity)

    @Delete
    suspend fun deletePlant(plantEntity: MyPlantEntity)

    @Query("SELECT * FROM my_plant")
    fun getAllPlants(): Flow<List<MyPlantEntity>>

    @Query("SELECT * FROM my_plant WHERE id = :id")
    suspend fun getPlant(id: Int) : MyPlantEntity

    @Update
    suspend fun updatePlant(plantEntity: MyPlantEntity)


}