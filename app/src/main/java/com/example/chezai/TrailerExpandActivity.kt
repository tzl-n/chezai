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

class TrailerExpandActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_trailer_expand)

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

        // 菜单按钮
        findViewById<ImageView>(R.id.btnMenu).setOnClickListener {
            val intent = Intent(this, TrailerDarkModeActivity::class.java)
            startActivity(intent)
        }

        // 锁按钮
        findViewById<LinearLayout>(R.id.btnLock).setOnClickListener {
            Toast.makeText(this, "锁车/解锁", Toast.LENGTH_SHORT).show()
        }

        // 出租车按钮
        findViewById<LinearLayout>(R.id.btnTaxi).setOnClickListener {
            Toast.makeText(this, "出租车模式", Toast.LENGTH_SHORT).show()
        }

        // 相机按钮
        findViewById<LinearLayout>(R.id.btnCamera).setOnClickListener {
            Toast.makeText(this, "相机", Toast.LENGTH_SHORT).show()
        }

        // 磁铁按钮
        findViewById<LinearLayout>(R.id.btnMagnet).setOnClickListener {
            Toast.makeText(this, "磁铁功能", Toast.LENGTH_SHORT).show()
        }

        // 底部功能卡片点击事件
        // 安全卡片
        findViewById<androidx.cardview.widget.CardView>(R.id.cardSecurity).setOnClickListener {
            Toast.makeText(this, "安全功能", Toast.LENGTH_SHORT).show()
        }

        // 服务卡片
        findViewById<androidx.cardview.widget.CardView>(R.id.cardService).setOnClickListener {
            Toast.makeText(this, "服务功能", Toast.LENGTH_SHORT).show()
        }

        // 能耗卡片
        findViewById<androidx.cardview.widget.CardView>(R.id.cardEnergy).setOnClickListener {
            Toast.makeText(this, "能耗功能", Toast.LENGTH_SHORT).show()
        }
    }
}
