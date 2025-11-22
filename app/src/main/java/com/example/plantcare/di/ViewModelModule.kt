package com.example.plantcare.di

import com.example.plantcare.presentation.home.HomeViewModel
import com.example.plantcare.presentation.mygarden.MyGardenViewModel
import com.example.plantcare.presentation.plantlist.PlantListViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
   viewModelOf(::HomeViewModel)
   viewModelOf(::PlantListViewModel)
   viewModelOf(::MyGardenViewModel)
}