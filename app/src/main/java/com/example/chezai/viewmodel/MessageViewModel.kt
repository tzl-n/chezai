package com.example.chezai.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * 消息 ViewModel
 */
class MessageViewModel : BaseViewModel() {
    
    private val _messages = MutableLiveData<List<String>>()
    val messages: LiveData<List<String>> = _messages
    
    private val _unreadCount = MutableLiveData<Int>()
    val unreadCount: LiveData<Int> = _unreadCount
    
    /**
     * 获取消息列表
     */
    fun getMessages() {
        safeApiCall(
            apiCall = {
                // ApiClient.apiService.getMessages()
                listOf("消息1", "消息2", "消息3")
            },
            onSuccess = { messageList ->
                _messages.value = messageList
                _unreadCount.value = messageList.size
            }
        )
    }
    
    /**
     * 标记消息为已读
     */
    fun markAsRead(messageId: String) {
        safeApiCall(
            apiCall = {
                // ApiClient.apiService.markAsRead(messageId)
                true
            },
            onSuccess = {
                val currentCount = _unreadCount.value ?: 0
                _unreadCount.value = maxOf(0, currentCount - 1)
            }
        )
    }
}
