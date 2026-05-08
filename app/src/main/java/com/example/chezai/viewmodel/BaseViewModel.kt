package com.example.chezai.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * 基础 ViewModel
 */
open class BaseViewModel : ViewModel() {
    
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage
    
    protected fun showLoading() {
        _isLoading.value = true
    }
    
    protected fun hideLoading() {
        _isLoading.value = false
    }
    
    protected fun showError(message: String) {
        _errorMessage.value = message
    }
    
    protected fun <T> safeApiCall(apiCall: suspend () -> T, onSuccess: (T) -> Unit) {
        viewModelScope.launch {
            try {
                showLoading()
                val result = apiCall()
                onSuccess(result)
            } catch (e: Exception) {
                showError(e.message ?: "未知错误")
            } finally {
                hideLoading()
            }
        }
    }
}
