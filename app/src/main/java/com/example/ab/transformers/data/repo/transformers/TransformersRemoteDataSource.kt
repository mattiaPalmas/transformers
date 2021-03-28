package com.example.ab.transformers.data.repo.transformers

import com.example.ab.transformers.data.models.Transformer
import com.example.ab.transformers.data.models.TransformerRequest
import com.example.ab.transformers.data.rest.TransformersEndpoints

class TransformersRemoteDataSource(
    private val endpoints: TransformersEndpoints
    ) : TransformersDataSource {

    override suspend fun getTransformers(token: String): List<Transformer> {
        return endpoints.getTransformers(token)
    }

    override suspend fun saveTransformer(token: String, transformer: Transformer) {
        val transformerRequest = TransformerRequest(
            transformer.id,
            transformer.name,
            transformer.team,
            transformer.strength,
            transformer.intelligence,
            transformer.speed,
            transformer.endurance,
            transformer.rank,
            transformer.courage,
            transformer.firepower,
            transformer.skill
        )
        return endpoints.saveTransformer("Bearer $token", transformerRequest)
    }
}