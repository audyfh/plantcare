package com.example.plantcare.presentation.ai.diagnose.comp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.plantcare.domain.model.PlantDiagnosis
import com.example.plantcare.ui.theme.GrayDark
import com.example.plantcare.ui.theme.OffWhite
import com.example.plantcare.ui.theme.PrimaryGreen
import com.example.plantcare.ui.theme.SoftMint

@Composable
fun DiagnosisResultCard(
    diagnosis: PlantDiagnosis,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = OffWhite),
        border = BorderStroke(1.dp, SoftMint)
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Hasil Analisis",
                    style = MaterialTheme.typography.titleSmall,
                    color = PrimaryGreen
                )
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(999.dp))
                        .background(
                            if (diagnosis.health_status.equals("healthy", ignoreCase = true))
                                SoftMint
                            else
                                Color(0xFFFFE4E4)
                        )
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = diagnosis.health_status,
                        style = MaterialTheme.typography.bodySmall,
                        color = if (diagnosis.health_status.equals("healthy", ignoreCase = true))
                            PrimaryGreen
                        else
                            Color(0xFFB3261E)
                    )
                }
            }

            Text(
                text = diagnosis.diagnosis_result,
                style = MaterialTheme.typography.bodyMedium,
                color = GrayDark
            )
        }
    }
}
