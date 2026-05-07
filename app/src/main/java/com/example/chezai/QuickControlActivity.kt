package com.example.chezai

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Switch
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QuickControlActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quick_control)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        
        setupUI()
    }
    
    private fun setupUI() {
        // 返回按钮
        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }
        
        // 车辆信息按钮
        findViewById<Button>(R.id.btnVehicleInfo).setOnClickListener {
            Toast.makeText(this, "车辆信息", Toast.LENGTH_SHORT).show()
        }
        
        // 远程控制按钮
        findViewById<Button>(R.id.btnRemoteControl).setOnClickListener {
            Toast.makeText(this, "远程控制", Toast.LENGTH_SHORT).show()
        }
        
        // 车门开关
        findViewById<Switch>(R.id.switchDoor).setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(this, if (isChecked) "车门已开启" else "车门已关闭", Toast.LENGTH_SHORT).show()
        }
        
        // 车窗开关
        findViewById<Switch>(R.id.switchWindow).setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(this, if (isChecked) "车窗已开启" else "车窗已关闭", Toast.LENGTH_SHORT).show()
        }
        
        // 车灯开关
        findViewById<Switch>(R.id.switchLight).setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(this, if (isChecked) "车灯已开启" else "车灯已关闭", Toast.LENGTH_SHORT).show()
        }
    }
}
