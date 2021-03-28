package com.example.ab.transformers.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseViewModel : ViewModel() {

    private val _showProgressLiveData = MutableLiveData<Boolean>()
    val showProgressLiveData: LiveData<Boolean>
        get() = _showProgressLiveData

    private val _showErrorLiveData = MutableLiveData<String>()
    val showErrorLiveData: LiveData<String>
        get() = _showErrorLiveData

    protected fun <T> launchBackgroundJob(
        backgroundJob: suspend () -> T,
        onSuccess: (T) -> Unit = {},
        onError: (Throwable) -> Unit = ::showError,
        progressBarEnabled: Boolean = true,
        runOnDispatcher: CoroutineDispatcher? = null
    ): Job = viewModelScope.launch {
        if (progressBarEnabled) showProgress()

        kotlin.runCatching {
            if (runOnDispatcher != null) {
                withContext(runOnDispatcher) {
                    backgroundJob.invoke()
                }
            } else {
                backgroundJob.invoke()
            }
        }
            .onSuccess { result -> onSuccess.invoke(result) }
            .onFailure { error -> onError.invoke(error) }

        if (progressBarEnabled) hideProgress()
    }

    protected fun showError(error: Throwable) {
        _showErrorLiveData.value = error.message
    }

    protected fun showProgress() {
        _showProgressLiveData.value = true
    }

    protected fun hideProgress() {
        _showProgressLiveData.value = false
    }
}