package com.example.plantcare.presentation.home

import com.example.plantcare.domain.model.PlantDomain
import com.example.plantcare.domain.model.WeatherDomain

data class HomeUiState(
    val isLoading: Boolean = false,
    val errorMsg: String = "",
    val wateringProgress: Float = 0f,
    val totalNeedWatering : Int = 0,
    val weather : WeatherDomain? = null
)
