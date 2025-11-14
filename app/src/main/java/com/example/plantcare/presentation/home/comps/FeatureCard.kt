package com.example.plantcare.presentation.home.comps

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.plantcare.R
import com.example.plantcare.ui.theme.PlantCareTheme

@Composable
fun FeatureCard(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    icon: Int,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .background(color = Color(0xFFF7F7F7))
            .height(150.dp)
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(8.dp)
        ) {
            Text(
                title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                description,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray.copy(alpha = 0.8f)
            )
        }
        Image(
            painter = painterResource(icon),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(90.dp)
        )
    }
}

data class FeatureItem(
    val title: String,
    val description: String,
    val icon: Int
)

val listFeatureItems = listOf(
    FeatureItem(
        title = "Diagnose",
        description = "Check your plant’s health",
        icon = R.drawable.feature1
    ),
    FeatureItem(
        title = "Identify",
        description = "Recognize a plant",
        icon = R.drawable.feature2
    ),
    FeatureItem(
        title = "My Plants",
        description = "All your plants in one place",
        icon = R.drawable.feature3_1
    ),
    FeatureItem(
        title = "Reminder",
        description = "Stay on top of your plant care",
        icon = R.drawable.feature4
    ),
)

@Preview(showBackground = true)
@Composable
private fun PrevCard() {
    PlantCareTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(14.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                FeatureCard(
                    modifier = Modifier.weight(1f),
                    title = "Diagnose",
                    description = "Check your plant’s health",
                    icon = R.drawable.feature1
                ) { }
                FeatureCard(
                    modifier = Modifier.weight(1f),
                    title = "Diagnose",
                    description = "Check your plant’s health",
                    icon = R.drawable.feature1
                ) { }
            }

        }

    }
}