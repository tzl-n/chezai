package com.example.chezai

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CarLockActivity : AppCompatActivity() {
    private var isLocked = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_car_lock)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 初始化视图
        val btnBack = findViewById<ImageView>(R.id.btn_back)
        val btnUnlock = findViewById<Button>(R.id.btn_unlock)
        val btnLock = findViewById<Button>(R.id.btn_lock)
        val tvLockStatus = findViewById<TextView>(R.id.tv_lock_status)
        val ivLockStatus = findViewById<ImageView>(R.id.iv_lock_status)

        // 返回按钮点击事件
        btnBack.setOnClickListener {
            finish()
        }

        // 解锁按钮点击事件
        btnUnlock.setOnClickListener {
            if (!isLocked) {
                Toast.makeText(this, "车辆已经是解锁状态", Toast.LENGTH_SHORT).show()
            } else {
                isLocked = false
                updateLockStatus(tvLockStatus, ivLockStatus, btnUnlock, btnLock)
                Toast.makeText(this, "车辆已解锁", Toast.LENGTH_SHORT).show()
            }
        }

        // 锁定按钮点击事件
        btnLock.setOnClickListener {
            if (isLocked) {
                Toast.makeText(this, "车辆已经是锁定状态", Toast.LENGTH_SHORT).show()
            } else {
                isLocked = true
                updateLockStatus(tvLockStatus, ivLockStatus, btnUnlock, btnLock)
                Toast.makeText(this, "车辆已锁定", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateLockStatus(
        tvLockStatus: TextView,
        ivLockStatus: ImageView,
        btnUnlock: Button,
        btnLock: Button
    ) {
        if (isLocked) {
            // 车辆已锁定：解锁按钮为红色（可点击），锁定按钮为灰色（不可点击）
            tvLockStatus.text = "车门锁止"
            ivLockStatus.setImageResource(R.drawable.ic_lock_red)
            
            // 左侧红色按钮
            btnUnlock.setBackgroundResource(R.drawable.bg_button_unlock_left)
            btnUnlock.setTextColor(getColor(android.R.color.white))
            btnUnlock.isEnabled = true
            
            // 右侧灰色按钮
            btnLock.setBackgroundResource(R.drawable.bg_button_grey_right)
            btnLock.setTextColor(getColor(android.R.color.darker_gray))
            btnLock.isEnabled = false
        } else {
            // 车辆已解锁：锁定按钮为红色（可点击），解锁按钮为灰色（不可点击）
            tvLockStatus.text = "车门解锁"
            ivLockStatus.setImageResource(R.drawable.ic_lock_red)
            
            // 左侧灰色按钮
            btnUnlock.setBackgroundResource(R.drawable.bg_button_grey_left)
            btnUnlock.setTextColor(getColor(android.R.color.darker_gray))
            btnUnlock.isEnabled = false
            
            // 右侧红色按钮
            btnLock.setBackgroundResource(R.drawable.bg_button_unlock_right)
            btnLock.setTextColor(getColor(android.R.color.white))
            btnLock.isEnabled = true
        }
    }
}
