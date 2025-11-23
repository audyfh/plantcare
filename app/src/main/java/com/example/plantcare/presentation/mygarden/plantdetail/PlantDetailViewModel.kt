package com.example.plantcare.presentation.mygarden.plantdetail

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantcare.domain.model.MyPlant
import com.example.plantcare.domain.repository.MyPlantRepository
import com.example.plantcare.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PlantDetailViewModel(
    private val myPlantRepository: MyPlantRepository
) : ViewModel() {

    private val _plantDetail = MutableStateFlow<MyPlant?>(null)
    val plantDetail = _plantDetail.asStateFlow()


    fun getPlantDetail(id: Int) {
        viewModelScope.launch {
            val data = myPlantRepository.getPlantById(id)
            when (data) {
                is Resource.Error<*> -> {}
                Resource.Idle -> {}
                is Resource.Loading<*> -> {}
                is Resource.Success<*> -> {
                    _plantDetail.value = data.data
                }
            }
        }
    }
}