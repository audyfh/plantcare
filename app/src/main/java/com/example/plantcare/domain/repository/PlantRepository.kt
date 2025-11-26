package com.example.plantcare.domain.repository

import androidx.paging.PagingData
import com.example.plantcare.domain.model.PlantDetailDomain
import com.example.plantcare.domain.model.PlantDomain
import com.example.plantcare.util.Resource
import kotlinx.coroutines.flow.Flow

interface PlantRepository {

    fun getPlantsPagingData(): Flow<PagingData<PlantDomain>>
    suspend fun getPlantDetail(id: String): Resource<PlantDetailDomain>
}


