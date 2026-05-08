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

class ChargingMapActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_charging_map)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupUI()
    }

    private fun setupUI() {
        // 菜单按钮
        findViewById<ImageView>(R.id.btnMenu).setOnClickListener {
            //跳转
            val intent = Intent(this, ChargingStationListActivity::class.java)
            startActivity(intent)
        }

        // 定位按钮
        findViewById<LinearLayout>(R.id.btnLocation).setOnClickListener {
            Toast.makeText(this, "定位", Toast.LENGTH_SHORT).show()
        }

        // 路况按钮
        findViewById<LinearLayout>(R.id.btnTraffic).setOnClickListener {
            Toast.makeText(this, "路况", Toast.LENGTH_SHORT).show()
        }

        // 筛选按钮
        findViewById<LinearLayout>(R.id.btnFilter).setOnClickListener {
            Toast.makeText(this, "筛选", Toast.LENGTH_SHORT).show()
        }

        // 预约按钮
        findViewById<LinearLayout>(R.id.btnReserve).setOnClickListener {
            Toast.makeText(this, "预约", Toast.LENGTH_SHORT).show()
        }

        // 导航按钮
        findViewById<LinearLayout>(R.id.btnNavigate).setOnClickListener {
            Toast.makeText(this, "导航", Toast.LENGTH_SHORT).show()
        }

        // 收藏按钮
        findViewById<LinearLayout>(R.id.btnFavorite).setOnClickListener {
            Toast.makeText(this, "收藏", Toast.LENGTH_SHORT).show()
        }
    }
}
