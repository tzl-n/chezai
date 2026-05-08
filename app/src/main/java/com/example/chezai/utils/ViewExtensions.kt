package com.example.chezai.utils

import android.view.View

/**
 * View 扩展函数
 */

/**
 * 设置 View 的可见性
 */
fun View.setVisible(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}

/**
 * 设置 View 为可见
 */
fun View.show() {
    this.visibility = View.VISIBLE
}

/**
 * 设置 View 为隐藏
 */
fun View.hide() {
    this.visibility = View.GONE
}

/**
 * 设置 View 为不可见但占用空间
 */
fun View.invisible() {
    this.visibility = View.INVISIBLE
}
