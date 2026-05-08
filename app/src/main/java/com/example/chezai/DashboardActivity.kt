package com.example.chezai

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupUI()
    }

    private fun setupUI() {
        // 返回按钮
        findViewById<androidx.cardview.widget.CardView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        // 查看更多按钮
        findViewById<android.widget.TextView>(R.id.btnViewMore).setOnClickListener {
            Toast.makeText(this, "查看更多", Toast.LENGTH_SHORT).show()
        }
    }
}
