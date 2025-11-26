package com.example.plantcare.domain.model

import com.example.plantcare.data.network.plantlist.model.detail.FlowerInfo
import com.example.plantcare.data.network.plantlist.model.detail.FoliageInfo
import com.example.plantcare.data.network.plantlist.model.detail.GrowthInfo
import com.example.plantcare.data.network.plantlist.model.detail.MainSpecies
import com.example.plantcare.data.network.plantlist.model.detail.Specifications

data class PlantDetailDomain(
    val id: Int,
    val commonName: String,
    val scientificName: String,
    val imageUrl: String,
    val mainSpecies: MainSpecies
)
