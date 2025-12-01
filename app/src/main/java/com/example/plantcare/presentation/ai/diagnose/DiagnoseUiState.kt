package com.example.plantcare.presentation.ai.diagnose

import android.net.Uri
import com.example.plantcare.domain.model.MyPlant
import com.example.plantcare.domain.model.PlantDiagnosis

data class DiagnoseUiState(
    val isLoading : Boolean = false,
    val plantDiagnosis : PlantDiagnosis? = null,
    val error : String? = null,
    val selectedPlant : MyPlant? = null,
    val imageUri : Uri? = null,
    val myPlantList : List<MyPlant> = emptyList()
)
