package com.example.plantcare.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface AppRoute {

    @Serializable
    data object HomeRoute : AppRoute
    @Serializable
    data object PlantListRoute : AppRoute

    @Serializable
    data object MyGardenRootRoute : AppRoute
    @Serializable
    data class MyGardenRoute(
        val selectedTab : String = "My Plants"
    ) : AppRoute

    @Serializable
    data object AiRootRoute : AppRoute
    @Serializable
    data object AiRoute : AppRoute

    @Serializable
    data object IdentifyRoute : AppRoute

    @Serializable
    data object AddPlantRoute : AppRoute

    @Serializable
    data object DiagnoseRoute : AppRoute

    @Serializable
    data object DiagnoseResultRoute : AppRoute

    @Serializable
    data object WateringPlantRoute : AppRoute

    @Serializable
    data class PlantDetailRoute(
        val id : Int
    ) : AppRoute

    @Serializable
    data class PlantListDetailRoute(
        val id : Int
    ) : AppRoute

    @Serializable
    data class PlantSearchRoute(
        val query : String
    ) : AppRoute
}