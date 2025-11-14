package com.example.plantcare.domain.model

import com.example.plantcare.data.network.model.Links

data class PlantDomain(
    val author: String,
    val bibliography: String,
    val common_name: String,
    val family: String?,
    val family_common_name: String,
    val genus: String,
    val genus_id: Int,
    val id: Int?,
    val image_url: String,
    val links: Links,
    val rank: String,
    val scientific_name: String,
    val slug: String,
    val status: String,
    val synonyms: List<String>,
    val year: Int?
)
