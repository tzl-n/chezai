package com.example.chezai.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.chezai.data.model.SecurityLog
import com.example.chezai.repository.Repository

/**
 * 安全日志 ViewModel
 */
class SecurityViewModel : BaseViewModel() {
    
    private val _securityLogs = MutableLiveData<List<SecurityLog>>()
    val securityLogs: LiveData<List<SecurityLog>> = _securityLogs
    
    /**
     * 获取安全日志
     */
    fun getSecurityLogs() {
        safeApiCall(
            apiCall = {
                Repository.getSecurityLogs()
            },
            onSuccess = { logs ->
                _securityLogs.value = logs
            }
        )
    }
}
