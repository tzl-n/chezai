package com.example.chezai

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CarPanoramaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_car_panorama)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.btn_back)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupClickListeners()
    }

    private fun setupClickListeners() {
        // 返回按钮
        findViewById<View>(R.id.btn_back).setOnClickListener {
            finish()
        }

        // 解锁按钮 - 跳转到已驻车页面
        findViewById<View>(R.id.btn_unlock).setOnClickListener {
            val intent = Intent(this, ParkedActivity::class.java)
            startActivity(intent)
        }

        // 更多按钮
        findViewById<View>(R.id.btn_more).setOnClickListener {
            Toast.makeText(this, "更多", Toast.LENGTH_SHORT).show()
        }

        // 视角控制按钮
        setupButton(R.id.btn_top_view, "俯视图")
        setupButton(R.id.btn_front_view, "前视图")
        setupButton(R.id.btn_back_view, "后视图")
        setupButton(R.id.btn_left_view, "左视图")
        setupButton(R.id.btn_right_view, "右视图")

        // 底部控制按钮
        setupButton(R.id.btn_auto_rotate, "自动旋转")
        setupButton(R.id.btn_360, "360度")
        setupButton(R.id.btn_screenshot, "截图")
        setupButton(R.id.btn_record, "录像")
        setupButton(R.id.btn_fullscreen, "全屏")
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