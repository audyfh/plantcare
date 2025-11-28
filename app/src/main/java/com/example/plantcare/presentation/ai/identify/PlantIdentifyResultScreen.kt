package com.example.plantcare.presentation.ai.identify

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.plantcare.domain.model.PlantIdentify
import com.example.plantcare.ui.theme.GrayDark
import com.example.plantcare.ui.theme.OffWhite
import com.example.plantcare.ui.theme.PrimaryGreen

@Composable
fun PlantIdentifyResultScreen(
    plant: PlantIdentify,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(OffWhite)
            .padding(14.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = plant.name.ifBlank { "Nama tidak diketahui" },
            style = MaterialTheme.typography.titleMedium,
            color = PrimaryGreen
        )
        Text(
            text = plant.scientific_name,
            style = MaterialTheme.typography.bodyMedium,
            color = GrayDark,

        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = "Family: ${plant.family}",
            style = MaterialTheme.typography.bodySmall,
            color = GrayDark
        )
        Text(
            text = "Genus: ${plant.genus}",
            style = MaterialTheme.typography.bodySmall,
            color = GrayDark
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = plant.description,
            style = MaterialTheme.typography.bodyMedium,
            color = GrayDark
        )
    }
}
