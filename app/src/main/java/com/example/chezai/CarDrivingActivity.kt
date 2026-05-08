package com.example.chezai

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CarDrivingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_car_driving)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupClickListeners()
    }

    private fun setupClickListeners() {
        // 关闭按钮
        findViewById<ImageView>(R.id.btn_close).setOnClickListener {
            finish()
        }

        // 锚点按钮
        setupIconButton(R.id.btn_anchor, "锚点")

        // 移动按钮
        setupIconButton(R.id.btn_move, "移动")

        // 上传按钮
        setupIconButton(R.id.btn_upload, "上传")

        // 刷新按钮
        setupIconButton(R.id.btn_refresh, "刷新")

        // 下载按钮
        setupIconButton(R.id.btn_download, "下载")
    }

    private fun setupIconButton(viewId: Int, label: String) {
        val view = findViewById<View>(viewId)
        view.setOnClickListener {
            Toast.makeText(this, label, Toast.LENGTH_SHORT).show()
        }
        
        // 添加点击效果
        view.setOnTouchListener { v, event ->
            when (event.action) {
                android.view.MotionEvent.ACTION_DOWN -> {
                    v.animate().scaleX(0.9f).scaleY(0.9f).setDuration(100).start()
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
