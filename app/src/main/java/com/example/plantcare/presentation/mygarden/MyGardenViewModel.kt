package com.example.plantcare.presentation.mygarden

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantcare.domain.model.MyPlant
import com.example.plantcare.domain.repository.MyPlantRepository
import com.example.plantcare.presentation.mygarden.state.MyGardenUiState
import com.example.plantcare.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MyGardenViewModel(
    private val myPlantRepository: MyPlantRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MyGardenUiState())
    val uiState: StateFlow<MyGardenUiState> = _uiState.asStateFlow()

    init {
        getAllPlants()
    }


    fun addPlant(myPlant: MyPlant){
        viewModelScope.launch {
            myPlantRepository.addPlant(myPlant)
        }
    }

    private fun getAllPlants(){
        viewModelScope.launch {
            myPlantRepository.getAllPlants().collect {
                when(it){
                    is Resource.Success -> {
                        _uiState.value = _uiState.value.copy(
                            myPlants = it.data!!,
                            isLoading = false,
                        )
                    }
                    is Resource.Error -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            errorMsg = it.msg
                            )
                    }
                    else -> {}
                }
            }
        }
    }

}