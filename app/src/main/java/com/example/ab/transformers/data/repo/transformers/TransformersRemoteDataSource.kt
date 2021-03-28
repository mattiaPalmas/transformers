package com.example.ab.transformers.data.repo.transformers

import com.example.ab.transformers.data.models.Transformer
import com.example.ab.transformers.data.rest.TransformersEndpoints

class TransformersRemoteDataSource(
    private val endpoints: TransformersEndpoints
    ) : TransformersDataSource {

    override suspend fun getTransformers(token: String): List<Transformer> {
        return endpoints.getTransformers(token)
    }
}