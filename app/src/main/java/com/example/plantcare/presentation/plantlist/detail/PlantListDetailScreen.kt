package com.example.plantcare.presentation.plantlist.detail

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.plantcare.presentation.plantlist.comps.PlantDetailContent
import com.example.plantcare.ui.theme.PrimaryGreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun PlantListDetailScreen(
    modifier: Modifier = Modifier,
    plantId: Int,
    navigateBack: () -> Unit
) {
    val viewModel: PlantListDetailViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(plantId) {
        viewModel.getPlantDetail(plantId.toString())
    }

    Log.d("PlantListDetailScreen", "Plant ID: $plantId")
    Log.d("PlantListDetailScreen", "Plant Data: ${state.plant}")
    Log.d("PlantListDetailScreen", "Family : ${state.plant?.mainSpecies?.family}")
    Log.d("PlantListDetailScreen", "Genus : ${state.plant?.mainSpecies?.genus}")

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = PrimaryGreen,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { navigateBack() }
                )
                Text(
                    text = "Plant Detail",
                    style = MaterialTheme.typography.titleMedium,
                    color = PrimaryGreen
                )
                Spacer(Modifier.width(24.dp))
            }
        },
        modifier = modifier.fillMaxSize()
    ) { paddingValues ->
        when {
            state.isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = PrimaryGreen)
                }
            }

            state.error != null -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = state.error ?: "Something went wrong",
                            color = Color.Red,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = "Tap to retry",
                            color = PrimaryGreen,
                            modifier = Modifier.clickable {
                                viewModel.getPlantDetail(plantId.toString())
                            }
                        )
                    }
                }
            }

            state.plant != null -> {
                PlantDetailContent(
                    plant = state.plant!!,
                    modifier = Modifier
                        .padding(
                            top = paddingValues.calculateTopPadding(),
                            start = 14.dp,
                            end = 14.dp,
                            bottom = 14.dp
                        )
                )
            }
        }
    }
}