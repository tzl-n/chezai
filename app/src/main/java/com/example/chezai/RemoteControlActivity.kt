package com.example.chezai

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RemoteControlActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_remote_control)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.remoteControlRoot)) { v, insets ->
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

        // 发动机开启
        findViewById<LinearLayout>(R.id.btnEngine).setOnClickListener {
            Toast.makeText(this, "发动机开启", Toast.LENGTH_SHORT).show()
        }

        // 车锁关闭
        findViewById<LinearLayout>(R.id.btnCarLock).setOnClickListener {
            Toast.makeText(this, "车锁关闭", Toast.LENGTH_SHORT).show()
        }

        // 后盖箱开启
        findViewById<LinearLayout>(R.id.btnTrunk).setOnClickListener {
            Toast.makeText(this, "后盖箱开启", Toast.LENGTH_SHORT).show()
        }

        // 近光灯关闭
        findViewById<LinearLayout>(R.id.btnLowBeam).setOnClickListener {
            Toast.makeText(this, "近光灯关闭", Toast.LENGTH_SHORT).show()
        }

        // 远光灯关闭
        findViewById<LinearLayout>(R.id.btnHighBeam).setOnClickListener {
            Toast.makeText(this, "远光灯关闭", Toast.LENGTH_SHORT).show()
        }

        // 车窗开启
        findViewById<LinearLayout>(R.id.btnWindow).setOnClickListener {
            Toast.makeText(this, "车窗开启", Toast.LENGTH_SHORT).show()
        }

        // 天窗开启
        findViewById<LinearLayout>(R.id.btnSunroof).setOnClickListener {
            Toast.makeText(this, "天窗开启", Toast.LENGTH_SHORT).show()
        }

        // 空调开启
        findViewById<LinearLayout>(R.id.btnAC).setOnClickListener {
            Toast.makeText(this, "空调开启", Toast.LENGTH_SHORT).show()
        }

        // 座椅加热关闭
        findViewById<LinearLayout>(R.id.btnSeatHeat).setOnClickListener {
            Toast.makeText(this, "座椅加热关闭", Toast.LENGTH_SHORT).show()
        }
    }
}
