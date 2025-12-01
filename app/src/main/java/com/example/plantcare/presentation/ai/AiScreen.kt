package com.example.plantcare.presentation.ai

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.plantcare.R
import com.example.plantcare.presentation.ai.comps.AiCard

@Composable
fun AiScreen(
    modifier: Modifier = Modifier,
    navigateIdentify: () -> Unit,
    navigateDiagnose: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(14.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                "Plant AI",
                style = MaterialTheme.typography.titleMedium,
                color = Color(0xFF1F4E20)
            )
        }
        Spacer(Modifier.height(24.dp))
        AiCard(
            title = "Identify Plant",
            description = "Smart AI to identify unrecognized plant",
            image = R.drawable.img_identify,
            btnText = "Start Identify"
        ) { navigateIdentify()}
        Spacer(Modifier.height(24.dp))
        AiCard(
            title = "Diagnose Plant",
            description = "Diagnose your plant disease and health status",
            image = R.drawable.img_diagnose,
            btnText = "Start Diagnose"
        ) { navigateDiagnose()}
    }
}