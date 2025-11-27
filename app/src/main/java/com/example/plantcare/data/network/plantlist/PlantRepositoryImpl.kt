package com.example.plantcare.data.network.plantlist

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.plantcare.domain.model.PlantDetailDomain
import com.example.plantcare.domain.model.PlantDomain
import com.example.plantcare.domain.repository.PlantRepository
import com.example.plantcare.util.Resource
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

    override suspend fun getPlantDetail(id: String): Resource<PlantDetailDomain> {
        try {
            val data = plantService.getPlantDetail(id)
            if (data.isSuccessful) {
                val plant = data.body()?.data
                Log.d("PlantRepositoryImpl", "getPlantDetail: ${plant}")
                if (plant != null) {
                    return Resource.Success(plant.toDomain())
                } else {
                    return Resource.Error("Plant not found")
                }
            }
        } catch (e : Exception) {
            Log.e("PlantRepositoryImpl", "getPlantDetail: ${e.message}")
            return Resource.Error("Something went wrong")
        }
        return Resource.Error("Something went wrong")
    }

    override suspend fun searchPlants(query: String): Resource<List<PlantDomain>> {
        try {
            val data = plantService.searchPlants(query)
            if (data.isSuccessful){
                val plants = data.body()?.data
                if (plants != null) {
                    return Resource.Success(plants.map { it.toDomain() })
                } else {
                    return Resource.Error("Plant not found")
                }
            }
        } catch (e : Exception) {
            Log.e("PlantRepositoryImpl", "searchPlants: ${e.message}")
            return Resource.Error("Something went wrong")
        }
        return Resource.Error("Something went wrong")
    }
}