package com.example.chezai.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.chezai.data.model.CallInfo
import com.example.chezai.repository.Repository

/**
 * 通话 ViewModel
 */
class CallViewModel : BaseViewModel() {
    
    private val _callInfo = MutableLiveData<CallInfo>()
    val callInfo: LiveData<CallInfo> = _callInfo
    
    /**
     * 获取通话信息
     */
    fun getCallInfo() {
        safeApiCall(
            apiCall = {
                Repository.getCallInfo()
            },
            onSuccess = { info ->
                _callInfo.value = info
            }
        )
    }
}
