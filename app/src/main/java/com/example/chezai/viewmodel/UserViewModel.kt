package com.example.chezai.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * 用户 ViewModel
 */
class UserViewModel : BaseViewModel() {
    
    private val _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn: LiveData<Boolean> = _isLoggedIn
    
    private val _username = MutableLiveData<String>()
    val username: LiveData<String> = _username
    
    /**
     * 用户登录
     */
    fun login(phone: String, password: String) {
        safeApiCall(
            apiCall = {
                // ApiClient.apiService.login(phone, password)
                true
            },
            onSuccess = { success ->
                _isLoggedIn.value = success
                if (success) {
                    _username.value = "用户"
                }
            }
        )
    }
    
    /**
     * 用户登出
     */
    fun logout() {
        _isLoggedIn.value = false
        _username.value = null
    }
}
