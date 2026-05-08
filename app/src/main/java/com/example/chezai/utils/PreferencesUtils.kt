package com.example.chezai.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * SharedPreferences 工具类
 */
object PreferencesUtils {
    
    private const val PREF_NAME = "chezai_prefs"
    private const val KEY_USER_ID = "user_id"
    private const val KEY_IS_LOGGED_IN = "is_logged_in"
    private const val KEY_CAR_ID = "car_id"
    private const val KEY_THEME = "theme"
    
    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }
    
    /**
     * 保存用户 ID
     */
    fun saveUserId(context: Context, userId: String) {
        getPreferences(context).edit().putString(KEY_USER_ID, userId).apply()
    }
    
    /**
     * 获取用户 ID
     */
    fun getUserId(context: Context): String? {
        return getPreferences(context).getString(KEY_USER_ID, null)
    }
    
    /**
     * 保存登录状态
     */
    fun saveLoginStatus(context: Context, isLoggedIn: Boolean) {
        getPreferences(context).edit().putBoolean(KEY_IS_LOGGED_IN, isLoggedIn).apply()
    }
    
    /**
     * 获取登录状态
     */
    fun isLoggedIn(context: Context): Boolean {
        return getPreferences(context).getBoolean(KEY_IS_LOGGED_IN, false)
    }
    
    /**
     * 保存车辆 ID
     */
    fun saveCarId(context: Context, carId: String) {
        getPreferences(context).edit().putString(KEY_CAR_ID, carId).apply()
    }
    
    /**
     * 获取车辆 ID
     */
    fun getCarId(context: Context): String? {
        return getPreferences(context).getString(KEY_CAR_ID, null)
    }
    
    /**
     * 清除所有数据
     */
    fun clearAll(context: Context) {
        getPreferences(context).edit().clear().apply()
    }
}
