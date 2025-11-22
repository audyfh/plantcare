package com.example.plantcare.presentation.navigation.comps


import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.plantcare.R
import com.example.plantcare.ui.theme.SoftMint


@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    selected : Int,
    onClick : (Int) -> Unit
) {
    val items = listOf("Home", "Random", "AI", "My Plant")
    NavigationBar(
        containerColor = Color.White,
    ) {
        navItems.forEachIndexed { index, item ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                   indicatorColor = SoftMint
                ),
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title
                    )
                },
                label = {
                    Text(text = item.title)
                },
                selected = selected == index,
                onClick = {
                    onClick(index)
                }
            )
        }
    }
}

data class NavBarItem(
    val title : String,
    val icon : Int
)

private val navItems = listOf(
    NavBarItem(
        title = "Home",
        icon = R.drawable.ic_home
    ),
    NavBarItem(
        title = "Random",
        icon = R.drawable.ic_random
    ),
    NavBarItem(
        title = "AI",
        icon = R.drawable.ic_ai
    ),
    NavBarItem(
        title = "My Garden",
        icon = R.drawable.ic_plant
    )
)

