package com.example.chezai

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SystemUpgradeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_system_upgrade)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.systemUpgradeRoot)) { v, insets ->
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
        
        // 更多按钮
        findViewById<ImageView>(R.id.btnMore).setOnClickListener {
            Toast.makeText(this, "更多选项", Toast.LENGTH_SHORT).show()
        }
        
        // 更新按钮
        findViewById<Button>(R.id.btnUpdate).setOnClickListener {
            Toast.makeText(this, "开始更新", Toast.LENGTH_SHORT).show()
        }
        
        // 系统更新开关
        findViewById<Button>(R.id.btnSystemUpdate).setOnClickListener {
            toggleButton(it as Button)
        }
        
        // 安全响应与系统文件开关
        findViewById<Button>(R.id.btnSecurityUpdate).setOnClickListener {
            toggleButton(it as Button)
        }
        
        // 系统自动下载开关
        findViewById<Button>(R.id.btnAutoDownload).setOnClickListener {
            toggleButton(it as Button)
        }
    }
    
    private fun toggleButton(button: Button) {
        if (button.text == "关闭") {
            button.text = "开启"
            Toast.makeText(this, "${getButtonLabel(button)}已开启", Toast.LENGTH_SHORT).show()
        } else {
            button.text = "关闭"
            Toast.makeText(this, "${getButtonLabel(button)}已关闭", Toast.LENGTH_SHORT).show()
        }
    }
    
    private fun getButtonLabel(button: Button): String {
        return when (button.id) {
            R.id.btnSystemUpdate -> "系统更新"
            R.id.btnSecurityUpdate -> "安全响应与系统文件"
            R.id.btnAutoDownload -> "系统自动下载"
            else -> ""
        }
    }
}
