package com.example.chezai

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ChargingReservationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_charging_reservation)

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

        // 价格详情
        findViewById<TextView>(R.id.btnPriceDetail).setOnClickListener {
            Toast.makeText(this, "价格详情", Toast.LENGTH_SHORT).show()
        }

        // 查看更多
        findViewById<TextView>(R.id.btnViewMore).setOnClickListener {
            Toast.makeText(this, "查看更多", Toast.LENGTH_SHORT).show()
        }

        // 预约按钮
        findViewById<Button>(R.id.btnReserve).setOnClickListener {
            Toast.makeText(this, "预约充电", Toast.LENGTH_SHORT).show()
        }

        // 导航按钮
        findViewById<Button>(R.id.btnNavigate).setOnClickListener {
            Toast.makeText(this, "导航到充电站", Toast.LENGTH_SHORT).show()
        }
    }
}
