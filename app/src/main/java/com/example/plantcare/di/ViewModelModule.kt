package com.example.plantcare.di

import com.example.plantcare.presentation.ai.diagnose.DiagnosisViewModel
import com.example.plantcare.presentation.ai.identify.IdentifyPlantViewModel
import com.example.plantcare.presentation.home.HomeViewModel
import com.example.plantcare.presentation.home.search.SearchViewModel
import com.example.plantcare.presentation.home.watering.WateringViewModel
import com.example.plantcare.presentation.mygarden.MyGardenViewModel
import com.example.plantcare.presentation.mygarden.addplant.AddPlantViewModel
import com.example.plantcare.presentation.mygarden.plantdetail.PlantDetailViewModel
import com.example.plantcare.presentation.plantlist.PlantListViewModel
import com.example.plantcare.presentation.plantlist.detail.PlantListDetailViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
   viewModelOf(::HomeViewModel)
   viewModelOf(::PlantListViewModel)
   viewModelOf(::MyGardenViewModel)
   viewModelOf(::AddPlantViewModel)
   viewModelOf(::PlantDetailViewModel)
   viewModelOf(::WateringViewModel)
   viewModelOf(::PlantListDetailViewModel)
   viewModelOf(::SearchViewModel)
   viewModelOf(::IdentifyPlantViewModel)
   viewModelOf(::DiagnosisViewModel)
}