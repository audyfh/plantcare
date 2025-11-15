package com.example.plantcare.di

import com.example.plantcare.presentation.home.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
   viewModelOf(::HomeViewModel)
}