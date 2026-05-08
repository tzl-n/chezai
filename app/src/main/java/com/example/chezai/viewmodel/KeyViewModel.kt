package com.example.chezai.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.chezai.data.model.KeyInfo
import com.example.chezai.repository.Repository

/**
 * 钥匙 ViewModel
 */
class KeyViewModel : BaseViewModel() {
    
    private val _keys = MutableLiveData<List<KeyInfo>>()
    val keys: LiveData<List<KeyInfo>> = _keys
    
    /**
     * 获取钥匙列表
     */
    fun getKeys() {
        safeApiCall(
            apiCall = {
                Repository.getKeys()
            },
            onSuccess = { keyList ->
                _keys.value = keyList
            }
        )
    }
}
