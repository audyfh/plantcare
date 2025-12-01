package com.example.plantcare.presentation.ai.diagnose

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantcare.domain.model.MyPlant
import com.example.plantcare.domain.repository.AiRepository
import com.example.plantcare.domain.repository.MyGardenRepository
import com.example.plantcare.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DiagnosisViewModel(
    private val myGardenRepository: MyGardenRepository,
    private val aiRepository: AiRepository
) : ViewModel() {

    private val _state = MutableStateFlow(DiagnoseUiState())
    val state = _state.asStateFlow()

    init {
        getAllPlants()
    }


    private fun getAllPlants() {
        viewModelScope.launch {
            myGardenRepository.getAllPlants().collect {
                _state.value = _state.value.copy(
                    myPlantList = it
                )
            }
        }
    }

    fun diagnosePlant(
        context: Context,
        image: Uri
    ) {
        _state.value = _state.value.copy(
            isLoading = true
        )
        viewModelScope.launch {
            val data = aiRepository.diagnosePlant(context, image)
            if (data is Resource.Success) {
                _state.value = _state.value.copy(
                    plantDiagnosis = data.data,
                    isLoading = false
                )
            } else {
                _state.value = _state.value.copy(
                    error = data.msg,
                    isLoading = false
                )
            }
        }
    }

    fun updatePlant(
        myPlant: MyPlant
    ) {
        viewModelScope.launch {
            myGardenRepository.updatePlant(myPlant)
        }
    }

    fun setSelectedPlant(plant: MyPlant) {
        _state.value = _state.value.copy(
            selectedPlant = plant
        )
    }

    fun updateImageUri(uri: Uri) {
        _state.value = _state.value.copy(
            imageUri = uri
        )
    }
}

