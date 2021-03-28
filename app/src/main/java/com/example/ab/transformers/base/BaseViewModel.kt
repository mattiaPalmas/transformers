package com.example.ab.transformers.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

abstract class BaseViewModel : ViewModel() {

    private val _showErrorLiveData = MutableLiveData<String>()
    val showErrorLiveData: LiveData<String>
        get() = _showErrorLiveData

    protected fun <T> launchBackgroundJob(
        backgroundJob: suspend () -> T,
        onSuccess: (T) -> Unit = {},
        onError: (Throwable) -> Unit = ::showError
    ): Job = viewModelScope.launch {
        kotlin.runCatching {
            coroutineScope {
                backgroundJob.invoke()
            }
        }
            .onSuccess { result -> onSuccess.invoke(result) }
            .onFailure { error -> onError.invoke(error) }
    }

    protected fun showError(error: Throwable) {
        _showErrorLiveData.value = error.message
    }
}