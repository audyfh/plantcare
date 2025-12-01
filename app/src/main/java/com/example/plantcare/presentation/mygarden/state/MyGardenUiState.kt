package com.example.plantcare.presentation.mygarden.state

import com.example.plantcare.domain.model.MyPlant
import com.example.plantcare.domain.model.Schedule
import com.example.plantcare.util.Utility.toLocalDate
import java.time.LocalDate

data class MyGardenUiState(
    val myPlants: List<MyPlant> = emptyList(),
    val isLoading: Boolean = false,
    val errorMsg : String? = null,
    val scheduleList : List<Schedule> = emptyList(),
    val selectedDate : LocalDate = LocalDate.now()
) {
    val scheduleForSelectedDate: List<Schedule>
        get() = scheduleList.filter { it.date.toLocalDate() == selectedDate }
}