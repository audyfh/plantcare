package com.example.plantcare.presentation.mygarden

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.plantcare.domain.model.MyPlant
import com.example.plantcare.presentation.mygarden.comps.CategoryField
import com.example.plantcare.presentation.mygarden.comps.CustomTextField
import com.example.plantcare.ui.theme.PlantCareTheme
import com.example.plantcare.ui.theme.PrimaryGreen

@Composable
fun AddPlantScreen(
    modifier: Modifier = Modifier,
    navigateBack : () -> Unit,
    viewModel: MyGardenViewModel
) {

    var name by remember { mutableStateOf("") }
    var species by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var waterInterval by remember { mutableStateOf("") }
    var waterDay by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(14.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.AutoMirrored.Default.ArrowBack,
                contentDescription = "back button",
                modifier = modifier.clickable {
                    navigateBack()
                }
            )
            Text(
                "Add Plant",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier.width(20.dp))
        }
        Spacer(modifier.height(24.dp))
        CustomTextField(
            textLabel = "Plant Name",
            text = name,
            placeholder = "Aglaonema"
        ) { name = it}
        Spacer(modifier.height(8.dp))
        CustomTextField(
            textLabel = "Species",
            text = species,
            placeholder = "Aglaonema"
        ) { species = it}
        Spacer(modifier.height(8.dp))
        CategoryField(
            text = category
        ) { category = it}
        Spacer(modifier.height(8.dp))
        CustomTextField(
            textLabel = "Watering Interval (day)",
            text = waterInterval,
            placeholder = "3",
            isNumber = true
        ) { waterInterval = it}
        Spacer(modifier.height(8.dp))
        CustomTextField(
            textLabel = "Watering per day",
            text = waterDay,
            placeholder = "2",
            isNumber = true
        ) { waterDay = it }
        Spacer(modifier.height(14.dp))
        Text(
            "Plant Image"
        )
        Box(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
                .height(90.dp)
                .drawBehind {
                    drawRoundRect(
                        cornerRadius = CornerRadius(16.dp.toPx()),
                        color = Color.Gray,
                        style = Stroke(
                            width = 2.dp.toPx(),
                            pathEffect = PathEffect.dashPathEffect(
                                intervals = floatArrayOf(10f,10f),
                                phase = 0f
                            )
                        )
                    )
                }
        ) {
            Text(
                "Add Image",
                modifier = Modifier.align(Alignment.Center),
            )
        }
        Spacer(modifier.weight(1f))
        Button(
            onClick = {
                viewModel.addPlant(
                    MyPlant(
                        id = 0,
                        name = name,
                        species = species,
                        category = category,
                        wateringInterval = waterInterval.toInt(),
                        wateringPerDay = waterDay.toInt(),
                        imageUrl = imageUrl,
                        diagnosisResult = "",
                        healthStatus = "",
                        lastWateredAt = 0L,
                        lastDiagnosisAt = 0L
                    )
                )
                navigateBack()
            },
            modifier = Modifier
                .padding(bottom = 12.dp)
                .fillMaxWidth()
                .height(55.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryGreen
            )
        ) {
            Text(
                "Add Plant",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = SemiBold
            )
        }
    }
}
