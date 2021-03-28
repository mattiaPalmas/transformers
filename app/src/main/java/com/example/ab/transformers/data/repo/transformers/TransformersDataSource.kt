package com.example.ab.transformers.data.repo.transformers

import com.example.ab.transformers.data.models.Transformer

interface TransformersDataSource {

    suspend fun getTransformers(token: String): List<Transformer>
}