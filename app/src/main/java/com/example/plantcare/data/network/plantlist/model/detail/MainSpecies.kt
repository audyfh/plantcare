package com.example.plantcare.data.network.plantlist.model.detail

import com.google.gson.annotations.SerializedName

data class MainSpecies(
    val genus: String?,
    val family: String?,
    @SerializedName("flower") val flower: FlowerInfo?,
    @SerializedName("foliage") val foliage: FoliageInfo?,
    @SerializedName("specifications") val specifications: Specifications?,
    @SerializedName("growth") val growth: GrowthInfo?
)
