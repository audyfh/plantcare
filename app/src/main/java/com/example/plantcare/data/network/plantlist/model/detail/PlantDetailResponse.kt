package com.example.plantcare.data.network.plantlist.model.detail

import com.example.plantcare.data.network.plantlist.model.Links
import com.example.plantcare.data.network.plantlist.model.Meta

data class PlantDetailResponse(
    val data : PlantDetail,
    val links : Links,
    val meta : Meta
)
