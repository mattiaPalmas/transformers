package com.example.ab.transformers.data.rest

import com.example.ab.transformers.data.models.Transformer
import retrofit2.http.GET
import retrofit2.http.Header

interface TransformersEndpoints {

    @GET("allspark")
    suspend fun getToken(): String

    @GET("transformers")
    suspend fun getTransformers(
        @Header("Authorization") token: String
    ): List<Transformer>
}