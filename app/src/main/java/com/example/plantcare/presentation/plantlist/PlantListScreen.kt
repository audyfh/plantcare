package com.example.plantcare.presentation.plantlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.plantcare.presentation.home.comps.PlantCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun PlantListScreen(
    modifier: Modifier = Modifier
) {
    val viewModel : PlantListViewModel = koinViewModel()
    val plantList = viewModel.plants.collectAsLazyPagingItems()
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(14.dp),
    ) {
        Text(
            "Random Plants",
            style = MaterialTheme.typography.titleMedium,
            color = Color(0xFF1F4E20)
        )
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(plantList.itemCount){
                if (plantList[it]!=null){
                    PlantCard(plant = plantList[it]!!)
                }
            }
        }
    }
}