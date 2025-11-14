package com.example.plantcare.data.network.model

data class PlantResponse(
    val data: List<PlantData>,
    val links: Links,
    val meta: Meta
)