package com.example.plantcare.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import com.example.plantcare.presentation.home.comps.FeatureCard
import com.example.plantcare.presentation.home.comps.HomeHeader
import com.example.plantcare.presentation.home.comps.HomeSearchBar
import com.example.plantcare.presentation.home.comps.WeatherCard
import com.example.plantcare.presentation.home.comps.listFeatureItems
import com.example.plantcare.ui.theme.PlantCareTheme

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    var searchQuery by remember {
        mutableStateOf("")
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(14.dp),
    ) {
        HomeHeader()
        Spacer(modifier.height(24.dp))
        HomeSearchBar(
            query = searchQuery,
            onQueryChange = {
                searchQuery = it
            },
            onSearch = {}
        )
        Spacer(modifier.height(24.dp))
        WeatherCard()
        Spacer(modifier.height(24.dp))
        Text(
            "Plant Care",
            style = MaterialTheme.typography.titleMedium,
            color = Color(0xFF1F4E20)
        )
        Spacer(modifier.height(14.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(listFeatureItems.size){
                FeatureCard(
                    title = listFeatureItems[it].title,
                    description = listFeatureItems[it].description,
                    icon = listFeatureItems[it].icon
                ) {
                    when(it){
                        0 -> {}
                        1 -> {}
                        2 -> {}
                        3 -> {}
                    }
                }
            }
        }
        Spacer(modifier.height(24.dp))
        Text(
            "Random Plants",
            style = MaterialTheme.typography.titleMedium,
            color = Color(0xFF1F4E20)
        )
        Spacer(modifier.height(14.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun PrevHome() {
    PlantCareTheme {
        HomeScreen()
    }
}