package com.example.plantcare.presentation.mygarden

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantcare.domain.model.Schedule
import com.example.plantcare.domain.repository.MyGardenRepository
import com.example.plantcare.presentation.mygarden.state.MyGardenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate

class MyGardenViewModel(
    private val myGardenRepository: MyGardenRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MyGardenUiState())
    val uiState: StateFlow<MyGardenUiState> = _uiState.asStateFlow()

    init {
        getAllPlants()
        getAllSchedule()
    }


    private fun getAllPlants() {
        viewModelScope.launch {
            myGardenRepository.getAllPlants().collect {
                _uiState.value = _uiState.value.copy(
                    myPlants = it
                )
            }
        }
    }

    private fun getAllSchedule() {
        viewModelScope.launch {
            myGardenRepository.getAllSchedule().collect {
                _uiState.value = _uiState.value.copy(
                    scheduleList = it
                )
            }

        }
    }

    fun addSchedule(schedule: Schedule){
        viewModelScope.launch {
           val res = myGardenRepository.addSchedule(schedule)
        }
    }

    fun onDateSelected(date : LocalDate) {
        _uiState.update {
            it.copy(selectedDate = date)
        }
    }

}