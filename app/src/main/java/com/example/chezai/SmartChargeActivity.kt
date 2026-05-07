package com.example.chezai

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.TextView
import android.widget.Button
import android.widget.Toast

class SmartChargeActivity : AppCompatActivity() {
    
    private var timeRemaining = 1823 // 30分23秒，总共秒数
    private var handler: Handler? = null
    private var countdownRunnable: Runnable? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_smart_charge)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupUI()
        startCountdown()
    }
    
    private fun setupUI() {
        findViewById<android.widget.ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }
        
        // 扫码充电按钮点击事件
        findViewById<Button>(R.id.btnScanCharge).setOnClickListener {
            val intent = Intent(this, ChargingActivity::class.java)
            startActivity(intent)
        }

        // 取消预约按钮点击事件
        findViewById<Button>(R.id.btnCancelReservation).setOnClickListener {
            Toast.makeText(this, "已取消预约", Toast.LENGTH_SHORT).show()
            timeRemaining = 0
            findViewById<TextView>(R.id.tvCountdown).text = "0分00秒"
            findViewById<TextView>(R.id.tvCountdownSub).text = "预约已取消"
        }
    }

    
    private fun startCountdown() {
        val tvCountdown = findViewById<TextView>(R.id.tvCountdown)
        val tvCountdownSub = findViewById<TextView>(R.id.tvCountdownSub)
        
        handler = Handler(Looper.getMainLooper())
        countdownRunnable = object : Runnable {
            override fun run() {
                if (timeRemaining > 0) {
                    timeRemaining--
                    updateCountdownDisplay(tvCountdown, tvCountdownSub)
                    handler?.postDelayed(this, 1000)
                } else {
                    // 倒计时结束，取消预约
                    tvCountdownSub.text = "预约已取消"
                }
            }
        }
        handler?.postDelayed(countdownRunnable!!, 1000)
    }
    
    private fun updateCountdownDisplay(tvCountdown: TextView, tvCountdownSub: TextView) {
        val minutes = timeRemaining / 60
        val seconds = timeRemaining % 60
        tvCountdown.text = String.format("%d分%02d秒", minutes, seconds)
        tvCountdownSub.text = String.format("%d分钟预约时间，过期则自动取消", minutes)
    }
    
    override fun onDestroy() {
        super.onDestroy()
        handler?.removeCallbacksAndMessages(null)
    }
}
