package com.example.plantcare.presentation.ai.identify

import android.net.Uri
import com.example.plantcare.domain.model.PlantIdentify

data class IdentifyUiState(
    val isLoading : Boolean = false,
    val plant : PlantIdentify? = null,
    val error : String? = null,
    val imageUri : Uri? = null
)
