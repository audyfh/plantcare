package com.example.plantcare.data.network.plantlist.model.detail

import com.google.gson.annotations.SerializedName

data class PlantDetail(
    val id: Int,
    @SerializedName("common_name") val commonName: String?,
    @SerializedName("scientific_name") val scientificName: String?,
    @SerializedName("image_url") val imageUrl: String?,
    @SerializedName("main_species") val mainSpecies: MainSpecies?,

)
