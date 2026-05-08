package com.example.chezai.network

import com.example.chezai.data.model.*
import retrofit2.Response
import retrofit2.http.*

/**
 * API 接口定义
 */
interface ApiService {
    
    /**
     * 获取所有数据
     */
    @GET("api/all")
    suspend fun getAllData(): Response<ApiResponse<Map<String, Any>>>
    
    /**
     * 获取车辆状态
     */
    @GET("api/vehicle")
    suspend fun getVehicleStatus(): Response<ApiResponse<CarInfo>>
    
    /**
     * 获取充电信息
     */
    @GET("api/charging")
    suspend fun getChargingInfo(): Response<ApiResponse<ChargingInfo>>
    
    /**
     * 获取钥匙信息
     */
    @GET("api/keys")
    suspend fun getKeys(): Response<ApiResponse<List<KeyInfo>>>
    
    /**
     * 控制空调
     */
    @POST("api/hvac")
    suspend fun controlHVAC(@Body request: HVACRequest): Response<ApiResponse<HVACResponse>>
    
    /**
     * 系统升级
     */
    @POST("api/ota")
    suspend fun updateOTA(@Body request: OTARequest): Response<ApiResponse<OTAResponse>>
    
    /**
     * 获取通话信息
     */
    @GET("api/call")
    suspend fun getCallInfo(): Response<ApiResponse<CallInfo>>
    
    /**
     * 获取安全日志
     */
    @GET("api/security-logs")
    suspend fun getSecurityLogs(): Response<ApiResponse<List<SecurityLog>>>
}
