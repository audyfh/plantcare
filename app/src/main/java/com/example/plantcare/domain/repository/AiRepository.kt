package com.example.plantcare.domain.repository

import android.content.Context
import android.net.Uri
import com.example.plantcare.domain.model.PlantIdentify
import com.example.plantcare.util.Resource

interface AiRepository {

    suspend fun identifyPlant(
        context : Context,
        image : Uri,
    ) : Resource<PlantIdentify>
}