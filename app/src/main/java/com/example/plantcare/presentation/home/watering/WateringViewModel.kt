package com.example.plantcare.presentation.home.watering

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantcare.domain.model.MyPlant
import com.example.plantcare.domain.repository.MyGardenRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WateringViewModel(
    private val myGardenRepository: MyGardenRepository
) : ViewModel() {

    private val _plants = MutableStateFlow<List<MyPlant>>(emptyList())
    val plants = _plants.asStateFlow()

    init {
        observePlantsNeedWatering()
    }

    private fun observePlantsNeedWatering(
        nowProvider: () -> Long = { System.currentTimeMillis() }
    ) {
        viewModelScope.launch {
            myGardenRepository.getAllPlants().collect { allPlants ->
                val now = nowProvider()

                val needWatering = allPlants.filter { plant ->
                    if (plant.lastWateredAt == 0L) {
                        true
                    } else {
                        val daysSinceLastWater =
                            (now - plant.lastWateredAt) / (1000L * 60L * 60L * 24L)
                        daysSinceLastWater >= plant.wateringInterval
                    }
                }

                _plants.value = needWatering
            }
        }
    }

    fun markWatered(
        plant: MyPlant,
        wateringTime: Long = System.currentTimeMillis()
    ) {
        viewModelScope.launch {
            val updatedPlant = plant.copy(lastWateredAt = wateringTime)
            myGardenRepository.updatePlant(updatedPlant)
        }
    }
}