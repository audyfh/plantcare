package com.example.plantcare.presentation.plantlist.comps

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.plantcare.ui.theme.GrayDark
import com.example.plantcare.ui.theme.SecondaryGreen
import com.example.plantcare.ui.theme.SoftMint

@Composable
fun InfoRow(
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

@Composable
fun InfoChip(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(999.dp))
            .background(SoftMint)
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text(
            text = "$label: $value",
            style = MaterialTheme.typography.bodySmall,
            color = SecondaryGreen
        )
    }
}

fun describeLight(light: Int): String = when {
    light >= 8 -> "Full sun"
    light >= 5 -> "Partial sun"
    light >= 3 -> "Indirect light"
    else -> "Low light"
}

fun describeHumidity(h: Int): String = when {
    h >= 8 -> "High humidity"
    h >= 5 -> "Moderate humidity"
    else -> "Low humidity"
}

fun describeSoil(n: Int): String = when {
    n >= 8 -> "Very rich soil"
    n >= 5 -> "Moderately fertile soil"
    else -> "Poor soil tolerant"
}

fun buildTempRange(min: Double?, max: Double?): String =
    when {
        min == null && max == null -> "Unknown"
        min != null && max != null -> "${min}°C – ${max}°C"
        min != null -> "From ${min}°C"
        else -> "Up to ${max}°C"
    }
