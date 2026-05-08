package com.example.chezai

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CarControlDetailActivity : AppCompatActivity() {
    private var isLocked = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_car_control_detail)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 初始化视图
        val btnBack = findViewById<ImageView>(R.id.btn_back)
        val ivCarModel = findViewById<ImageView>(R.id.iv_car_model)
        val btnFlash = findViewById<LinearLayout>(R.id.btn_flash)
        val btnHorn = findViewById<LinearLayout>(R.id.btn_horn)
        val btnStart = findViewById<LinearLayout>(R.id.btn_start)
        val btnVentilation = findViewById<LinearLayout>(R.id.btn_ventilation)
        val tvDoorStatus = findViewById<TextView>(R.id.tv_door_status)
        val ivLockIcon = findViewById<ImageView>(R.id.iv_lock_icon)

        // 返回按钮点击事件
        btnBack.setOnClickListener {
            finish()
        }

        // 点击车辆模型跳转到App控制界面
        ivCarModel.setOnClickListener {
            val intent = Intent(this, CarAppControlActivity::class.java)
            startActivity(intent)
        }

        // 闪灯按钮
        btnFlash.setOnClickListener {
            Toast.makeText(this, "闪灯功能", Toast.LENGTH_SHORT).show()
        }

        // 鸣笛按钮
        btnHorn.setOnClickListener {
            Toast.makeText(this, "鸣笛功能", Toast.LENGTH_SHORT).show()
        }

        // 启动按钮
        btnStart.setOnClickListener {
            isLocked = !isLocked
            updateDoorStatus(tvDoorStatus, ivLockIcon)
            Toast.makeText(this, if (isLocked) "车辆已锁定" else "车辆已解锁", Toast.LENGTH_SHORT).show()
        }

        // 通风按钮
        btnVentilation.setOnClickListener {
            Toast.makeText(this, "通风功能", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateDoorStatus(tvDoorStatus: TextView, ivLockIcon: ImageView) {
        if (isLocked) {
            tvDoorStatus.text = "锁定"
            ivLockIcon.setImageResource(R.drawable.ic_lock_grey)
        } else {
            tvDoorStatus.text = "打开"
            ivLockIcon.setImageResource(R.drawable.ic_lock_grey)
        }
    }
}
