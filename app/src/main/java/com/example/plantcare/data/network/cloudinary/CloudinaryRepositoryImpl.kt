package com.example.plantcare.data.network.cloudinary

import android.content.Context
import android.net.Uri
import com.cloudinary.Cloudinary
import com.cloudinary.utils.ObjectUtils
import com.example.plantcare.domain.repository.CloudinaryRepository
import com.example.plantcare.util.readBytes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

class CloudinaryRepositoryImpl(
    private val context: Context,
    private val cloudinary: Cloudinary,
) : CloudinaryRepository {

    override suspend fun uploadPlantImage(imageUri: Uri): String = withContext(Dispatchers.IO) {
        val bytes = context.readBytes(imageUri)
        val option = ObjectUtils.asMap(
            "resource_type", "image",
            "upload_preset", "kwbihwuf",
            "folder", "plantcare/garden"
        )

        val result: Map<*, *> = cloudinary.uploader().upload(bytes, option)
        val secureUrl = result["secure_url"] as? String
            ?: throw IllegalStateException("No secure url found")
        secureUrl
    }
}