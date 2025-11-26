package com.example.plantcare.data.network.plantlist.model.detail

import com.google.gson.annotations.SerializedName

data class Specifications(
    @SerializedName("growth_rate") val growthRate: String?,
    @SerializedName("growth_habit") val growthHabit: String?
)
