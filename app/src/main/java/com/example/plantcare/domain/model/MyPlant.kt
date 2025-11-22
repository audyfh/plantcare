package com.example.plantcare.domain.model

data class MyPlant(
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
