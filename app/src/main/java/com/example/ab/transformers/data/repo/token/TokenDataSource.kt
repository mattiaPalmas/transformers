package com.example.ab.transformers.data.repo.token

interface TokenDataSource {

    suspend fun getToken(): String
}