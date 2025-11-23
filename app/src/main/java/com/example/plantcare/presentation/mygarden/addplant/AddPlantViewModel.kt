package com.example.plantcare.presentation.mygarden.addplant

import android.net.Uri
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantcare.domain.model.MyPlant
import com.example.plantcare.domain.repository.CloudinaryRepository
import com.example.plantcare.domain.repository.MyPlantRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AddPlantViewModel(
    private val cloudinaryRepository: CloudinaryRepository,
    private val myPlantRepository: MyPlantRepository
) : ViewModel() {

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _imgUrl = MutableStateFlow<String?>(null)
    val imgUrl = _imgUrl.asStateFlow()

    fun addPlant(myPlant: MyPlant) {
        viewModelScope.launch {
            myPlantRepository.addPlant(myPlant)
        }
    }

    fun uploadPlantImage(imageUri: Uri) {
        viewModelScope.launch {
            try {
                _loading.value = true
                val url = cloudinaryRepository.uploadPlantImage(imageUri)
                _imgUrl.value = url
            } finally {
                _loading.value = false
            }
        }
    }

}