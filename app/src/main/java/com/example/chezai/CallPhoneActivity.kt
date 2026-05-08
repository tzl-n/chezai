package com.example.chezai

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class CallPhoneActivity : AppCompatActivity() {

    private lateinit var tvCallDuration: TextView
    private var seconds = 203 // 3:23 = 203秒
    private val handler = Handler(Looper.getMainLooper())
    private var isRunning = false

    private val updateTimeRunnable = object : Runnable {
        override fun run() {
            if (isRunning) {
                seconds++
                updateDurationText()
                handler.postDelayed(this, 1000)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_call_phone)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.root)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvCallDuration = findViewById(R.id.tvCallDuration)
        updateDurationText()

        setupListeners()
        startTimer()
    }

    private fun updateDurationText() {
        val minutes = seconds / 60
        val secs = seconds % 60
        tvCallDuration.text = String.format(Locale.getDefault(), "%02d:%02d", minutes, secs)
    }

    private fun startTimer() {
        isRunning = true
        handler.post(updateTimeRunnable)
    }

    private fun stopTimer() {
        isRunning = false
        handler.removeCallbacks(updateTimeRunnable)
    }

    private fun setupListeners() {
        // 返回按钮
        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            stopTimer()
            finish()
        }

        // 录音
        findViewById<android.widget.LinearLayout>(R.id.btnRecord).setOnClickListener {
            Toast.makeText(this, "录音", Toast.LENGTH_SHORT).show()
        }

        // 等待
        findViewById<android.widget.LinearLayout>(R.id.btnHold).setOnClickListener {
            Toast.makeText(this, "等待", Toast.LENGTH_SHORT).show()
        }

        // 备忘录
        findViewById<android.widget.LinearLayout>(R.id.btnMemo).setOnClickListener {
            Toast.makeText(this, "备忘录", Toast.LENGTH_SHORT).show()
        }

        // 添加通话
        findViewById<android.widget.LinearLayout>(R.id.btnAddCall).setOnClickListener {
            Toast.makeText(this, "添加通话", Toast.LENGTH_SHORT).show()
        }

        // 静音
        findViewById<android.widget.LinearLayout>(R.id.btnMute).setOnClickListener {
            Toast.makeText(this, "静音", Toast.LENGTH_SHORT).show()
        }

        // 联系人
        findViewById<android.widget.LinearLayout>(R.id.btnContacts).setOnClickListener {
            Toast.makeText(this, "联系人", Toast.LENGTH_SHORT).show()
        }

        // 扬声器
        findViewById<ImageView>(R.id.btnSpeaker).setOnClickListener {
            Toast.makeText(this, "扬声器", Toast.LENGTH_SHORT).show()
        }

        // 挂断
        findViewById<Button>(R.id.btnHangup).setOnClickListener {
            stopTimer()
            Toast.makeText(this, "通话结束", Toast.LENGTH_SHORT).show()
            finish()
        }

        // 更多
        findViewById<ImageView>(R.id.btnMore).setOnClickListener {
            Toast.makeText(this, "更多选项", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopTimer()
    }
}
