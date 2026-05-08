package com.example.chezai.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * 数据缓存管理器
 * 支持内存缓存 + SharedPreferences 持久化
 */
object DataCacheManager {
    
    private const val PREF_CACHE = "api_cache"
    private const val CACHE_EXPIRY = "cache_expiry"
    
    // 默认缓存时间：5分钟
    var defaultCacheDuration: Long = 5 * 60 * 1000
    
    private val gson = Gson()
    
    /**
     * 缓存数据（内存 + 持久化）
     */
    fun <T> cacheData(context: Context, key: String, data: T, duration: Long = defaultCacheDuration) {
        // 内存缓存
        MemoryCache.put(key, data, duration)
        
        // 持久化缓存
        val prefs = getPrefs(context)
        prefs.edit().apply {
            putString(key, gson.toJson(data))
            putLong("${key}_$CACHE_EXPIRY", System.currentTimeMillis() + duration)
            apply()
        }
    }
    
    /**
     * 获取缓存数据
     */
    fun <T> getCachedData(context: Context, key: String, clazz: Class<T>): T? {
        // 优先从内存缓存获取
        val memoryData = MemoryCache.get<T>(key)
        if (memoryData != null) return memoryData
        
        // 从持久化缓存获取
        val prefs = getPrefs(context)
        val json = prefs.getString(key, null) ?: return null
        val expiry = prefs.getLong("${key}_$CACHE_EXPIRY", 0)
        
        // 检查是否过期
        if (System.currentTimeMillis() > expiry) {
            clearCache(context, key)
            return null
        }
        
        // 反序列化
        return try {
            gson.fromJson(json, clazz)
        } catch (e: Exception) {
            null
        }
    }
    
    /**
     * 获取缓存的列表数据
     */
    fun <T> getCachedList(context: Context, key: String, clazz: Class<T>): List<T>? {
        val prefs = getPrefs(context)
        val json = prefs.getString(key, null) ?: return null
        val expiry = prefs.getLong("${key}_$CACHE_EXPIRY", 0)
        
        if (System.currentTimeMillis() > expiry) {
            clearCache(context, key)
            return null
        }
        
        return try {
            val type = TypeToken.getParameterized(List::class.java, clazz).type
            gson.fromJson(json, type)
        } catch (e: Exception) {
            null
        }
    }
    
    /**
     * 清除指定缓存
     */
    fun clearCache(context: Context, key: String) {
        MemoryCache.remove(key)
        getPrefs(context).edit().apply {
            remove(key)
            remove("${key}_$CACHE_EXPIRY")
            apply()
        }
    }
    
    /**
     * 清除所有缓存
     */
    fun clearAllCache(context: Context) {
        MemoryCache.clear()
        getPrefs(context).edit().clear().apply()
    }
    
    private fun getPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_CACHE, Context.MODE_PRIVATE)
    }
    
    /**
     * 内存缓存
     */
    private object MemoryCache {
        private val cache = mutableMapOf<String, CacheEntry<Any>>()
        
        fun <T> put(key: String, data: T, duration: Long) {
            cache[key] = CacheEntry<Any>(data as Any, System.currentTimeMillis() + duration)
        }
        
        @Suppress("UNCHECKED_CAST")
        fun <T> get(key: String): T? {
            val entry = cache[key] ?: return null
            if (System.currentTimeMillis() > entry.expiry) {
                cache.remove(key)
                return null
            }
            return entry.data as T
        }
        
        fun remove(key: String) {
            cache.remove(key)
        }
        
        fun clear() {
            cache.clear()
        }
    }
    
    private data class CacheEntry<T>(
        val data: T,
        val expiry: Long
    )
}
