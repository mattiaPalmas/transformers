package com.example.ab.transformers.data.rest

import com.example.ab.transformers.data.models.Transformer
import com.example.ab.transformers.data.models.TransformerRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface TransformersEndpoints {

    @GET("allspark")
    suspend fun getToken(): String

    @GET("transformers")
    suspend fun getTransformers(
        @Header("Authorization") token: String
    ): List<Transformer>

    @POST("transformers")
    suspend fun saveTransformer(
        @Header("Authorization") token: String,
        @Body transformerRequest: TransformerRequest
    )
}