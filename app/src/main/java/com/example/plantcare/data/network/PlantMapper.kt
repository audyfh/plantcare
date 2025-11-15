package com.example.plantcare.data.network

import com.example.plantcare.data.network.model.Links
import com.example.plantcare.data.network.model.PlantData
import com.example.plantcare.domain.model.PlantDomain

fun PlantData.toDomain(): PlantDomain {
    return PlantDomain(
        common_name = common_name ?: "",
        scientific_name = scientific_name ?: "",
        image_url = image_url ?: "",
        author = author ?: "",
        bibliography = bibliography ?: "",
        family = family ?: "",
        family_common_name = family_common_name ?: "",
        genus = genus ?: "",
        genus_id = genus_id ?: 0,
        id = id ?: 0,
        links = links ?: Links(
            first = "",
            last = "",
            next = "",
            prev = "",
            self = ""
        ),
        rank = rank ?: "",
        slug = slug ?: "",
        status = status ?: "",
        synonyms = synonyms ?: emptyList(),
        year = year ?: 0
    )
}
