package com.example.plantcare.data.network.ai


import android.content.Context
import android.net.Uri
import android.util.Log
import com.example.plantcare.domain.model.PlantDiagnosis
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

    override suspend fun diagnosePlant(
        context: Context,
        image: Uri
    ): Resource<PlantDiagnosis> {
        val prompt = """
You are an expert plant pathologist. Analyze the plant in the image and diagnose its health condition.

Your task:
1. Determine whether the plant is healthy or not.
2. If unhealthy, identify the most likely disease, nutrient deficiency, pest attack, fungus, rot, or environmental stress.
3. Provide a short but clear explanation.

Output Rules:
- Your entire response MUST be valid JSON only.
- Do NOT include any explanations outside JSON.
- Use this structure exactly:

{
  "health_status": "...",
  "diagnosis_result": "..."
}

Where:
- health_status = "healthy" or "unhealthy"
- diagnosis_result = explanation (max 3–4 sentences)

Start your analysis now.
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
                val json = Utility.extractJson(it)
                val diagnosis = Json.decodeFromString<PlantDiagnosis>(json)
                return Resource.Success(diagnosis)

            }
        } catch (e: Exception) {
            return Resource.Error(e.message.toString())
        }
        return Resource.Error("Unknown Error")
    }
}






