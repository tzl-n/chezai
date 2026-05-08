package com.example.chezai

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class ParkedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_parked)

        setupClickListeners()
    }

    private fun setupClickListeners() {
        // 功能按钮
        setupButton(R.id.btn_lock, "锁车")
        setupButton(R.id.btn_charge, "充电")
        
        // 空调按钮 - 跳转到空调设置页面
        findViewById<View>(R.id.btn_ac1).setOnClickListener {
            val intent = Intent(this, AcControlActivity::class.java)
            startActivity(intent)
        }
        
        setupButton(R.id.btn_window, "车窗")

        // 信息卡片
        setupButton(R.id.card_location, "查看位置")

        // 功能列表
        setupButton(R.id.item_reserve, "预约充电")
        setupButton(R.id.item_key, "钥匙与安全")
        setupButton(R.id.item_fridge, "车载冰箱")
    }

    private fun setupButton(viewId: Int, label: String) {
        val view = findViewById<View>(viewId)
        view.setOnClickListener {
            Toast.makeText(this, label, Toast.LENGTH_SHORT).show()
        }

        // 添加点击效果
        view.setOnTouchListener { v, event ->
            when (event.action) {
                android.view.MotionEvent.ACTION_DOWN -> {
                    v.animate().scaleX(0.98f).scaleY(0.98f).setDuration(100).start()
                    false
                }
                android.view.MotionEvent.ACTION_UP, android.view.MotionEvent.ACTION_CANCEL -> {
                    v.animate().scaleX(1f).scaleY(1f).setDuration(100).start()
                    false
                }
                else -> false
            }
        }
    }
}