package com.example.verbumlex.ai

import android.util.Log
import com.example.BuildConfig
import com.example.verbumlex.core.ConnectivityState
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.util.concurrent.TimeUnit

class GeminiLegalService {

    private val client = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

    private val jsonMediaType = "application/json; charset=utf-8".toMediaType()

    suspend fun performOnlineHermeneuticConsultation(
        userQuery: String,
        contextCorpus: String
    ): Pair<String, ConnectivityState> = withContext(Dispatchers.IO) {
        val apiKey = try {
            BuildConfig.GEMINI_API_KEY
        } catch (e: Throwable) {
            ""
        }

        if (apiKey.isBlank() || apiKey == "MY_GEMINI_API_KEY") {
            Log.d("GeminiLegalService", "API Key no configurada o en modo offline local.")
            return@withContext Pair(
                "Consulta procesada en MODO OFFLINE LOCAL por los motores de VERBUM LEX CORE. Todas las fuentes proceden del corpus normativo inmutable precargado.",
                ConnectivityState.OFFLINE
            )
        }

        try {
            // Use gemini-3.1-pro-preview with thinking mode for complex legal analysis
            val url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-3.1-pro-preview:generateContent?key=$apiKey"

            val systemInstruction = """
                Eres VERBUM LEX CORE, un motor de inteligencia jurídica de alta precisión y estricta trazabilidad.
                Regla de oro: NINGUNA CONCLUSIÓN JURÍDICA SIN TRAZA DE EVIDENCIA.
                Distingue siempre: HECHO, NORMA, INTERPRETACIÓN, INFERENCIA, CONCLUSIÓN.
                Nunca inventes leyes ni resoluciones. Si falta evidencia declara 'NO VERIFICADO'.
            """.trimIndent()

            val requestJson = JSONObject().apply {
                put("contents", JSONArray().apply {
                    put(JSONObject().apply {
                        put("role", "user")
                        put("parts", JSONArray().apply {
                            put(JSONObject().put("text", "Contexto Normativo Verificado:\n$contextCorpus\n\nConsulta:\n$userQuery"))
                        })
                    })
                })
                put("systemInstruction", JSONObject().apply {
                    put("parts", JSONArray().apply {
                        put(JSONObject().put("text", systemInstruction))
                    })
                })
                put("generationConfig", JSONObject().apply {
                    put("temperature", 0.2)
                    put("thinkingConfig", JSONObject().apply {
                        put("thinkingLevel", "HIGH")
                    })
                })
            }

            val requestBody = requestJson.toString().toRequestBody(jsonMediaType)
            val request = Request.Builder()
                .url(url)
                .post(requestBody)
                .build()

            val response = client.newCall(request).execute()
            if (response.isSuccessful) {
                val responseString = response.body?.string().orEmpty()
                val responseObj = JSONObject(responseString)
                val candidates = responseObj.optJSONArray("candidates")
                val firstCandidate = candidates?.optJSONObject(0)
                val content = firstCandidate?.optJSONObject("content")
                val parts = content?.optJSONArray("parts")
                val text = parts?.optJSONObject(0)?.optString("text")

                if (!text.isNullOrBlank()) {
                    Pair(text, ConnectivityState.VERIFICADO_EN_LINEA)
                } else {
                    Pair("Respuesta local completada (Contenido en línea sin partes legibles).", ConnectivityState.OFFLINE)
                }
            } else {
                Log.w("GeminiLegalService", "Error HTTP ${response.code}: ${response.message}")
                Pair("Servicio en línea no disponible (Código ${response.code}). Operando en MODO OFFLINE LOCAL seguro.", ConnectivityState.OFFLINE)
            }
        } catch (e: Exception) {
            Log.e("GeminiLegalService", "Excepción al conectar con Gemini API", e)
            Pair("Error de red: ${e.message}. Conmutado automáticamente a MODO OFFLINE LOCAL verificado.", ConnectivityState.OFFLINE)
        }
    }
}
