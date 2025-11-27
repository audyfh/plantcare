package com.example.plantcare.presentation.home.search

import com.example.plantcare.domain.model.PlantDomain

data class SearchUiState(
    val isLoading: Boolean = false,
    val errorMsg: String? = null,
    val plants : List<PlantDomain> = emptyList(),
    val searchQuery: String = ""
)
