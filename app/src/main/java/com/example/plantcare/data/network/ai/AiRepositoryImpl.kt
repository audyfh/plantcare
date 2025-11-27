package com.example.plantcare.data.network.ai


import android.content.Context
import android.net.Uri
import android.util.Log
import com.example.plantcare.domain.model.PlantIdentify
import com.example.plantcare.domain.repository.AiRepository
import com.example.plantcare.util.Resource
import com.example.plantcare.util.Utility
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNull.content

class AiRepositoryImpl(
    private val gemini: GenerativeModel
) : AiRepository {
    override suspend fun identifyPlant(
        context: Context,
        image: Uri
    ): Resource<PlantIdentify> {
        val prompt = """
You are a plant identification assistant.

You will be given a photo of a plant (leaf, flower, stem, or whole plant).
Your task:
1. Identify the plant as accurately as possible.
2. If you are not sure, pick the closest common match BUT mention the uncertainty in the description.

Respond ONLY in valid JSON with this exact structure (no extra text, no explanation, no markdown):

{
  "name": "<common name in English if available, otherwise local common name>",
  "scientific_name": "<binomial latin name, e.g. 'Ficus elastica'>",
  "family": "<plant family, e.g. 'Moraceae'>",
  "genus": "<genus, e.g. 'Ficus'>",
  "description": "<short description about the plant characteristics and basic care tips (light, watering, and environment)>"
}

If you really cannot identify the plant at all, use:
- "name": "Unknown"
- "scientific_name": "Unknown"
- "family": "Unknown"
- "genus": "Unknown"
- "description": "Plant cant be identified from this photo"
""".trimIndent()
        try {
            val bitmap = Utility.uriToBitmap(context, image)
            val response = gemini.generateContent(
                content {
                    image(bitmap!!)
                    text(prompt)
                }
            )
            response.text?.let {
                Log.d("AI", "identifyPlant: $it")
                val json = Utility.extractJson(it)
                Log.d("AI", "identifyPlant: $json")
                val plant = Json.decodeFromString<PlantIdentify>(json)
                return Resource.Success(plant)

            }
        } catch (e: Exception) {
            return Resource.Error(e.message.toString())
        }
        return Resource.Error("Unknown Error")
    }
}






