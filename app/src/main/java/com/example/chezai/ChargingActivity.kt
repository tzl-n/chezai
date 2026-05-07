package com.example.chezai

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ChargingActivity : AppCompatActivity() {
    
    private var chargingSeconds = 2349 // 0小时39分钟09秒
    private var prepaidAmount = 0.0 // 充电预付金额
    private var handler: Handler? = null
    private var chargingRunnable: Runnable? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_charging)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        
        setupUI()
        startChargingTimer()
    }
    
    private fun setupUI() {
        // 返回按钮
        findViewById<android.widget.ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }
        
        // 结束充电按钮
        findViewById<Button>(R.id.btnEndCharging).setOnClickListener {
            stopChargingTimer()
            val intent = Intent(this, ChargeCompletedActivity::class.java)
            startActivity(intent)
            finish()
        }
        
        // 立即充值按钮
        findViewById<Button>(R.id.btnRecharge).setOnClickListener {
            Toast.makeText(this, "跳转到充值页面...", Toast.LENGTH_SHORT).show()
            // TODO: 实现充值功能
        }
        
        // 查看充电记录
        findViewById<TextView>(R.id.tvViewRecords).setOnClickListener {
            Toast.makeText(this, "查看充电记录", Toast.LENGTH_SHORT).show()
            // TODO: 跳转到充电记录页面
        }
    }
    
    private fun startChargingTimer() {
        val tvChargingTime = findViewById<TextView>(R.id.tvChargingTime)
        val tvPrepaid = findViewById<TextView>(R.id.tvPrepaid)
        
        handler = Handler(Looper.getMainLooper())
        chargingRunnable = object : Runnable {
            override fun run() {
                chargingSeconds++
                prepaidAmount += 0.01 // 每秒增加1分钱
                updateChargingTime(tvChargingTime)
                updatePrepaidAmount(tvPrepaid)
                handler?.postDelayed(this, 1000)
            }
        }
        handler?.postDelayed(chargingRunnable!!, 1000)
    }
    
    private fun updateChargingTime(tvChargingTime: TextView) {
        val hours = chargingSeconds / 3600
        val minutes = (chargingSeconds % 3600) / 60
        val seconds = chargingSeconds % 60
        tvChargingTime.text = String.format("%d小时%d分钟%02d秒", hours, minutes, seconds)
    }
    
    private fun updatePrepaidAmount(tvPrepaid: TextView) {
        tvPrepaid.text = String.format("%.2f元", prepaidAmount)
    }
    
    private fun stopChargingTimer() {
        handler?.removeCallbacksAndMessages(null)
    }
    
    override fun onDestroy() {
        super.onDestroy()
        stopChargingTimer()
    }
}
