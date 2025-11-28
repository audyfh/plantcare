package com.example.plantcare.presentation.plantlist.comps

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.plantcare.domain.model.PlantDetailDomain
import com.example.plantcare.ui.theme.GrayDark
import com.example.plantcare.ui.theme.OffWhite
import com.example.plantcare.ui.theme.PrimaryGreen
import com.example.plantcare.ui.theme.SandBeige
import com.example.plantcare.ui.theme.SecondaryGreen
import com.example.plantcare.ui.theme.SoftMint

@Composable
fun PlantDetailContent(
    plant: PlantDetailDomain,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(14.dp),
    ) {
        // IMAGE
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = SoftMint
            )
        ) {
            if (!plant.imageUrl.isNullOrBlank()) {
                AsyncImage(
                    model = plant.imageUrl,
                    contentDescription = plant.commonName ?: plant.scientificName,
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
            text = plant.commonName ?: plant.scientificName ?: "Unknown plant",
            style = MaterialTheme.typography.titleLarge,
            color = PrimaryGreen
        )

        if (!plant.scientificName.isNullOrBlank()) {
            Text(
                text = plant.scientificName,
                style = MaterialTheme.typography.bodyMedium,
                color = GrayDark
            )
        }

        Spacer(Modifier.height(8.dp))

        // FAMILY / GENUS
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            plant.mainSpecies.family?.let {
                InfoChip(label = "Family", value = it)
            }
            plant.mainSpecies.genus?.let {
                InfoChip(label = "Genus", value = it)
            }
        }

        Spacer(Modifier.height(20.dp))

        // SPECIFICATIONS
        Text(
            text = "Specifications",
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
                    label = "Growth rate",
                    value = plant.mainSpecies.specifications?.growthRate ?: "Unknown"
                )
                InfoRow(
                    label = "Growth habit",
                    value = plant.mainSpecies.specifications?.growthHabit ?: "Unknown"
                )
            }
        }

        Spacer(Modifier.height(20.dp))

        // GROWTH REQUIREMENTS
        Text(
            text = "Growth Requirements",
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
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                val growth = plant.mainSpecies.growth

                InfoRow(
                    label = "Light",
                    value = growth?.light?.let { describeLight(it) } ?: "Unknown"
                )
                InfoRow(
                    label = "Humidity",
                    value = growth?.humidity?.let { describeHumidity(it) } ?: "Unknown"
                )
                InfoRow(
                    label = "Soil nutrients",
                    value = growth?.soilNutriments?.let { describeSoil(it) } ?: "Unknown"
                )
            }
        }
    }
}
