package com.example.chezai.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.chezai.data.model.OTAResponse
import com.example.chezai.repository.Repository

/**
 * OTA 升级 ViewModel
 */
class OTAViewModel : BaseViewModel() {
    
    private val _otaResponse = MutableLiveData<OTAResponse>()
    val otaResponse: LiveData<OTAResponse> = _otaResponse
    
    private val _updateProgress = MutableLiveData<Int>()
    val updateProgress: LiveData<Int> = _updateProgress
    
    /**
     * 执行 OTA 升级
     */
    fun updateOTA(version: String, forceUpdate: Boolean = false) {
        safeApiCall(
            apiCall = {
                Repository.updateOTA(version, forceUpdate)
            },
            onSuccess = { response ->
                _otaResponse.value = response
                _updateProgress.value = response.downloadProgress
            }
        )
    }
}
