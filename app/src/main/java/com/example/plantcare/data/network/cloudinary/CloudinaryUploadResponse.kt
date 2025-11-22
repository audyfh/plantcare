package com.example.plantcare.data.network.cloudinary

data class CloudinaryUploadResponse(
    val public_id: String,
    val secure_url: String,
    val url: String
)
