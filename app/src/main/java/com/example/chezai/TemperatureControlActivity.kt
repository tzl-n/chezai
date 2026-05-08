package com.example.chezai

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TemperatureControlActivity : AppCompatActivity() {

    private var isStarted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_temperature_control)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupPickers()
        setupActions()
    }

    private fun setupPickers() {
        val values = arrayOf(4, 5, 10, 15, 20, 25, 30)

        findViewById<NumberPicker>(R.id.pickerHour).apply {
            // 定义你想要显示的数字
            val values = arrayOf(4, 5, 10, 15, 20, 25, 30)
            // 定义显示的文字（加"分钟"后缀）
            val displayTexts = arrayOf("4分钟", "5分钟", "10分钟", "15分钟", "20分钟", "25分钟", "30分钟")

            minValue = 0
            maxValue = values.size - 1  // 6（因为有7个选项）
            value = 0                    // 默认选中第一个（4分钟）
            wrapSelectorWheel = false

            // 显示自定义文字
            displayedValues = displayTexts

            setOnValueChangedListener { picker, oldVal, newVal ->
                val selectedValue = values[newVal]
                // selectedValue 就是选中的数字：4,5,10,15,20,25,30
            }
        }
    }

    private fun setupActions() {
        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        findViewById<Button>(R.id.btnStart).setOnClickListener { button ->
            if (!isStarted) {
                isStarted = true
                findViewById<TextView>(R.id.tvCurrentStatus).text = "当前情况： 已开启"
                (button as Button).text = "已开启"
                Toast.makeText(this, "已开启温度调节", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
