package com.example.plantcare.presentation.plantlist.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantcare.domain.repository.PlantRepository
import com.example.plantcare.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PlantListDetailViewModel(
    private val plantRepository: PlantRepository
) : ViewModel() {

    private val _state = MutableStateFlow(PlantListDetailUiState())
    val state = _state.asStateFlow()


    fun getPlantDetail(id: String){
        _state.value = _state.value.copy(
            isLoading = true
        )
        viewModelScope.launch {
            val data = plantRepository.getPlantDetail(id)
            if (data is Resource.Success) {
                _state.value = _state.value.copy(
                    plant = data.data,
                    isLoading = false
                )
            } else {
                _state.value = _state.value.copy(
                    error = data.msg ?: "Something went wrong",
                    isLoading = false
                )
            }
        }
    }
}