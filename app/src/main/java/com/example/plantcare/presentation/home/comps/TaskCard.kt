package com.example.plantcare.presentation.home.comps

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.plantcare.R
import com.example.plantcare.ui.theme.PlantCareTheme

@Composable
fun TaskCard(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    totalTask: Int,
    progress: Float,
    img: Int,
    onClick: () -> Unit = {}
) {
    Row(    
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(Color(0xFFF0F8FF))
            .padding(12.dp)
            .clickable{
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Text(
                    text = "$totalTask $description",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "${(progress * 100).toInt()}%",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF29B6F6)
                )
                Spacer(modifier = Modifier.height(4.dp))

                LinearProgressIndicator(
                    progress = {progress},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    trackColor = Color(0xFF29B6F6).copy(alpha = 0.1f),
                    color = Color(0xFF29B6F6)
                )
            }
        }
        Spacer(modifier = Modifier.width(12.dp))
        Image(
            painter = painterResource(img),
            contentDescription = null,
            modifier = Modifier
                .fillMaxHeight()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Test() {
    PlantCareTheme {
        Column(
            modifier = Modifier.fillMaxSize().background(Color.White).padding(14.dp),
            verticalArrangement = Arrangement.Center
        ) {
            TaskCard(
                title = "Plant Watering",
                description = "Plant need to watering",
                totalTask = 10,
                progress = 0f,
                img = R.drawable.taskimg
            )
        }
    }
}