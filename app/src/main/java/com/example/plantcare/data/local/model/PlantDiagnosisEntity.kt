package com.example.plantcare.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plant_diagnosis")
data class PlantDiagnosisEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val plantId: String,
    val diseaseName : String,
    val healthStatus : String,
    val diagnosisResult : String
)
