package com.example.ab.transformers.screens.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ab.transformers.base.BaseViewModel
import com.example.ab.transformers.data.models.Transformer
import com.example.ab.transformers.data.repo.token.TokenRepo
import com.example.ab.transformers.data.repo.transformers.TransformersRepo
import kotlinx.coroutines.coroutineScope

class MainViewModel(
    private val tokenRepo: TokenRepo,
    private val transformersRepo: TransformersRepo
) : BaseViewModel() {

    private val _transformersLiveData = MutableLiveData<List<Transformer>>()
    val transformersLiveData: LiveData<List<Transformer>>
        get() = _transformersLiveData

    private val _transformerSavedLiveData = MutableLiveData<Boolean>()
    val transformerSavedLiveData: LiveData<Boolean>
        get() = _transformerSavedLiveData

    fun getTransformers() {
        launchBackgroundJob(
            backgroundJob = {
                val token = tokenRepo.getToken()

                transformersRepo.getTransformers(token)
            },
            onSuccess = {
                _transformersLiveData.postValue(it)
            },
            onError = {
                showError(it)
            }
        )
    }

    fun saveNewTransformer(transformer: Transformer) {
        launchBackgroundJob(
            backgroundJob = {
                val token = tokenRepo.getToken()

                transformersRepo.saveTransformer(token, transformer)
            },
            onSuccess = {
                _transformerSavedLiveData.postValue(true)
            },
            onError = {
                showError(it)
            }
        )
    }
}