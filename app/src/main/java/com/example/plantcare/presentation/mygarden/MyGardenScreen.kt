package com.example.plantcare.presentation.mygarden

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.plantcare.presentation.mygarden.comps.CustomTab
import com.example.plantcare.ui.theme.OffWhite
import com.example.plantcare.ui.theme.PrimaryGreen
import com.example.plantcare.ui.theme.SoftMint
import org.koin.androidx.compose.koinViewModel

@Composable
fun MyGardenScreen(
    modifier: Modifier = Modifier,
    viewModel: MyGardenViewModel,
    navigateToAddPlant : () -> Unit
) {

    var selectedTab by remember { mutableStateOf("My Plants") }
    Scaffold(
        modifier = modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                containerColor = PrimaryGreen,
                shape = RoundedCornerShape(22.dp)
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Add",
                    tint = SoftMint
                    )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    "My Garden",
                    style = MaterialTheme.typography.titleMedium,
                    color = PrimaryGreen
                )
            }
            Spacer(Modifier.height(24.dp))

            CustomTab(
                selectedTab = selectedTab,
                onClick = { selectedTab = it }
            )

            Spacer(Modifier.height(24.dp))

            when(selectedTab) {
                "My Plants" -> MyPlantScreen(
                    viewModel = viewModel,
                    navigateToAddPlant = {navigateToAddPlant()}
                )
                "Schedule" -> Text("Schedule")
            }
        }
    }



}