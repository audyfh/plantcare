package com.example.plantcare.presentation.mygarden.plantdetail

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.plantcare.domain.model.MyPlant
import com.example.plantcare.ui.theme.GrayDark
import com.example.plantcare.ui.theme.OffWhite
import com.example.plantcare.ui.theme.PrimaryGreen
import com.example.plantcare.ui.theme.SandBeige
import com.example.plantcare.ui.theme.SecondaryGreen
import com.example.plantcare.ui.theme.SoftMint
import com.example.plantcare.util.Utility
import org.koin.compose.viewmodel.koinViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun PlantDetailScreen(
    modifier: Modifier = Modifier,
    plantId: Int,
    navigateBack: () -> Unit
) {
    val viewModel: PlantDetailViewModel = koinViewModel()

    LaunchedEffect(plantId) {
        viewModel.getPlantDetail(id = plantId)
    }

    val plantState by viewModel.plantDetail.collectAsState() // MyPlant?

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
                Spacer(Modifier.width(12.dp))
            }
        },
        modifier = modifier.fillMaxSize().background(Color.White)
    ) { paddingValues ->
        if (plantState == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(OffWhite)
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = PrimaryGreen)
            }
        } else {
            val plant = plantState!!
            PlantDetailContent(
                plant = plant,
                modifier = Modifier
                    .padding(top = paddingValues.calculateTopPadding())
                    .fillMaxSize()
            )
        }
    }
}

@Composable
private fun PlantDetailContent(
    plant: MyPlant,
    modifier: Modifier = Modifier
) {
    val lastWateredPlaceholder = if (plant.lastWateredAt == 0L) {
        "Not watered yet"
    } else {
        Utility.formatDateTime(plant.lastWateredAt)
    }

    val healthStatusText = plant.healthStatus.ifBlank { "No health check yet" }

    val lastDiagnosisPlaceholder = if (plant.lastDiagnosisAt == 0L) {
        "No diagnosis history"
    } else {
        Utility.formatDateTime(plant.lastDiagnosisAt)
    }

    val diagnosisResultText = plant.diagnosisResult.ifBlank { "No diagnosis recorded" }

    Column(
        modifier = modifier
            .background(Color.White)
            .padding(14.dp)
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = SoftMint
            )
        ) {
            if (plant.imageUrl.isNotEmpty()) {
                AsyncImage(
                    model = plant.imageUrl,
                    contentDescription = plant.name,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(20.dp)),
                    contentScale = ContentScale.Crop
                )
            } else {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = null,
                        tint = PrimaryGreen,
                        modifier = Modifier.size(48.dp)
                    )
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        Text(
            text = plant.name,
            style = MaterialTheme.typography.titleLarge,
            color = PrimaryGreen
        )

        if (!plant.species.isNullOrBlank()) {
            Text(
                text = plant.species ?: "",
                style = MaterialTheme.typography.bodyMedium,
                color = GrayDark
            )
        }

        Spacer(Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(999.dp))
                    .background(SoftMint)
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Text(
                    text = plant.category,
                    style = MaterialTheme.typography.bodySmall,
                    color = SecondaryGreen
                )
            }
        }

        Spacer(Modifier.height(20.dp))

        Text(
            text = "Watering Info",
            style = MaterialTheme.typography.titleSmall,
            color = PrimaryGreen
        )
        Spacer(Modifier.height(8.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = OffWhite),
            border = BorderStroke(1.dp, SoftMint)
        ) {
            Column(
                modifier = Modifier.padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                InfoRow(
                    label = "Interval",
                    value = "Every ${plant.wateringInterval} day(s)"
                )
                InfoRow(
                    label = "Times per day",
                    value = "${plant.wateringPerDay} time(s)"
                )
                InfoRow(
                    label = "Last watered at",
                    value = lastWateredPlaceholder
                )
            }
        }

        Spacer(Modifier.height(20.dp))

        Text(
            text = "Health & Diagnosis",
            style = MaterialTheme.typography.titleSmall,
            color = PrimaryGreen
        )
        Spacer(Modifier.height(8.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = OffWhite),
            border = BorderStroke(1.dp, SandBeige)
        ) {
            Column(
                modifier = Modifier.padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                InfoRow(
                    label = "Health status",
                    value = healthStatusText
                )
                InfoRow(
                    label = "Last diagnosis",
                    value = lastDiagnosisPlaceholder
                )
                Column {
                    Text(
                        text = "Diagnosis result",
                        style = MaterialTheme.typography.bodySmall,
                        color = GrayDark
                    )
                    Spacer(Modifier.height(2.dp))
                    Text(
                        text = diagnosisResultText,
                        style = MaterialTheme.typography.bodyMedium,
                        color = GrayDark
                    )
                }
            }
        }
    }
}

@Composable
private fun InfoRow(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = GrayDark
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = GrayDark,
            textAlign = TextAlign.End
        )
    }
}

