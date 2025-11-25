package com.example.plantcare.presentation.home.watering.comps

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
fun WateringPlantItem(
    plant: MyPlant,
    onMarkWatered: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = OffWhite
        ),
        border = BorderStroke(1.dp, SoftMint)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                model = plant.imageUrl,
                contentDescription = plant.name,
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = plant.name,
                    style = MaterialTheme.typography.bodyLarge,
                    color = PrimaryGreen
                )
                if (!plant.species.isNullOrBlank()) {
                    Text(
                        text = plant.species,
                        style = MaterialTheme.typography.bodySmall,
                        color = GrayDark
                    )
                }
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "Every ${plant.wateringInterval} day(s) • ${plant.wateringPerDay}x/day",
                    style = MaterialTheme.typography.bodySmall,
                    color = GrayDark
                )
            }

            Spacer(Modifier.width(8.dp))

            Button(
                onClick = onMarkWatered,
                shape = RoundedCornerShape(999.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryGreen,
                    contentColor = OffWhite
                ),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text(
                    text = "Mark watered",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}