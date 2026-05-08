package com.example.chezai

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TripDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_trip_detail)

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

        // 行程记录列表项点击事件
        setupButton(R.id.trip_item_1, "行程1")
        setupButton(R.id.trip_item_2, "行程2")
        setupButton(R.id.trip_item_3, "行程3")
        setupButton(R.id.trip_item_4, "行程4")
        setupButton(R.id.trip_item_5, "行程5")
        setupButton(R.id.trip_item_6, "行程6")
        setupButton(R.id.trip_item_7, "行程7")
        setupButton(R.id.trip_item_8, "行程8")
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