package com.example.chezai

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RentalTimeActivity : AppCompatActivity() {

    private var timeRemaining = 1414 // 23分34秒，总共秒数
    private var handler: Handler? = null
    private var countdownRunnable: Runnable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_rental_time)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupUI()
        startCountdown()
    }

    private fun setupUI() {
        // 返回按钮
        findViewById<android.widget.ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        // 继续租赁按钮
        findViewById<Button>(R.id.btnContinueRental).setOnClickListener {
            Toast.makeText(this, "租赁成功", Toast.LENGTH_SHORT).show()
            //把字改成租赁成功
            findViewById<TextView>(R.id.btnContinueRental).text = "已租赁"

        }

        // 车辆无损按钮
        findViewById<android.widget.LinearLayout>(R.id.btnVehicleDamage).setOnClickListener {
            Toast.makeText(this, "报告损坏情况", Toast.LENGTH_SHORT).show()
        }

        // 解锁按钮
        findViewById<CardView>(R.id.btnUnlock).setOnClickListener {
            Toast.makeText(this, "正在解锁车辆...", Toast.LENGTH_SHORT).show()

            CoroutineScope(Dispatchers.Main).launch {
                delay(2000)
                Toast.makeText(this@RentalTimeActivity, "已解锁", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun startCountdown() {
        // 立即显示初始时间
        updateCountdownDisplay()
        
        handler = Handler(Looper.getMainLooper())
        countdownRunnable = object : Runnable {
            override fun run() {
                if (timeRemaining > 0) {
                    timeRemaining--
                    updateCountdownDisplay()
                    handler?.postDelayed(this, 1000)
                } else {
                    Toast.makeText(this@RentalTimeActivity, "租赁时间已到", Toast.LENGTH_SHORT).show()
                }
            }
        }
        handler?.postDelayed(countdownRunnable!!, 1000) // 1秒后开始倒计时
    }

    private fun updateCountdownDisplay() {
        val hours = timeRemaining / 3600
        val minutes = (timeRemaining % 3600) / 60
        val seconds = timeRemaining % 60

        val timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds)
        findViewById<TextView>(R.id.tvCountdown).text = timeString
    }

    override fun onDestroy() {
        super.onDestroy()
        handler?.removeCallbacksAndMessages(null)
    }
}
