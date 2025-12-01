package com.example.plantcare.presentation.ai.diagnose.comp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.plantcare.domain.model.MyPlant
import com.example.plantcare.ui.theme.GrayDark
import com.example.plantcare.ui.theme.OffWhite
import com.example.plantcare.ui.theme.PrimaryGreen
import com.example.plantcare.ui.theme.SoftMint
@Composable
fun SelectedPlantInfo(
    plant: MyPlant,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = OffWhite),
        border = BorderStroke(1.dp, SoftMint)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (plant.imageUrl.isNotEmpty()) {
                AsyncImage(
                    model = plant.imageUrl,
                    contentDescription = plant.name,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(14.dp)),
                    contentScale = ContentScale.Crop
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(SoftMint),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = plant.name.firstOrNull()?.uppercase() ?: "P",
                        style = MaterialTheme.typography.bodyLarge,
                        color = PrimaryGreen
                    )
                }
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = plant.name,
                    style = MaterialTheme.typography.titleSmall,
                    color = PrimaryGreen
                )
                if (!plant.species.isNullOrBlank()) {
                    Text(
                        text = plant.species ?: "",
                        style = MaterialTheme.typography.bodySmall,
                        color = GrayDark
                    )
                }
                Text(
                    text = "Category: ${plant.category}",
                    style = MaterialTheme.typography.bodySmall,
                    color = GrayDark
                )
                Text(
                    text = "Watering: evert ${plant.wateringInterval} day, ${plant.wateringPerDay}x/day",
                    style = MaterialTheme.typography.bodySmall,
                    color = GrayDark
                )
                if (plant.healthStatus.isNotBlank()) {
                    Text(
                        text = "Current Status: ${plant.healthStatus}",
                        style = MaterialTheme.typography.bodySmall,
                        color = GrayDark
                    )
                }
            }
        }
    }
}
