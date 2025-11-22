package com.example.plantcare.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.plantcare.R
import com.example.plantcare.domain.model.dummyPlant
import com.example.plantcare.presentation.home.comps.FeatureCard
import com.example.plantcare.presentation.home.comps.HomeHeader
import com.example.plantcare.presentation.home.comps.HomeSearchBar
import com.example.plantcare.presentation.home.comps.PlantCard
import com.example.plantcare.presentation.home.comps.TaskCard
import com.example.plantcare.presentation.home.comps.WeatherCard
import com.example.plantcare.presentation.home.comps.listFeatureItems
import com.example.plantcare.ui.theme.PlantCareTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    val viewModel : HomeViewModel = koinViewModel()
    var searchQuery by remember {
        mutableStateOf("")
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(14.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        item {
            HomeHeader()
        }

        item {
            HomeSearchBar(
                query = searchQuery,
                onQueryChange = { searchQuery = it },
                onSearch = {}
            )
        }

        item {
            WeatherCard()
        }

        item {
            Text(
                "Plant Care",
                style = MaterialTheme.typography.titleMedium,
                color = Color(0xFF1F4E20)
            )
        }

        item {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                userScrollEnabled = false
            ) {
                items(listFeatureItems) { feature ->
                    FeatureCard(
                        title = feature.title,
                        description = feature.description,
                        icon = feature.icon
                    ) {

                    }
                }
            }
        }

        item {
            Text(
                "Tugasmu hari ini!",
                style = MaterialTheme.typography.titleMedium,
                color = Color(0xFF1F4E20)
            )
            Spacer(modifier = Modifier.height(12.dp))
            TaskCard(
                title = "Plant Watering",
                description = "Plant need to watering",
                totalTask = 10,
                progress = 0f,
                img = R.drawable.taskimg
            )
        }
        item {
            Spacer(modifier = Modifier.height(6.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PrevHome() {
    PlantCareTheme {
        HomeScreen()
    }
}
