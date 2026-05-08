package com.example.chezai

import android.content.Intent
import android.os.Bundle
<<<<<<< HEAD
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.FrameLayout
=======
import android.os.Handler
import android.os.Looper
>>>>>>> tang
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
<<<<<<< HEAD
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        
        // 启动雷达扫描动画
        startRadarAnimation()
        
        // 设置进入按钮点击事件
        setupEnterButton()
=======

>>>>>>> tang
    }
    
    private fun startRadarAnimation() {
        val scanSweep = findViewById<android.view.View>(R.id.scanSweep)
        
        // 创建旋转动画
        val rotateAnimation = RotateAnimation(
            0f, 360f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )
        
        // 设置动画属性
        rotateAnimation.duration = 3000 // 3秒旋转一周
        rotateAnimation.repeatCount = Animation.INFINITE // 无限循环
        rotateAnimation.interpolator = android.view.animation.LinearInterpolator() // 匀速旋转
        
        // 启动动画
        scanSweep.startAnimation(rotateAnimation)
    }
    
    private fun setupEnterButton() {
        val enterButton = findViewById<FrameLayout>(R.id.enterButton)
        enterButton.setOnClickListener {
            // 跳转到主页面
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}
