package com.example.chezai

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TrailerDarkModeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_trailer_dark_mode)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.root)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupClickListeners()
    }

    private fun setupClickListeners() {
        // 返回按钮
        findViewById<ImageView>(R.id.btnBack)?.setOnClickListener {
            finish()
        }

        // 菜单按钮
        findViewById<ImageView>(R.id.btnMenu)?.setOnClickListener {
            Toast.makeText(this, "菜单", Toast.LENGTH_SHORT).show()
        }

        // 解锁按钮
        findViewById<LinearLayout>(R.id.btnUnlock)?.setOnClickListener {
            Toast.makeText(this, "解锁", Toast.LENGTH_SHORT).show()
        }

        // 车主按钮
        findViewById<LinearLayout>(R.id.btnOwner)?.setOnClickListener {
            Toast.makeText(this, "车主服务", Toast.LENGTH_SHORT).show()
        }

        // 录像按钮
        findViewById<LinearLayout>(R.id.btnRecord)?.setOnClickListener {
            Toast.makeText(this, "录像", Toast.LENGTH_SHORT).show()
        }

        // 灯光按钮
        findViewById<LinearLayout>(R.id.btnLight)?.setOnClickListener {
            Toast.makeText(this, "灯光控制", Toast.LENGTH_SHORT).show()
        }

        // 车辆控制按钮
        findViewById<LinearLayout>(R.id.btnVehicleControl)?.setOnClickListener {
            Toast.makeText(this, "车辆控制", Toast.LENGTH_SHORT).show()
        }

        // 返回主页面按钮
        findViewById<ImageView>(R.id.btnBackMain)?.setOnClickListener {
            finish()
        }
    }
}
