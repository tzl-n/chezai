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

class ChargingStationListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_charging_station_list)

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
            Toast.makeText(this, "菜单", Toast.LENGTH_SHORT).show()
        }


        //点击跳转
        findViewById<LinearLayout>(R.id.car1).setOnClickListener {
            //跳转到ChargingMapActivity
            val intent = Intent(this, ChargingReservationActivity::class.java)
            startActivity(intent)
        }
        findViewById<LinearLayout>(R.id.car2).setOnClickListener {
            //跳转到ChargingMapActivity
            val intent = Intent(this, ChargingReservationActivity::class.java)
            startActivity(intent)
        }
        findViewById<LinearLayout>(R.id.car3).setOnClickListener {
            //跳转到ChargingMapActivity
            val intent = Intent(this, ChargingReservationActivity::class.java)
            startActivity(intent)
        }
        findViewById<LinearLayout>(R.id.car4).setOnClickListener {
            //跳转到ChargingMapActivity
            val intent = Intent(this, ChargingReservationActivity::class.java)
            startActivity(intent)
        }


        // 卡片 1 按钮
        findViewById<LinearLayout>(R.id.btnReserve1).setOnClickListener {
            Toast.makeText(this, "预约充电桩 1", Toast.LENGTH_SHORT).show()
        }
        findViewById<LinearLayout>(R.id.btnNavigate1).setOnClickListener {
            Toast.makeText(this, "导航到充电桩 1", Toast.LENGTH_SHORT).show()
        }

        // 卡片 2 按钮
        findViewById<LinearLayout>(R.id.btnReserve2).setOnClickListener {
            Toast.makeText(this, "预约充电桩 2", Toast.LENGTH_SHORT).show()
        }
        findViewById<LinearLayout>(R.id.btnNavigate2).setOnClickListener {
            Toast.makeText(this, "导航到充电桩 2", Toast.LENGTH_SHORT).show()
        }

        // 卡片 3 按钮
        findViewById<LinearLayout>(R.id.btnReserve3).setOnClickListener {
            Toast.makeText(this, "预约充电桩 3", Toast.LENGTH_SHORT).show()
        }
        findViewById<LinearLayout>(R.id.btnNavigate3).setOnClickListener {
            Toast.makeText(this, "导航到充电桩 3", Toast.LENGTH_SHORT).show()
        }

        // 卡片 4 按钮
        findViewById<LinearLayout>(R.id.btnReserve4).setOnClickListener {
            Toast.makeText(this, "预约充电桩 4", Toast.LENGTH_SHORT).show()
        }
        findViewById<LinearLayout>(R.id.btnNavigate4).setOnClickListener {
            Toast.makeText(this, "导航到充电桩 4", Toast.LENGTH_SHORT).show()
        }
    }
}
