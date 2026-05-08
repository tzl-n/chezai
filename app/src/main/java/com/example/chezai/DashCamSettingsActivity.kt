package com.example.chezai

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Switch
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DashCamSettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dash_cam_settings)

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

        // 开机提示音开关
        findViewById<Switch>(R.id.switchBootSound).setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(this, if (isChecked) "开机提示音开启" else "开机提示音关闭", Toast.LENGTH_SHORT).show()
        }

        // 本地语音控制开关
        findViewById<Switch>(R.id.switchVoiceControl).setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(this, if (isChecked) "本地语音控制开启" else "本地语音控制关闭", Toast.LENGTH_SHORT).show()
        }

        // H.265 视频编码开关
        findViewById<Switch>(R.id.switchH265).setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(this, if (isChecked) "H.265 编码开启" else "H.265 编码关闭", Toast.LENGTH_SHORT).show()
        }

        // 麦克风录音开关
        findViewById<Switch>(R.id.switchMic).setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(this, if (isChecked) "麦克风录音开启" else "麦克风录音关闭", Toast.LENGTH_SHORT).show()
        }

        // 显示速度开关
        findViewById<Switch>(R.id.switchSpeed).setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(this, if (isChecked) "显示速度开启" else "显示速度关闭", Toast.LENGTH_SHORT).show()
        }
    }
}
