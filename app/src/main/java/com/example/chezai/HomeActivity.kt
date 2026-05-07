package com.example.chezai

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.content_main)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.contentDrawerLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        
        setupDrawer()
        setupSidebarMenu()
    }
    
    private fun setupDrawer() {
        val drawerLayout = findViewById<DrawerLayout>(R.id.contentDrawerLayout)
        val headerTitle = findViewById<TextView>(R.id.headerTitle)
        
        // 点击标题打开侧边栏
        headerTitle.setOnClickListener {
            if (!drawerLayout.isDrawerOpen(findViewById(R.id.sidebarMenu))) {
                drawerLayout.openDrawer(findViewById(R.id.sidebarMenu))
            }
        }
    }
    
    private fun setupSidebarMenu() {
        val drawerLayout = findViewById<DrawerLayout>(R.id.contentDrawerLayout)
        
        // 消息
        findViewById<LinearLayout>(R.id.menuMessage).setOnClickListener {
            Toast.makeText(this, "消息", Toast.LENGTH_SHORT).show()
            drawerLayout.closeDrawers()
        }
        
        // 钥匙管理
        findViewById<LinearLayout>(R.id.menuKey).setOnClickListener {
            drawerLayout.closeDrawers()
            val intent = Intent(this, KeyManagementActivity::class.java)
            startActivity(intent)
        }
        
        // 系统升级
        findViewById<LinearLayout>(R.id.menuUpgrade).setOnClickListener {
            drawerLayout.closeDrawers()
            val intent = Intent(this, SystemUpgradeActivity::class.java)
            startActivity(intent)
        }
        
        // 智能充电
        findViewById<LinearLayout>(R.id.menuCharge).setOnClickListener {
            drawerLayout.closeDrawers()
            val intent = Intent(this, SmartChargeActivity::class.java)
            startActivity(intent)
        }
        
        // 快捷控车
        findViewById<LinearLayout>(R.id.menuControl).setOnClickListener {
            Toast.makeText(this, "快捷控车", Toast.LENGTH_SHORT).show()
            drawerLayout.closeDrawers()
        }
        
        // 活物监测
        findViewById<LinearLayout>(R.id.menuMonitor).setOnClickListener {
            Toast.makeText(this, "活物监测", Toast.LENGTH_SHORT).show()
            drawerLayout.closeDrawers()
        }
        
        // 日志管理
        findViewById<LinearLayout>(R.id.menuLog).setOnClickListener {
            Toast.makeText(this, "日志管理", Toast.LENGTH_SHORT).show()
            drawerLayout.closeDrawers()
        }
        
        // 地理标签
        findViewById<LinearLayout>(R.id.menuGeo).setOnClickListener {
            Toast.makeText(this, "地理标签", Toast.LENGTH_SHORT).show()
            drawerLayout.closeDrawers()
        }
        
        // 私密安全
        findViewById<LinearLayout>(R.id.menuPrivacy).setOnClickListener {
            Toast.makeText(this, "私密安全", Toast.LENGTH_SHORT).show()
            drawerLayout.closeDrawers()
        }
    }
}
