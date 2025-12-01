package com.example.plantcare.presentation.home


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantcare.domain.repository.MyGardenRepository
import com.example.plantcare.domain.repository.WeatherRepository
import com.example.plantcare.util.location.LocationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val myGardenRepository: MyGardenRepository,
    private val weatherRepository: WeatherRepository,
    private val locationRepository: LocationRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()

    private var initialNeedWatering: Int? = null

    init {
        getWateringProgress()
    }

    fun getWateringProgress() {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                isLoading = true
            )
            myGardenRepository.getAllPlants().collect { plants ->

                val now = System.currentTimeMillis()
                val plantsNeedWatering = plants.filter { plant ->
                    if (plant.lastWateredAt == 0L) {
                        true
                    } else {
                        val daysSinceLastWater =
                            (now - plant.lastWateredAt) / (1000L * 60L * 60L * 24L)
                        daysSinceLastWater >= plant.wateringInterval
                    }
                }

                val totalNeedWatering = plantsNeedWatering.size
                if (initialNeedWatering == null) {
                    initialNeedWatering = totalNeedWatering
                }

                val baseline = initialNeedWatering ?: totalNeedWatering

                val progress = when {
                    baseline == 0 -> 1f
                    totalNeedWatering == 0 -> 1f
                    else -> {
                        val done = (baseline - totalNeedWatering).coerceAtLeast(0)
                        done.toFloat() / baseline.toFloat()
                    }
                }

                _state.value = _state.value.copy(
                    totalNeedWatering = totalNeedWatering,
                    wateringProgress = progress,
                    isLoading = false
                )
            }
        }
    }

    fun getWeather() {
        val current = _state.value
        if (current.weather != null || current.isLoading) return
        viewModelScope.launch {
            _state.value = _state.value.copy(
                isLoading = true
            )
            val latLon = locationRepository.getCurrentLocation()
            Log.d("HomeViewModel", "LatLon: $latLon")

            val data = weatherRepository
                .getCurrentWeather(latLon?.latitude ?: -7.983908, latLon?.longitude ?: 112.621391)
            if (data != null) {
                Log.d("HomeViewModel", "getWeather: ${data.data}")
                _state.value = _state.value.copy(
                    weather = data.data,
                    isLoading = false
                )
            } else {
                Log.d("HomeViewModel", "getWeather: ${data.msg}")
                _state.value = _state.value.copy(
                    errorMsg = "Unknown Error",
                    isLoading = false
                )
            }

        }
    }

}


