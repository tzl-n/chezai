package com.example.chezai

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class VehicleInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_vehicle_info)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupUI()
    }

    private fun setupUI() {
        // 菜单按钮
        findViewById<androidx.cardview.widget.CardView>(R.id.btnMenu).setOnClickListener {
            Toast.makeText(this, "菜单", Toast.LENGTH_SHORT).show()
        }

        // 查看更多按钮
        findViewById<android.widget.TextView>(R.id.btnViewMore).setOnClickListener {
            Toast.makeText(this, "查看更多", Toast.LENGTH_SHORT).show()
        }

        // 引擎卡片
        findViewById<androidx.cardview.widget.CardView>(R.id.cardEngine).setOnClickListener {
            Toast.makeText(this, "引擎 - 睡眠模式", Toast.LENGTH_SHORT).show()
        }

        // 气候卡片
        findViewById<androidx.cardview.widget.CardView>(R.id.cardClimate).setOnClickListener {
            Toast.makeText(this, "气候 - 未连接", Toast.LENGTH_SHORT).show()
        }

        // 照明卡片
        findViewById<androidx.cardview.widget.CardView>(R.id.cardLighting).setOnClickListener {
            Toast.makeText(this, "照明 - 未连接", Toast.LENGTH_SHORT).show()
        }

        // 涡轮空调卡片
        findViewById<androidx.cardview.widget.CardView>(R.id.cardTurbo).setOnClickListener {
            Toast.makeText(this, "涡轮空调 - 未连接", Toast.LENGTH_SHORT).show()
        }
    }
}
