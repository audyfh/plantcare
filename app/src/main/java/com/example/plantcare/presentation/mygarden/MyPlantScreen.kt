package com.example.plantcare.presentation.mygarden

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.plantcare.presentation.mygarden.comps.MyPlantCard
import com.example.plantcare.ui.theme.PrimaryGreen

@Composable
fun MyPlantScreen(
    modifier: Modifier = Modifier,
    viewModel: MyGardenViewModel,
    navigateToDetail : (Int) -> Unit,
    navigateToAddPlant : () -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val plantList = uiState.myPlants


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 14.dp)
    ) {
        if (plantList.isEmpty()){
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Text(
                    "There is no plant in your garden yet, ",
                    style = MaterialTheme.typography.bodyMedium

                )
                Text(
                    "Add now",
                    textDecoration = TextDecoration.Underline,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = SemiBold,
                    color = PrimaryGreen,
                    modifier = Modifier.clickable{
                        navigateToAddPlant()
                    }
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(plantList) {
                    MyPlantCard(
                        plant = it,
                        onClick = {
                            navigateToDetail(it)
                        }
                    )
                }
            }
        }
    }
}