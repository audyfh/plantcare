package com.example.plantcare.domain.model

import com.example.plantcare.data.network.model.Links

data class PlantDomain(
    val author: String,
    val bibliography: String,
    val common_name: String,
    val family: String,
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
    val year: Int
)

val dummyPlant = listOf(
    PlantDomain(
        author = "L.",
        bibliography = "Species Plantarum 1753",
        common_name = "Sunflower",
        family = "Asteraceae",
        family_common_name = "Daisy family",
        genus = "Helianthus",
        genus_id = 123,
        id = 1,
        image_url = "https://bs.plantnet.org/image/o/63073d2fbf45b90701279405ecc2eec0272906ed",
        links = Links(
            first = "/api/plants/1",
            last = "/api/plants/1",
            next = "/api/plants/2",
            prev = "",
            self = "/api/plants/1"
        ),
        rank = "species",
        scientific_name = "Helianthus annuus",
        slug = "helianthus-annuus",
        status = "accepted",
        synonyms = listOf("https://bs.plantnet.org/image/o/63073d2fbf45b90701279405ecc2eec0272906ed"),
        year = 1753
    ),

    PlantDomain(
        author = "Mill.",
        bibliography = "Gard. Dict. ed. 8",
        common_name = "Aloe Vera",
        family = "Asphodelaceae",
        family_common_name = "Aloe family",
        genus = "Aloe",
        genus_id = 124,
        id = 2,
        image_url = "https://bs.plantnet.org/image/o/63073d2fbf45b90701279405ecc2eec0272906ed",
        links = Links(
            first = "/api/plants/2",
            last = "/api/plants/2",
            next = "/api/plants/3",
            prev = "/api/plants/1",
            self = "/api/plants/2"
        ),
        rank = "species",
        scientific_name = "Aloe vera",
        slug = "aloe-vera",
        status = "accepted",
        synonyms = listOf("Aloe barbadensis"),
        year = 1768
    ),

    PlantDomain(
        author = "Thunb.",
        bibliography = "Flora Japonica",
        common_name = "Sakura",
        family = "Rosaceae",
        family_common_name = "Rose family",
        genus = "Prunus",
        genus_id = 125,
        id = 3,
        image_url = "https://bs.plantnet.org/image/o/63073d2fbf45b90701279405ecc2eec0272906ed",
        links = Links(
            first = "/api/plants/3",
            last = "/api/plants/3",
            next = "/api/plants/4",
            prev = "/api/plants/2",
            self = "/api/plants/3"
        ),
        rank = "species",
        scientific_name = "Prunus serrulata",
        slug = "prunus-serrulata",
        status = "accepted",
        synonyms = listOf("Cerasus serrulata"),
        year = 1784
    ),

    PlantDomain(
        author = "F.Muell.",
        bibliography = "Fragmenta Phytographiae Australiae",
        common_name = "Eucalyptus",
        family = "Myrtaceae",
        family_common_name = "Myrtle family",
        genus = "Eucalyptus",
        genus_id = 126,
        id = 4,
        image_url = "https://bs.plantnet.org/image/o/63073d2fbf45b90701279405ecc2eec0272906ed",
        links = Links(
            first = "/api/plants/4",
            last = "/api/plants/4",
            next = "/api/plants/5",
            prev = "/api/plants/3",
            self = "/api/plants/4"
        ),
        rank = "species",
        scientific_name = "Eucalyptus globulus",
        slug = "eucalyptus-globulus",
        status = "accepted",
        synonyms = listOf("Eucalyptus maidenii"),
        year = 1858
    ),

    PlantDomain(
        author = "Lam.",
        bibliography = "Encyclopédie Méthodique",
        common_name = "Lavender",
        family = "Lamiaceae",
        family_common_name = "Mint family",
        genus = "Lavandula",
        genus_id = 127,
        id = 5,
        image_url = "https://bs.plantnet.org/image/o/63073d2fbf45b90701279405ecc2eec0272906ed",
        links = Links(
            first = "/api/plants/5",
            last = "/api/plants/5",
            next = "",
            prev = "/api/plants/4",
            self = "/api/plants/5"
        ),
        rank = "species",
        scientific_name = "Lavandula angustifolia",
        slug = "lavandula-angustifolia",
        status = "accepted",
        synonyms = listOf("Lavandula officinalis"),
        year = 1783
    )
)

