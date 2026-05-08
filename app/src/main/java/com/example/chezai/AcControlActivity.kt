package com.example.chezai

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class AcControlActivity : AppCompatActivity() {

    private var steeringHeatOn = true
    private var seatHeatOn = false
    private var seatVentOn = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ac_control)

        setupClickListeners()
    }

    private fun setupClickListeners() {
        // 关闭按钮
        findViewById<View>(R.id.btn_close).setOnClickListener {
            finish()
        }

        // 温度调节按钮
        findViewById<View>(R.id.btn_temp_minus).setOnClickListener {
            Toast.makeText(this, "温度-", Toast.LENGTH_SHORT).show()
        }
        findViewById<View>(R.id.btn_temp_plus).setOnClickListener {
            Toast.makeText(this, "温度+", Toast.LENGTH_SHORT).show()
        }

        // 功能开关
        findViewById<View>(R.id.btn_steering_heat).setOnClickListener {
            steeringHeatOn = !steeringHeatOn
            updateSwitchState(R.id.icon_steering_heat, steeringHeatOn)
            Toast.makeText(this, if (steeringHeatOn) "方向盘加热已开启" else "方向盘加热已关闭", Toast.LENGTH_SHORT).show()
        }

        findViewById<View>(R.id.btn_seat_heat).setOnClickListener {
            seatHeatOn = !seatHeatOn
            updateSwitchState(R.id.icon_seat_heat, seatHeatOn)
            Toast.makeText(this, if (seatHeatOn) "座椅加热已开启" else "座椅加热已关闭", Toast.LENGTH_SHORT).show()
        }

        findViewById<View>(R.id.btn_seat_vent).setOnClickListener {
            seatVentOn = !seatVentOn
            updateSwitchState(R.id.icon_seat_vent, seatVentOn)
            Toast.makeText(this, if (seatVentOn) "座椅通风已开启" else "座椅通风已关闭", Toast.LENGTH_SHORT).show()
        }

        // 主驾副驾选择
        findViewById<View>(R.id.btn_driver).setOnClickListener {
            Toast.makeText(this, "主驾", Toast.LENGTH_SHORT).show()
        }
        findViewById<View>(R.id.btn_passenger).setOnClickListener {
            Toast.makeText(this, "副驾", Toast.LENGTH_SHORT).show()
        }

        // 底部按钮
        findViewById<View>(R.id.btn_add_reserve).setOnClickListener {
            Toast.makeText(this, "加入预约", Toast.LENGTH_SHORT).show()
        }
        findViewById<View>(R.id.btn_turn_on).setOnClickListener {
            Toast.makeText(this, "立即开启", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateSwitchState(iconId: Int, isOn: Boolean) {
        val icon = findViewById<ImageView>(iconId)
        if (isOn) {
            icon.setImageResource(R.drawable.ic_check_green)
        } else {
            icon.setImageResource(R.drawable.ic_uncheck_gray)
        }
    }
}