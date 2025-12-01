package com.example.plantcare.presentation.ai.diagnose

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.plantcare.presentation.mygarden.comps.MyPlantCard
import com.example.plantcare.ui.theme.PrimaryGreen

@Composable
fun DiagnoseScreen(
    modifier: Modifier = Modifier,
    viewModel: DiagnosisViewModel,
    navigateBack: () -> Unit,
    navigateDiagnose : () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val myPlant = state.myPlantList

    when {
        myPlant.isEmpty() -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(14.dp),
            ) {
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
                        text = "Select Plant",
                        style = MaterialTheme.typography.titleMedium,
                        color = PrimaryGreen
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = "There is no plant in your garden yet",
                        style = MaterialTheme.typography.bodyMedium,
                        color = PrimaryGreen,
                    )
                }

            }
        }

        else -> {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(14.dp)
            ) {
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
                        text = "Select Plant",
                        style = MaterialTheme.typography.titleMedium,
                        color = PrimaryGreen
                    )
                    Spacer(Modifier.width(24.dp))
                }
                Spacer(Modifier.height(24.dp))
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(myPlant) { plant->
                        MyPlantCard(
                            plant = plant,
                            onClick = {
                                viewModel.setSelectedPlant(plant)
                                navigateDiagnose()
                            }
                        )
                    }
                }

            }
        }

    }

}