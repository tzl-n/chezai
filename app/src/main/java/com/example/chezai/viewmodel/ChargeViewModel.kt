package com.example.chezai.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.chezai.data.model.ChargingInfo
import com.example.chezai.repository.Repository

/**
 * 充电 ViewModel
 */
class ChargeViewModel : BaseViewModel() {
    
    private val _chargingInfo = MutableLiveData<ChargingInfo?>()
    val chargingInfo: LiveData<ChargingInfo?> = _chargingInfo
    
    private val _chargingStatus = MutableLiveData<String>()
    val chargingStatus: LiveData<String> = _chargingStatus
    
    private val _batteryLevel = MutableLiveData<Int>()
    val batteryLevel: LiveData<Int> = _batteryLevel
    
    /**
     * 获取充电信息
     */
    fun getChargingInfo() {
        safeApiCall(
            apiCall = {
                Repository.getChargingInfo()
            },
            onSuccess = { info ->
                _chargingInfo.value = info
                _chargingStatus.value = if (info.isCharging) "充电中" else "未充电"
                _batteryLevel.value = info.batteryLevel
            }
        )
    }
    
    /**
     * 开始充电
     */
    fun startCharging() {
        safeApiCall(
            apiCall = {
                // 根据实际 API 调整
                true
            },
            onSuccess = { _ ->
                _chargingStatus.value = "充电中"
                getChargingInfo()
            }
        )
    }
    
    /**
     * 停止充电
     */
    fun stopCharging() {
        safeApiCall(
            apiCall = {
                // 根据实际 API 调整
                true
            },
            onSuccess = { _ ->
                _chargingStatus.value = "已停止"
                getChargingInfo()
            }
        )
    }
}
