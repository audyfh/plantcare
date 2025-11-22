package com.example.plantcare.presentation.mygarden.comps

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.plantcare.ui.theme.OffWhite
import com.example.plantcare.ui.theme.PrimaryGreen

@Composable
fun CustomTab(
    modifier: Modifier = Modifier,
    selectedTab: String,
    onClick : (String) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(color = OffWhite)
            .padding(
                horizontal = 16.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .clip(RoundedCornerShape(18.dp))
                .background(
                    color = if (selectedTab == "My Plants") PrimaryGreen else OffWhite
                )
                .clickable {
                   onClick("My Plants")
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                "My Plants",
                color = if (selectedTab == "My Plants") OffWhite else Color.Black
            )
        }
        Row(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .clip(RoundedCornerShape(18.dp))
                .background(
                    color = if (selectedTab == "Schedule") PrimaryGreen else OffWhite
                ) .clickable {
                    onClick("Schedule")
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                "Schedule",
                color = if (selectedTab == "Schedule") OffWhite else Color.Black
            )
        }
    }
}