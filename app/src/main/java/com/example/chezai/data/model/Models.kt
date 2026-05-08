package com.example.chezai.data.model

/**
 * 通用响应数据类
 */
data class ApiResponse<T>(
    val code: Int,
    val message: String,
    val data: T?
)

/**
 * 车辆信息
 */
data class CarInfo(
    val id: String,
    val plateNumber: String,
    val model: String,
    val batteryLevel: Int,
    val fuelLevel: Int,
    val range: Int,
    val status: String,
    val speed: Int = 0,
    val mileage: Int = 0,
    val engineStatus: String = "关闭",
    val lockStatus: String = "已锁"
)

/**
 * 充电信息
 */
data class ChargingInfo(
    val isCharging: Boolean,
    val batteryLevel: Int,
    val chargingPower: Int,
    val estimatedTime: Int,
    val temperature: Int
)

/**
 * 钥匙信息
 */
data class KeyInfo(
    val id: String,
    val name: String,
    val type: String,
    val isActive: Boolean,
    val lastUsed: String
)

/**
 * 空调控制请求
 */
data class HVACRequest(
    val action: String,
    val temperature: Int?,
    val mode: String?
)

/**
 * 空调控制响应
 */
data class HVACResponse(
    val success: Boolean,
    val currentTemp: Int,
    val targetTemp: Int,
    val mode: String
)

/**
 * OTA升级请求
 */
data class OTARequest(
    val version: String,
    val forceUpdate: Boolean
)

/**
 * OTA升级响应
 */
data class OTAResponse(
    val success: Boolean,
    val downloadProgress: Int,
    val status: String
)

/**
 * 通话信息
 */
data class CallInfo(
    val phoneNumber: String,
    val callType: String,
    val duration: Int,
    val timestamp: String
)

/**
 * 安全日志
 */
data class SecurityLog(
    val id: String,
    val type: String,
    val description: String,
    val timestamp: String,
    val location: String
)

/**
 * 车辆控制请求
 */
data class CarControlRequest(
    val carId: String,
    val action: String,
    val value: Any?
)

/**
 * 用户信息
 */
data class UserInfo(
    val userId: String,
    val username: String,
    val phone: String,
    val avatar: String
)
