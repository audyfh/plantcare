package com.example.plantcare.presentation.mygarden.state

import com.example.plantcare.domain.model.MyPlant

data class MyGardenUiState(
    val myPlants: List<MyPlant> = emptyList(),
    val isLoading: Boolean = true,
    val errorMsg : String? = null
)