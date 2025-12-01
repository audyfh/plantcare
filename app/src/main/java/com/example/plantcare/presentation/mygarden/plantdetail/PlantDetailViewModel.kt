package com.example.plantcare.presentation.mygarden.plantdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantcare.domain.model.MyPlant
import com.example.plantcare.domain.repository.MyGardenRepository
import com.example.plantcare.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PlantDetailViewModel(
    private val myGardenRepository: MyGardenRepository
) : ViewModel() {

    private val _plantDetail = MutableStateFlow<MyPlant?>(null)
    val plantDetail = _plantDetail.asStateFlow()


    fun getPlantDetail(id: Int) {
        viewModelScope.launch {
            val data = myGardenRepository.getPlantById(id)
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