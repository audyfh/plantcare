package com.example.plantcare.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class PlantDiagnosis(
    val health_status : String,
    val diagnosis_result : String
)
