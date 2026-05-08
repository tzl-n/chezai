package com.example.chezai

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CarAppControlActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_app_control)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 寻车按钮点击事件
        findViewById<LinearLayout>(R.id.btn_find_car).setOnClickListener {
            // 寻车功能
        }

        // 车锁按钮点击事件 - 跳转到一键锁车Activity
        findViewById<LinearLayout>(R.id.btn_lock).setOnClickListener {
            val intent = Intent(this, OneClickLockActivity::class.java)
            startActivity(intent)
        }

        // 空调按钮点击事件
        findViewById<LinearLayout>(R.id.btn_ac).setOnClickListener {
            // 空调控制功能
        }

        // 更多按钮点击事件 - 跳转到汽车智能网联Activity
        findViewById<LinearLayout>(R.id.btn_more).setOnClickListener {
            val intent = Intent(this, CarSmartConnectActivity::class.java)
            startActivity(intent)
        }

        // 地图导航按钮点击事件
        findViewById<LinearLayout>(R.id.btn_navigation).setOnClickListener {
            // 地图导航功能
        }

        // 车辆控制标签点击事件 - 跳转到车载App界面
        findViewById<LinearLayout>(R.id.tab_car_control).setOnClickListener {
            val intent = Intent(this, CarSmartAppActivity::class.java)
            startActivity(intent)
        }

        // 环境调节标签点击事件
        findViewById<LinearLayout>(R.id.tab_env_control).setOnClickListener {
            // 切换到环境调节标签
            setActiveTab(false)
        }
    }

    private fun setActiveTab(isCarControl: Boolean) {
        val carControlText = findViewById<TextView>(R.id.tab_car_control_text)
        val envControlText = findViewById<TextView>(R.id.tab_env_control_text)

        if (isCarControl) {
            carControlText.setTextColor(getColor(R.color.black))
            envControlText.setTextColor(getColor(R.color.grey))
        } else {
            carControlText.setTextColor(getColor(R.color.grey))
            envControlText.setTextColor(getColor(R.color.black))
        }
    }
}