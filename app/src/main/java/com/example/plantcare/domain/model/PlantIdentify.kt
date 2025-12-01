package com.example.plantcare.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class PlantIdentify(
    val name : String,
    val scientific_name : String,
    val family : String,
    val genus : String,
    val description : String
)
