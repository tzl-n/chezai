package com.example.chezai

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CarMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_car_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.btn_add)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupClickListeners()
    }

    private fun setupClickListeners() {
        // 添加按钮
        setupButton(R.id.btn_add, "添加")
        
        // 胎压正常按钮
        setupButton(R.id.btn_tire, "胎压正常")
        
        // 解锁按钮 - 跳转到已驻车页面
        findViewById<View>(R.id.btn_unlock).setOnClickListener {
            val intent = Intent(this, ParkedActivity::class.java)
            startActivity(intent)
        }
        
        // 其他功能按钮
        setupButton(R.id.btn_cruise, "巡车")
        setupButton(R.id.btn_aircon, "空调")
        setupButton(R.id.btn_navigation, "导航")
        
        // 累计旅程卡片 - 点击跳转到行程详情页面
        findViewById<View>(R.id.btn_total_trip).setOnClickListener {
            val intent = Intent(this, TripDetailActivity::class.java)
            startActivity(intent)
        }
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
                    v.animate().scaleX(0.95f).scaleY(0.95f).setDuration(100).start()
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