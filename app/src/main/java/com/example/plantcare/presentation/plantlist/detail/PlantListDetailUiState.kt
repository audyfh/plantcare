package com.example.plantcare.presentation.plantlist.detail

import com.example.plantcare.domain.model.PlantDetailDomain

data class PlantListDetailUiState(
    val isLoading : Boolean = false,
    val plant : PlantDetailDomain? = null,
    val error : String? = null
)
