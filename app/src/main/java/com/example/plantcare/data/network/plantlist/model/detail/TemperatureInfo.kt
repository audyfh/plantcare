package com.example.plantcare.data.network.plantlist.model.detail

import com.google.gson.annotations.SerializedName

data class TemperatureInfo(
   @SerializedName("deg_c") val degC: Double?
)
