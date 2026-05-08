package com.example.chezai

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.cardview.widget.CardView

class CarOwnerServiceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_car_owner_service)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.root)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupListeners()
    }

    private fun setupListeners() {
        // 返回按钮
        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        // 更多按钮
        findViewById<ImageView>(R.id.btnMore).setOnClickListener {
            Toast.makeText(this, "更多功能", Toast.LENGTH_SHORT).show()
        }

        // 地图导航
        findViewById<CardView>(R.id.cardNavigation).setOnClickListener {
            Toast.makeText(this, "地图导航", Toast.LENGTH_SHORT).show()
        }

        // 智能电桩
        findViewById<CardView>(R.id.cardCharging).setOnClickListener {
            Toast.makeText(this, "智能电桩", Toast.LENGTH_SHORT).show()
        }

        // 自动泊车
        findViewById<CardView>(R.id.cardParking).setOnClickListener {
            Toast.makeText(this, "自动泊车", Toast.LENGTH_SHORT).show()
        }

        // 车辆状况
        findViewById<CardView>(R.id.cardStatus).setOnClickListener {
            Toast.makeText(this, "车辆状况", Toast.LENGTH_SHORT).show()
        }
    }
}
