package com.example.chezai.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.chezai.data.model.HVACResponse
import com.example.chezai.repository.Repository

/**
 * 空调控制 ViewModel
 */
class HVACViewModel : BaseViewModel() {
    
    private val _hvacResponse = MutableLiveData<HVACResponse>()
    val hvacResponse: LiveData<HVACResponse> = _hvacResponse
    
    /**
     * 控制空调
     */
    fun controlHVAC(action: String, temperature: Int? = null, mode: String? = null) {
        safeApiCall(
            apiCall = {
                Repository.controlHVAC(action, temperature, mode)
            },
            onSuccess = { response ->
                _hvacResponse.value = response
            }
        )
    }
}
