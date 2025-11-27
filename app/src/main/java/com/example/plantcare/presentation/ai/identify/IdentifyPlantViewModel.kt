package com.example.plantcare.presentation.ai.identify

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantcare.domain.repository.AiRepository
import com.example.plantcare.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class IdentifyPlantViewModel(
    private val aiRepository: AiRepository
) : ViewModel() {

    private val _state = MutableStateFlow(IdentifyUiState())
    val state = _state.asStateFlow()


    fun identifyPlant(
        context: Context,
        image: Uri
    ) {
        viewModelScope.launch {
            _state.value = state.value.copy(
                isLoading = true
            )
            val data = aiRepository.identifyPlant(context, image)
            if (data is Resource.Success) {
                _state.value = state.value.copy(
                    isLoading = false,
                    plant = data.data
                )
            } else {
                _state.value = state.value.copy(
                    isLoading = false,
                    error = data.msg
                )
            }
        }
    }

    fun updateImageUri(uri: Uri) {
        _state.value = state.value.copy(
            imageUri = uri
        )
    }


}