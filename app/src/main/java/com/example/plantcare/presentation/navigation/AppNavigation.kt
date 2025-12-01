package com.example.plantcare.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.example.plantcare.presentation.ai.AiScreen
import com.example.plantcare.presentation.ai.diagnose.DiagnoseResultScreen
import com.example.plantcare.presentation.ai.diagnose.DiagnoseScreen
import com.example.plantcare.presentation.ai.diagnose.DiagnosisViewModel
import com.example.plantcare.presentation.ai.identify.IdentifyPlantScreen
import com.example.plantcare.presentation.home.HomeScreen
import com.example.plantcare.presentation.home.search.SearchScreen
import com.example.plantcare.presentation.home.watering.WateringScreen
import com.example.plantcare.presentation.mygarden.addplant.AddPlantScreen
import com.example.plantcare.presentation.mygarden.MyGardenScreen
import com.example.plantcare.presentation.mygarden.MyGardenViewModel
import com.example.plantcare.presentation.mygarden.plantdetail.PlantDetailScreen
import com.example.plantcare.presentation.navigation.comps.BottomNavBar
import com.example.plantcare.presentation.plantlist.PlantListScreen
import com.example.plantcare.presentation.plantlist.detail.PlantListDetailScreen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {

    val navController = rememberNavController()
    var selectedTab by remember { mutableIntStateOf(0) }
    val screenWithoutNavbar = listOf(
        AppRoute.AddPlantRoute::class.qualifiedName!!,
        AppRoute.PlantDetailRoute::class.qualifiedName!!,
        AppRoute.WateringPlantRoute::class.qualifiedName!!,
        AppRoute.PlantListDetailRoute::class.qualifiedName!!,
        AppRoute.PlantSearchRoute::class.qualifiedName!!,
        AppRoute.IdentifyRoute::class.qualifiedName!!,
        AppRoute.DiagnoseRoute::class.qualifiedName!!,
        AppRoute.DiagnoseResultRoute::class.qualifiedName!!
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val showBottomBar = currentDestination?.route?.let { currentRoute ->
        !screenWithoutNavbar.any {
            currentRoute.startsWith(it)
        }
    } ?: true
    val gardenViewModel: MyGardenViewModel = koinViewModel()
    val diagnoseViewModel: DiagnosisViewModel = koinViewModel()

    Scaffold(
        modifier = modifier.fillMaxSize().background(Color.White),
        bottomBar = {
            if (showBottomBar) {
                BottomNavBar(
                    selected = selectedTab
                ) {
                    selectedTab = it
                    when (it) {
                        0 -> navigateToTab(navController, AppRoute.HomeRoute)
                        1 -> navigateToTab(navController, AppRoute.PlantListRoute)
                        2 -> navigateToTab(navController, AppRoute.AiRoute)
                        3 -> navigateToTab(navController, AppRoute.MyGardenRoute())
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
                HomeScreen(
                    navigateWatering = {
                        navController.navigate(AppRoute.WateringPlantRoute)
                    },
                    navigateSearch = {
                        navController.navigate(AppRoute.PlantSearchRoute(it))
                    },
                    navigateDiagnose = {
                        navController.navigate(AppRoute.DiagnoseRoute)
                    },
                    navigateIdentify = {
                        navController.navigate(AppRoute.IdentifyRoute)
                    },
                    navigateSchedule = {
                        selectedTab = 3
                        navigateToTab(navController, AppRoute.MyGardenRoute(it))
                    },
                    navigateMyPlant = {
                        selectedTab = 3
                        navigateToTab(navController, AppRoute.MyGardenRoute(it))
                    }
                )
            }
            composable<AppRoute.PlantListRoute> {
                PlantListScreen(
                    navigateDetail = {
                        navController.navigate(AppRoute.PlantListDetailRoute(it))
                    }
                )
            }
            navigation<AppRoute.AiRootRoute>(startDestination = AppRoute.AiRoute){
                composable<AppRoute.AiRoute> {
                    AiScreen(
                        navigateIdentify = {navController.navigate(AppRoute.IdentifyRoute)},
                        navigateDiagnose = {navController.navigate(AppRoute.DiagnoseRoute)}
                    )
                }
                composable<AppRoute.IdentifyRoute> {
                    IdentifyPlantScreen(
                        navigateBack = {navController.popBackStack()}
                    )
                }
                composable<AppRoute.DiagnoseRoute> {

                    DiagnoseScreen(
                        viewModel = diagnoseViewModel,
                        navigateBack = {navController.popBackStack()},
                        navigateDiagnose = {navController.navigate(AppRoute.DiagnoseResultRoute)}
                    )
                }
                composable<AppRoute.DiagnoseResultRoute> { entry ->

                    DiagnoseResultScreen(
                        viewModel = diagnoseViewModel,
                        navigateBack = { navController.popBackStack()}
                    )
                }
            }
            composable<AppRoute.WateringPlantRoute> {
                WateringScreen(
                    navigateBack = {navController.popBackStack()}
                )
            }
            navigation<AppRoute.MyGardenRootRoute>(startDestination = AppRoute.MyGardenRoute()) {
                composable<AppRoute.MyGardenRoute> {
                    MyGardenScreen(
                        viewModel = gardenViewModel,
                        navigateToDetail = {
                            navController.navigate(AppRoute.PlantDetailRoute(it))
                        },
                        navigateToAddPlant = { navController.navigate(AppRoute.AddPlantRoute) }
                    )
                }
                composable<AppRoute.AddPlantRoute> {
                    AddPlantScreen(
                        navigateBack = { navController.popBackStack() }
                    )
                }
                composable<AppRoute.PlantDetailRoute> {
                    val args = it.toRoute<AppRoute.PlantDetailRoute>()
                    PlantDetailScreen(
                        plantId = args.id,
                        navigateBack = { navController.popBackStack() }
                    )
                }
                composable<AppRoute.PlantListDetailRoute>{
                    val args = it.toRoute<AppRoute.PlantListDetailRoute>()
                    PlantListDetailScreen(
                        plantId = args.id,
                        navigateBack = {navController.popBackStack()}
                    )
                }
                composable<AppRoute.PlantSearchRoute>{
                    val args = it.toRoute<AppRoute.PlantSearchRoute>()
                    SearchScreen(
                        query = args.query,
                        navigateDetail = {
                            navController.navigate(AppRoute.PlantListDetailRoute(it))
                        },
                        navigateBack = {navController.popBackStack()}
                    )
                }
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