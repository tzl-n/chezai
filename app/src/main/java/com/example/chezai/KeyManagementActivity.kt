package com.example.chezai

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class KeyManagementActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_key_management)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.keyManagementRoot)) { v, insets ->
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
        
        // 菜单按钮
        findViewById<ImageView>(R.id.btnMenu).setOnClickListener {
            val intent = Intent(this, CarEnvironmentControlActivity::class.java)
            startActivity(intent)
        }
        
        // 呼叫手机
        findViewById<LinearLayout>(R.id.btnCallPhone).setOnClickListener {
            val intent = Intent(this, CallPhoneActivity::class.java)
            startActivity(intent)
        }
        
        // 自拍解锁
        findViewById<LinearLayout>(R.id.btnSelfieUnlock).setOnClickListener {
            Toast.makeText(this, "自拍解锁", Toast.LENGTH_SHORT).show()
        }
        
        // 定位找车
        findViewById<LinearLayout>(R.id.btnLocateCar).setOnClickListener {
            val intent = Intent(this, SearchParkingActivity::class.java)
            startActivity(intent)
        }
        
        // 录音锁车
        findViewById<LinearLayout>(R.id.btnRecordLock).setOnClickListener {
            Toast.makeText(this, "录音锁车", Toast.LENGTH_SHORT).show()
        }
        
        // 靠近解锁
        findViewById<LinearLayout>(R.id.btnNearUnlock).setOnClickListener {
            Toast.makeText(this, "靠近解锁", Toast.LENGTH_SHORT).show()
        }
        
        // 指纹解锁
        findViewById<LinearLayout>(R.id.btnFingerprintUnlock).setOnClickListener {
            Toast.makeText(this, "指纹解锁", Toast.LENGTH_SHORT).show()
        }
    }
}
