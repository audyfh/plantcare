package com.example.plantcare.presentation.mygarden

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.plantcare.presentation.mygarden.comps.ScheduleCalendar
import com.example.plantcare.presentation.mygarden.comps.ScheduleCard
import com.example.plantcare.ui.theme.PrimaryGreen
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun ScheduleScreen(
    modifier: Modifier = Modifier,
    viewModel: MyGardenViewModel
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val scheduleList = uiState.scheduleList
    val currentMonth = YearMonth.now()
    val calendarState = rememberCalendarState(
        startMonth = currentMonth.minusMonths(12),
        endMonth = currentMonth.plusMonths(12),
        firstDayOfWeek = DayOfWeek.SUNDAY,
        firstVisibleMonth = currentMonth
    )
    val coroutineScope = rememberCoroutineScope()



    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(14.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                Icons.AutoMirrored.Default.KeyboardArrowLeft,
                contentDescription = "back button",
                modifier = modifier
                    .size(36.dp)
                    .clickable {
                        coroutineScope.launch {
                            val prevMonth = calendarState.firstVisibleMonth.yearMonth.minusMonths(1)
                            calendarState.animateScrollToMonth(prevMonth)
                        }
                    },
                tint = PrimaryGreen
            )
            Text(
                calendarState.firstVisibleMonth.yearMonth
                    .month
                    .getDisplayName(TextStyle.FULL, Locale.getDefault()) +
                        " " + calendarState.firstVisibleMonth.yearMonth.year,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )
            Icon(
                Icons.AutoMirrored.Default.KeyboardArrowRight,
                contentDescription = "back button",
                modifier = modifier
                    .size(36.dp)
                    .clickable {
                        coroutineScope.launch {
                            val prevMonth = calendarState.firstVisibleMonth.yearMonth.plusMonths(1)
                            calendarState.animateScrollToMonth(prevMonth)
                        }
                    },
                tint = PrimaryGreen
            )
        }
        ScheduleCalendar(
            schedules = scheduleList,
            onDateSelected = {
                viewModel.onDateSelected(it)
            },
            calendarState = calendarState,
            modifier = Modifier.fillMaxWidth()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 14.dp, vertical = 8.dp),
        ) {
            Text(
                "Today Schedule",
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )
            when {
                uiState.isLoading -> {
                    Text("Loading...")
                }
                uiState.errorMsg != null -> {
                    Text("Error: ${uiState.errorMsg}")
                }
                uiState.scheduleForSelectedDate.isEmpty() -> {
                    Text("No Schedule Today")
                }
                else -> {
                    Spacer(modifier = modifier.height(12.dp))
                    val data = uiState.scheduleForSelectedDate
                    LazyColumn(
                        modifier = modifier
                            .fillMaxSize()
                            .weight(1f),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(data) {
                            ScheduleCard(
                                schedule = it
                            )
                        }
                    }
                }
            }
        }
    }

}