package com.example.ab.transformers.data.repo.transformers

import com.example.ab.transformers.data.models.Transformer

class TransformersRepo(
    private val remoteDataSource: TransformersDataSource
) : TransformersDataSource{

    override suspend fun getTransformers(token: String): List<Transformer> {
        return remoteDataSource.getTransformers(token)
    }

}