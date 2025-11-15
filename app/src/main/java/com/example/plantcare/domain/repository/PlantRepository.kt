package com.example.plantcare.domain.repository

import androidx.paging.PagingData
import com.example.plantcare.domain.model.PlantDomain
import kotlinx.coroutines.flow.Flow

interface PlantRepository {

    fun getPlantsPagingData(): Flow<PagingData<PlantDomain>>
}


