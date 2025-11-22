package com.example.plantcare.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.plantcare.presentation.ai.AiScreen
import com.example.plantcare.presentation.home.HomeScreen
import com.example.plantcare.presentation.mygarden.AddPlantScreen
import com.example.plantcare.presentation.mygarden.MyGardenScreen
import com.example.plantcare.presentation.mygarden.MyGardenViewModel
import com.example.plantcare.presentation.navigation.comps.BottomNavBar
import com.example.plantcare.presentation.plantlist.PlantListScreen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {

    val navController = rememberNavController()
    var selectedTab by remember { mutableIntStateOf(0) }
    val screenWithoutNavbar = listOf(
        AppRoute.AddPlantRoute::class.qualifiedName!!
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val showBottomBar = currentDestination?.route?.let { currentRoute ->
        !screenWithoutNavbar.any{
            currentRoute.startsWith(it)
        }
    } ?: true
    val gardenViewModel : MyGardenViewModel = koinViewModel()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            if (showBottomBar){
                BottomNavBar(
                    selected = selectedTab
                ) {
                    selectedTab = it
                    when(it){
                        0 -> navigateToTab(navController, AppRoute.HomeRoute)
                        1 -> navigateToTab(navController, AppRoute.PlantListRoute)
                        2 -> navigateToTab(navController, AppRoute.AiRoute)
                        3 -> navigateToTab(navController, AppRoute.MyGardenRoute)
                    }
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            modifier = Modifier.padding(paddingValues),
            navController = navController,
            startDestination = AppRoute.HomeRoute
        ) {
            composable<AppRoute.HomeRoute> {
                HomeScreen()
            }
            composable<AppRoute.PlantListRoute> {
                PlantListScreen()
            }
            composable<AppRoute.AiRoute> {
                AiScreen()
            }
            composable<AppRoute.MyGardenRoute> {
                MyGardenScreen(
                    viewModel = gardenViewModel,
                    navigateToAddPlant = {  navigateToTab(navController, AppRoute.AddPlantRoute) }
                )
            }
            composable<AppRoute.AddPlantRoute> {
                AddPlantScreen(
                    navigateBack = {navController.popBackStack()},
                    viewModel = gardenViewModel
                )
            }
        }
    }
}

private fun navigateToTab(
    navController: NavController,
    route: AppRoute
) {
    navController.navigate(route) {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}