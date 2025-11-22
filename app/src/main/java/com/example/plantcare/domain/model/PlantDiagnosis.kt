package com.example.plantcare.domain.model

data class PlantDiagnosis(
    val id : Int,
    val plantId: String,
    val diseaseName : String,
    val healthStatus : String,
    val diagnosisResult : String
)
