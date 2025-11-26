package com.example.plantcare.data.network.plantlist

import com.example.plantcare.data.network.plantlist.model.Links
import com.example.plantcare.data.network.plantlist.model.PlantData
import com.example.plantcare.data.network.plantlist.model.detail.FlowerInfo
import com.example.plantcare.data.network.plantlist.model.detail.FoliageInfo
import com.example.plantcare.data.network.plantlist.model.detail.GrowthInfo
import com.example.plantcare.data.network.plantlist.model.detail.MainSpecies
import com.example.plantcare.data.network.plantlist.model.detail.PlantDetail
import com.example.plantcare.data.network.plantlist.model.detail.Specifications
import com.example.plantcare.domain.model.PlantDetailDomain
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

fun PlantDetail.toDomain(): PlantDetailDomain {
    return PlantDetailDomain(
        id = id,
        commonName = commonName ?: "",
        scientificName = scientificName ?: "",
        imageUrl = imageUrl ?: "",
        mainSpecies = MainSpecies(
            genus = mainSpecies?.genus ?: "",
            family = mainSpecies?.family ?: "",
            flower = mainSpecies?.flower ?: FlowerInfo(color = emptyList(), conspicuous = false),
            foliage = mainSpecies?.foliage ?: FoliageInfo(color = emptyList(), texture = ""),
            specifications = mainSpecies?.specifications ?: Specifications(growthRate = "", growthHabit = ""),
            growth = mainSpecies?.growth ?: GrowthInfo(
                light = 0,
                humidity = 0,
                soilNutriments = 0,
                minTemp = null,
                maxTemp = null
            )
        )
    )
}