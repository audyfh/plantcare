package com.example.plantcare.data.network.plantlist

import com.example.plantcare.BuildConfig
import com.example.plantcare.data.network.plantlist.model.PlantResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface PlantService {

    companion object {
        const val API_KEY = BuildConfig.PLANT_API_KEY
    }

    @GET("plants?token=$API_KEY")
    suspend fun getPlantsPagingData(
        @Query("page") page: Int,
    ) : Response<PlantResponse>


}


