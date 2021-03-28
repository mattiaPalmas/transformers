package com.example.ab.transformers.data.repo.token

import android.content.SharedPreferences
import com.example.ab.transformers.Constants.KEY_TOKEN
import com.example.ab.transformers.data.rest.TransformersEndpoints

class TokenRepo(
    private val sharedPrefs: SharedPreferences,
    private val endpoints: TransformersEndpoints
) : TokenDataSource {

    override suspend fun getToken(): String {
        val localToken = sharedPrefs.getString(KEY_TOKEN, null)

        return if (!localToken.isNullOrBlank()) {
            localToken
        } else {
            endpoints.getToken()
        }
    }
}