package com.example.plantcare.data.network

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.plantcare.domain.model.PlantDomain
import com.example.plantcare.domain.repository.PlantRepository
import kotlinx.coroutines.flow.Flow

class PlantRepositoryImpl(
    private val plantService: PlantService
) : PlantRepository {

    override fun getPlantsPagingData(): Flow<PagingData<PlantDomain>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                PlantPagingSource(plantService)
            }
        ).flow
    }
}