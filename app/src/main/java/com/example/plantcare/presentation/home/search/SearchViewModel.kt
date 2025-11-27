package com.example.plantcare.presentation.home.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantcare.domain.repository.PlantRepository
import com.example.plantcare.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel(
    private val plantRepository: PlantRepository
) : ViewModel() {

    private val _state = MutableStateFlow(SearchUiState())
    val state = _state.asStateFlow()


    fun searchPlants(query: String) {
        _state.value = _state.value.copy(
            isLoading = true
        )
        viewModelScope.launch {
            val data = plantRepository.searchPlants(query)
            if (data is Resource.Success) {
                _state.value = _state.value.copy(
                    plants = data.data ?: emptyList(),
                    isLoading = false
                )
            } else {
                _state.value = _state.value.copy(
                    errorMsg = data.msg ?: "Unknown Error",
                    isLoading = false
                )
            }
        }
    }

    fun updateSearchQuery(query: String) {
        _state.value = _state.value.copy(
            searchQuery = query
        )
    }

}