package com.example.plantcare.presentation.plantlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.plantcare.domain.repository.PlantRepository

class PlantListViewModel(
    private val plantRepository: PlantRepository
) : ViewModel(){

    val plants = plantRepository.getPlantsPagingData().cachedIn(viewModelScope)

}