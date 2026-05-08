package com.example.chezai

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SearchParkingActivity : AppCompatActivity() {
    
    private var isSearching = false
    private var rotateAnimation: RotateAnimation? = null
    private val handler = Handler(Looper.getMainLooper())
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_search_parking)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.searchParkingRoot)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupClickListeners()
        startSearching()
    }

    private fun setupClickListeners() {
        // 返回按钮
        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        // 停止检索按钮
        findViewById<Button>(R.id.btnStopSearch).setOnClickListener {
            if (isSearching) {
                stopSearching()
            } else {
                startSearching()
            }
        }
    }

    private fun startSearching() {
        isSearching = true
        
        // 开始旋转动画
        val radarImage = findViewById<ImageView>(R.id.ivRadar)
        rotateAnimation = RotateAnimation(
            0f, 360f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        ).apply {
            duration = 3000
            repeatCount = Animation.INFINITE
        }
        radarImage.startAnimation(rotateAnimation)
        
        // 更新按钮文字
        findViewById<Button>(R.id.btnStopSearch).text = "停止检索"
        findViewById<TextView>(R.id.tvSearchStatus).text = "正在搜索车位"
        findViewById<TextView>(R.id.tvSearchHint).text = "请稍后..."
    }

    private fun stopSearching() {
        isSearching = false
        
        // 停止动画
        findViewById<ImageView>(R.id.ivRadar).clearAnimation()
        
        // 更新按钮文字
        findViewById<Button>(R.id.btnStopSearch).text = "开始检索"
        findViewById<TextView>(R.id.tvSearchStatus).text = "已停止搜索"
        findViewById<TextView>(R.id.tvSearchHint).text = ""
        
        Toast.makeText(this, "已停止检索", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        rotateAnimation?.cancel()
        handler.removeCallbacksAndMessages(null)
    }
}
