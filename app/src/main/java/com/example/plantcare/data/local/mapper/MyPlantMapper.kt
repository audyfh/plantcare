package com.example.plantcare.data.local.mapper

import com.example.plantcare.data.local.model.MyPlantEntity
import com.example.plantcare.domain.model.MyPlant

fun MyPlant.toEntity() : MyPlantEntity {
    return MyPlantEntity(
        id = id,
        name = name,
        species = species,
        category = category,
        wateringInterval = wateringInterval,
        wateringPerDay = wateringPerDay,
        imageUrl = imageUrl,
        lastWateredAt = lastWateredAt,
        healthStatus = healthStatus,
        lastDiagnosisAt = lastDiagnosisAt,
        diagnosisResult = diagnosisResult
    )
}

fun MyPlantEntity.toDomain() : MyPlant {
    return MyPlant(
        id = id,
        name = name,
        species = species ?: "",
        category = category,
        wateringInterval = wateringInterval,
        wateringPerDay = wateringPerDay,
        imageUrl = imageUrl,
        lastWateredAt = lastWateredAt,
        healthStatus = healthStatus,
        lastDiagnosisAt = lastDiagnosisAt,
        diagnosisResult = diagnosisResult
    )
}