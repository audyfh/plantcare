package com.example.plantcare.presentation.home

import com.example.plantcare.domain.model.PlantDomain

data class HomeUiState(
    val isLoading: Boolean = false,
    val errorMsg: String = ""
)
