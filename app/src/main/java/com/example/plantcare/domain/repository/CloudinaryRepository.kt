package com.example.plantcare.domain.repository

import android.net.Uri

interface CloudinaryRepository {

    suspend fun uploadPlantImage(imageUri: Uri) : String
}