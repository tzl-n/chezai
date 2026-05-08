package com.example.chezai.utils

import android.content.Context
import android.widget.Toast

/**
 * Toast 工具类
 */
object ToastUtils {
    
    /**
     * 显示短 Toast
     */
    fun showShort(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
    
    /**
     * 显示长 Toast
     */
    fun showLong(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
    
    /**
     * 显示成功提示
     */
    fun showSuccess(context: Context, message: String) {
        showShort(context, "✓ $message")
    }
    
    /**
     * 显示错误提示
     */
    fun showError(context: Context, message: String) {
        showLong(context, "✗ $message")
    }
}
