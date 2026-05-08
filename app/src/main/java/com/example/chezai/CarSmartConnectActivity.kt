package com.example.chezai

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CarSmartConnectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_smart_connect)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 返回按钮点击事件
        findViewById<ImageView>(R.id.btn_back).setOnClickListener {
            finish()
        }

        // 百变主题按钮点击事件
        findViewById<LinearLayout>(R.id.btn_theme).setOnClickListener {
            val intent = Intent(this, CarModelSelectActivity::class.java)
            startActivity(intent)
        }
    }
}