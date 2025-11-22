package com.example.plantcare.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.plantcare.data.local.model.PlantDiagnosisEntity

@Dao
interface PlantDiagnosisDao {

    @Insert
    suspend fun insertDiagnosis(plantDiagnosisEntity: PlantDiagnosisEntity)

    @Update
    suspend fun updateDiagnosis(plantDiagnosisEntity: PlantDiagnosisEntity)

    @Query("SELECT * FROM plant_diagnosis  WHERE plantId = :plantId")
    suspend fun getAllPlantDiagnosis(plantId: String): List<PlantDiagnosisEntity>

    @Delete
    suspend fun deleteDiagnosis(plantDiagnosisEntity: PlantDiagnosisEntity)




}