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

class DashCamActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dash_cam)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.root)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupClickListeners()
    }

    private fun setupClickListeners() {
        // 返回按钮
        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        // 设置按钮
        findViewById<ImageView>(R.id.btnSettings).setOnClickListener {
            val intent = Intent(this, DashCamSettingsActivity::class.java)
            startActivity(intent)
        }

        // 前方记录按钮
        findViewById<TextView>(R.id.btnFrontRecord).setOnClickListener {
            Toast.makeText(this, "前方记录", Toast.LENGTH_SHORT).show()
        }

        // 后方记录按钮
        findViewById<TextView>(R.id.btnRearRecord).setOnClickListener {
            Toast.makeText(this, "后方记录", Toast.LENGTH_SHORT).show()
        }
    }
}
