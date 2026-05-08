package com.example.chezai.utils

import com.example.chezai.data.model.ApiResponse
import retrofit2.Response

/**
 * API 响应处理工具
 */
object ApiHelper {
    
    /**
     * 安全处理 API 响应
     */
    fun <T> handleResponse(response: Response<ApiResponse<T>>): T {
        if (response.isSuccessful) {
            val body = response.body()
            if (body?.code == 200 && body.data != null) {
                return body.data
            } else {
                throw Exception(body?.message ?: "未知错误")
            }
        } else {
            throw Exception("请求失败: ${response.code()} - ${response.message()}")
        }
    }
    
    /**
     * 安全处理列表响应
     */
    fun <T> handleListResponse(response: Response<ApiResponse<List<T>>>): List<T> {
        if (response.isSuccessful) {
            val body = response.body()
            if (body?.code == 200 && body.data != null) {
                return body.data
            } else {
                throw Exception(body?.message ?: "未知错误")
            }
        } else {
            throw Exception("请求失败: ${response.code()} - ${response.message()}")
        }
    }
}
