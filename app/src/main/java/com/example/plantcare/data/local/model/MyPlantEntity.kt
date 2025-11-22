
package com.example.plantcare.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_plant")
data class MyPlantEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val name: String,
    val species : String?,
    val category: String,
    val wateringInterval : Int,
    val wateringPerDay : Int,
    val imageUrl : String,
    val lastWateredAt : Long,
    val healthStatus : String,
    val lastDiagnosisAt : Long,
    val diagnosisResult : String
)