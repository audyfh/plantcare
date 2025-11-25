package com.example.plantcare.presentation.plantlist

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
    val viewModel: PlantListViewModel = koinViewModel()
    val plantList = viewModel.plants.collectAsLazyPagingItems()
    Log.d("Encyclopedia", "PlantListScreen: ${plantList.itemCount}")
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(14.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                "Plant Encyclopedia",
                style = MaterialTheme.typography.titleMedium,
                color = Color(0xFF1F4E20)
            )
        }
        Spacer(Modifier.height(24.dp))
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(plantList.itemCount) {
                if (plantList[it] != null) {
                    PlantCard(plant = plantList[it]!!)
                }
            }
        }
    }
}