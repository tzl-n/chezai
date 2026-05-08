package com.example.chezai.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import com.example.chezai.R

/**
 * 加载状态管理器
 * 统一管理 Loading、Empty、Error 三种状态
 */
class LoadingStateView(context: Context) : FrameLayout(context) {
    
    private val loadingLayout: LinearLayout
    private val emptyLayout: LinearLayout
    private val errorLayout: LinearLayout
    private val contentLayout: FrameLayout
    private val txtEmptyMessage: TextView
    private val txtErrorMessage: TextView
    private val btnRetry: Button
    
    private var onRetryListener: (() -> Unit)? = null
    
    init {
        LayoutInflater.from(context).inflate(R.layout.loading_state_layout, this, true)
        
        loadingLayout = findViewById(R.id.layoutLoading)
        emptyLayout = findViewById(R.id.layoutEmpty)
        errorLayout = findViewById(R.id.layoutError)
        contentLayout = findViewById(R.id.layoutContent)
        txtEmptyMessage = findViewById(R.id.txtEmptyMessage)
        txtErrorMessage = findViewById(R.id.txtErrorMessage)
        btnRetry = findViewById(R.id.btnRetry)
        
        btnRetry.setOnClickListener {
            onRetryListener?.invoke()
        }
    }
    
    /**
     * 显示加载状态
     */
    fun showLoading() {
        loadingLayout.visibility = View.VISIBLE
        emptyLayout.visibility = View.GONE
        errorLayout.visibility = View.GONE
        contentLayout.visibility = View.GONE
    }
    
    /**
     * 显示内容
     */
    fun showContent() {
        loadingLayout.visibility = View.GONE
        emptyLayout.visibility = View.GONE
        errorLayout.visibility = View.GONE
        contentLayout.visibility = View.VISIBLE
    }
    
    /**
     * 显示空状态
     */
    fun showEmpty(message: String = "暂无数据") {
        loadingLayout.visibility = View.GONE
        emptyLayout.visibility = View.VISIBLE
        errorLayout.visibility = View.GONE
        contentLayout.visibility = View.GONE
        txtEmptyMessage.text = message
    }
    
    /**
     * 显示错误状态
     */
    fun showError(message: String = "加载失败", onRetry: (() -> Unit)? = null) {
        loadingLayout.visibility = View.GONE
        emptyLayout.visibility = View.GONE
        errorLayout.visibility = View.VISIBLE
        contentLayout.visibility = View.GONE
        txtErrorMessage.text = message
        onRetryListener = onRetry
    }
    
    /**
     * 获取内容容器，用于添加子视图
     */
    fun getContentContainer(): FrameLayout = contentLayout
}
