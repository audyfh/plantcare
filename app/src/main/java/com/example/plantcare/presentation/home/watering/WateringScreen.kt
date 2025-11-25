package com.example.plantcare.presentation.home.watering

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.plantcare.domain.model.MyPlant
import com.example.plantcare.presentation.home.watering.comps.WateringPlantItem
import com.example.plantcare.ui.theme.GrayDark
import com.example.plantcare.ui.theme.OffWhite
import com.example.plantcare.ui.theme.PrimaryGreen
import com.example.plantcare.ui.theme.SecondaryGreen
import com.example.plantcare.ui.theme.SoftMint
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun WateringScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit
) {
    val viewModel: WateringViewModel = koinViewModel()
    val plants by viewModel.plants.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                Icons.AutoMirrored.Default.ArrowBack,
                contentDescription = "back button",
                modifier = modifier.clickable {
                    navigateBack()
                }
            )
            Text(
                "Plants Watering",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier.width(20.dp))
        }

        Spacer(Modifier.height(8.dp))

        Text(
            text = if (plants.isEmpty()) {
                "No plants need watering right now."
            } else {
                "${plants.size} plant(s) need watering today."
            },
            style = MaterialTheme.typography.bodyMedium,
            color = GrayDark,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(Modifier.height(16.dp))

        if (plants.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = SecondaryGreen,
                        modifier = Modifier.size(48.dp)
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "All your plants are happy and hydrated 🌿",
                        style = MaterialTheme.typography.bodyMedium,
                        color = GrayDark,
                        textAlign = TextAlign.Center
                    )
                }
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(plants) { plant ->
                    WateringPlantItem(
                        plant = plant,
                        onMarkWatered = { viewModel.markWatered(plant) }
                    )
                }
            }
        }
    }
}


