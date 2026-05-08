package com.example.chezai.repository

import com.example.chezai.data.model.*
import com.example.chezai.network.ApiClient
import com.example.chezai.utils.ApiHelper

/**
 * 数据仓库层
 * 负责整合所有 API 调用，提供统一的数据访问接口
 */
object Repository {
    
    private val apiService = ApiClient.apiService
    
    // ==================== 车辆相关 ====================
    
    /**
     * 获取所有数据
     */
    suspend fun getAllData(): Map<String, Any> {
        val response = apiService.getAllData()
        return ApiHelper.handleResponse(response)
    }
    
    /**
     * 获取车辆状态
     */
    suspend fun getVehicleStatus(): CarInfo {
        val response = apiService.getVehicleStatus()
        return ApiHelper.handleResponse(response)
    }
    
    // ==================== 充电相关 ====================
    
    /**
     * 获取充电信息
     */
    suspend fun getChargingInfo(): ChargingInfo {
        val response = apiService.getChargingInfo()
        return ApiHelper.handleResponse(response)
    }
    
    // ==================== 钥匙相关 ====================
    
    /**
     * 获取钥匙列表
     */
    suspend fun getKeys(): List<KeyInfo> {
        val response = apiService.getKeys()
        return ApiHelper.handleListResponse(response)
    }
    
    // ==================== 空调控制 ====================
    
    /**
     * 控制空调
     */
    suspend fun controlHVAC(action: String, temperature: Int? = null, mode: String? = null): HVACResponse {
        val request = HVACRequest(action, temperature, mode)
        val response = apiService.controlHVAC(request)
        return ApiHelper.handleResponse(response)
    }
    
    // ==================== OTA 升级 ====================
    
    /**
     * 执行 OTA 升级
     */
    suspend fun updateOTA(version: String, forceUpdate: Boolean = false): OTAResponse {
        val request = OTARequest(version, forceUpdate)
        val response = apiService.updateOTA(request)
        return ApiHelper.handleResponse(response)
    }
    
    // ==================== 通话相关 ====================
    
    /**
     * 获取通话信息
     */
    suspend fun getCallInfo(): CallInfo {
        val response = apiService.getCallInfo()
        return ApiHelper.handleResponse(response)
    }
    
    // ==================== 安全日志 ====================
    
    /**
     * 获取安全日志
     */
    suspend fun getSecurityLogs(): List<SecurityLog> {
        val response = apiService.getSecurityLogs()
        return ApiHelper.handleListResponse(response)
    }
}
