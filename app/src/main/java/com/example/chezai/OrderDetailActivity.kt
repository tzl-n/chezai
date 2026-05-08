package com.example.chezai

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class OrderDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_order_detail)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupUI()
    }

    private fun setupUI() {
        // 返回按钮
        findViewById<android.widget.ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        // 复制订单编号按钮
        findViewById<Button>(R.id.btnCopy).setOnClickListener {
            copyOrderNumber()
        }

        // 反馈按钮
        findViewById<Button>(R.id.btnFeedback).setOnClickListener {
            Toast.makeText(this, "反馈成功", Toast.LENGTH_SHORT).show()
        }
    }

    private fun copyOrderNumber() {
        val orderNumber = "202309081223384455945553"
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("订单编号", orderNumber)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(this, "订单编号已复制", Toast.LENGTH_SHORT).show()
    }
}
