package com.example.chezai.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.chezai.data.model.CarInfo
import com.example.chezai.repository.Repository

/**
 * 车辆 ViewModel
 */
class CarViewModel : BaseViewModel() {
    
    private val _carInfo = MutableLiveData<CarInfo?>()
    val carInfo: LiveData<CarInfo?> = _carInfo
    
    private val _isLocked = MutableLiveData<Boolean>()
    val isLocked: LiveData<Boolean> = _isLocked
    
    /**
     * 获取车辆信息
     */
    fun getCarInfo() {
        safeApiCall(
            apiCall = {
                Repository.getVehicleStatus()
            },
            onSuccess = { carInfo ->
                _carInfo.value = carInfo
                _isLocked.value = carInfo.lockStatus == "已锁"
            }
        )
    }
    
    /**
     * 控制车锁
     */
    fun toggleLock(lock: Boolean) {
        safeApiCall(
            apiCall = {
                // 根据实际 API 调整
                lock
            },
            onSuccess = { success ->
                _isLocked.value = success
            }
        )
    }
}
