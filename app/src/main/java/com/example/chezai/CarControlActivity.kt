package com.example.chezai

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CarControlActivity : AppCompatActivity() {
    private var temperature = 23.0f
    private lateinit var tvTemperature: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_car_control)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 初始化视图
        tvTemperature = findViewById(R.id.tv_temperature)

        // 设置功能按钮
        setupFunctionButton(R.id.func_lock, R.drawable.ic_lock, "车锁", true, false)
        setupFunctionButton(R.id.func_control, R.drawable.ic_car, "控制", false, true)
        setupFunctionButton(R.id.func_window, R.drawable.ic_window, "车窗")
        setupFunctionButton(R.id.func_flash, R.drawable.ic_flash, "闪灯鸣笛")

        // 温度调节
        setupTemperatureControl()
    }

    private fun setupFunctionButton(functionId: Int, iconId: Int, labelText: String, navigateToLock: Boolean = false, navigateToControl: Boolean = false) {
        val funcLayout = findViewById<android.widget.LinearLayout>(functionId)
        funcLayout?.let {
            val icon = it.findViewById<ImageView>(R.id.icon_func)
            val text = it.findViewById<TextView>(R.id.text_func)
            icon.setImageResource(iconId)
            text.text = labelText

            // 添加点击事件
            it.setOnClickListener {
                when {
                    navigateToLock -> {
                        // 跳转到车辆锁止界面
                        val intent = Intent(this, CarLockActivity::class.java)
                        startActivity(intent)
                    }
                    navigateToControl -> {
                        // 跳转到车辆控制详情界面
                        val intent = Intent(this, CarControlDetailActivity::class.java)
                        startActivity(intent)
                    }
                    else -> {
                        Toast.makeText(this, "点击了$labelText", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun setupTemperatureControl() {
        val btnMinus = findViewById<ImageView>(R.id.btn_minus)
        val btnPlus = findViewById<ImageView>(R.id.btn_plus)

        btnMinus.setOnClickListener {
            if (temperature > 16.0f) {
                temperature -= 0.5f
                tvTemperature.text = String.format("%.1f", temperature)
            }
        }

        btnPlus.setOnClickListener {
            if (temperature < 32.0f) {
                temperature += 0.5f
                tvTemperature.text = String.format("%.1f", temperature)
            }
        }
    }
}
