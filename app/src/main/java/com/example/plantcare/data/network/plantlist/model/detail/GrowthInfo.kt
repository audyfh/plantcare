package com.example.plantcare.data.network.plantlist.model.detail

import com.google.gson.annotations.SerializedName

data class GrowthInfo(
    val light: Int?,
    @SerializedName("atmospheric_humidity") val humidity: Int?,
    @SerializedName("soil_nutriments") val soilNutriments: Int?,
    @SerializedName("minimum_temperature") val minTemp: TemperatureInfo?,
    @SerializedName("maximum_temperature") val maxTemp: TemperatureInfo?
)
