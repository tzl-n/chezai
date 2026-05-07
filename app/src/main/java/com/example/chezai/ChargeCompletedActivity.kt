package com.example.chezai

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ChargeCompletedActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_charge_completed)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        
        setupUI()
    }
    
    private fun setupUI() {
        // 返回按钮
        findViewById<android.widget.ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }
        
        // 关闭按钮
        findViewById<Button>(R.id.btnClose).setOnClickListener {
            Toast.makeText(this, "关闭页面", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
