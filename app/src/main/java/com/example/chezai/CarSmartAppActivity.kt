package com.example.chezai

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CarSmartAppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_car_smart_app)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupClickListeners()
    }

    private fun setupClickListeners() {
        // 车辆图片点击跳转到全景视图
        findViewById<View>(R.id.car_image_container).setOnClickListener {
            val intent = Intent(this, CarMainActivity::class.java)
            startActivity(intent)
        }
        
        // 设置按钮
        setupButton(R.id.btn_settings, "设置")
        
        // 温度调节按钮
        setupButton(R.id.btn_temp_minus, "温度减")
        setupButton(R.id.btn_temp_plus, "温度加")
        
        // 功能按钮
        setupButton(R.id.btn_compass, "锚")
        setupButton(R.id.btn_direction, "列表")
        setupButton(R.id.btn_light, "灯光")
        setupButton(R.id.btn_control1, "控制1")
        setupButton(R.id.btn_paint, "控制")
        setupButton(R.id.btn_paint_color, "改色")
        setupButton(R.id.btn_control2, "控制2")
        setupButton(R.id.btn_control3, "控制3")
        
        // 底部导航
        setupButton(R.id.nav_navigation, "导航")
        setupButton(R.id.nav_car, "车辆")
        setupButton(R.id.nav_message, "消息")
        setupButton(R.id.nav_center, "服务中心")
        setupButton(R.id.nav_bell, "通知")
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