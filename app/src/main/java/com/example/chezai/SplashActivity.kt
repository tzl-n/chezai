package com.example.chezai

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashActivity : AppCompatActivity() {

    private lateinit var btnMonth: TextView
    private lateinit var btnWeek: TextView
    private lateinit var btnDay: TextView
    private lateinit var ivCarModel: ImageView
    private var currentSelection = "day"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initViews()
        initChart()
        setupClickListeners()
    }

    private fun initViews() {
        btnMonth = findViewById(R.id.btnMonth)
        btnWeek = findViewById(R.id.btnWeek)
        btnDay = findViewById(R.id.btnDay)
        ivCarModel = findViewById(R.id.ivCarModel)
    }

    private fun initChart() {
        val donutChart = findViewById<DonutChartView>(R.id.donutChart)

        // 设置环形图数据
        val segments = listOf(
            DonutChartView.Segment(40f, Color.parseColor("#F5A623"), "驱动"),
            DonutChartView.Segment(28f, Color.parseColor("#FF0000"), "空调"),
            DonutChartView.Segment(22f, Color.parseColor("#FF6B35"), "电池"),
            DonutChartView.Segment(10f, Color.parseColor("#FF8C69"), "其他")
        )

        donutChart.setSegments(segments)
        donutChart.setCenterText("37%", "能量回收")
    }

    private fun setupClickListeners() {
        btnMonth.setOnClickListener {
            if (currentSelection != "month") {
                currentSelection = "month"
                updateButtonStyles()
                updateChartData("month")
                Toast.makeText(this, "已切换到月视图", Toast.LENGTH_SHORT).show()
            }
        }

        btnWeek.setOnClickListener {
            if (currentSelection != "week") {
                currentSelection = "week"
                updateButtonStyles()
                updateChartData("week")
                Toast.makeText(this, "已切换到周视图", Toast.LENGTH_SHORT).show()
            }
        }

        btnDay.setOnClickListener {
            if (currentSelection != "day") {
                currentSelection = "day"
                updateButtonStyles()
                updateChartData("day")
                Toast.makeText(this, "已切换到日视图", Toast.LENGTH_SHORT).show()
            }
        }

        // 点击汽车模型跳转到车载控制页面
        ivCarModel.setOnClickListener {
            val intent = Intent(this, CarControlActivity::class.java)
            startActivity(intent)
        }
    }

    private fun updateButtonStyles() {
        val selectedColor = Color.parseColor("#FF0000")
        val selectedTextColor = Color.parseColor("#FFFFFF")
        val unselectedColor = Color.parseColor("#F5F5F5")
        val unselectedTextColor = Color.parseColor("#666666")

        // 重置所有按钮样式
        btnMonth.setBackgroundColor(unselectedColor)
        btnMonth.setTextColor(unselectedTextColor)
        btnWeek.setBackgroundColor(unselectedColor)
        btnWeek.setTextColor(unselectedTextColor)
        btnDay.setBackgroundColor(unselectedColor)
        btnDay.setTextColor(unselectedTextColor)

        // 添加点击动画效果
        when (currentSelection) {
            "month" -> {
                btnMonth.setBackgroundColor(selectedColor)
                btnMonth.setTextColor(selectedTextColor)
                animateButton(btnMonth)
            }
            "week" -> {
                btnWeek.setBackgroundColor(selectedColor)
                btnWeek.setTextColor(selectedTextColor)
                animateButton(btnWeek)
            }
            "day" -> {
                btnDay.setBackgroundColor(selectedColor)
                btnDay.setTextColor(selectedTextColor)
                animateButton(btnDay)
            }
        }
    }

    private fun animateButton(button: TextView) {
        // 添加缩放动画效果
        button.animate()
            .scaleX(1.1f)
            .scaleY(1.1f)
            .setDuration(100)
            .withEndAction {
                button.animate()
                    .scaleX(1.0f)
                    .scaleY(1.0f)
                    .setDuration(100)
                    .start()
            }
            .start()
    }

    private fun updateChartData(period: String) {
        val donutChart = findViewById<DonutChartView>(R.id.donutChart)
        
        // 根据不同时间周期更新数据
        when (period) {
            "month" -> {
                val segments = listOf(
                    DonutChartView.Segment(35f, Color.parseColor("#F5A623"), "驱动"),
                    DonutChartView.Segment(30f, Color.parseColor("#FF0000"), "空调"),
                    DonutChartView.Segment(20f, Color.parseColor("#FF6B35"), "电池"),
                    DonutChartView.Segment(15f, Color.parseColor("#FF8C69"), "其他")
                )
                donutChart.setSegments(segments)
                donutChart.setCenterText("42%", "能量回收")
            }
            "week" -> {
                val segments = listOf(
                    DonutChartView.Segment(38f, Color.parseColor("#F5A623"), "驱动"),
                    DonutChartView.Segment(25f, Color.parseColor("#FF0000"), "空调"),
                    DonutChartView.Segment(24f, Color.parseColor("#FF6B35"), "电池"),
                    DonutChartView.Segment(13f, Color.parseColor("#FF8C69"), "其他")
                )
                donutChart.setSegments(segments)
                donutChart.setCenterText("39%", "能量回收")
            }
            "day" -> {
                val segments = listOf(
                    DonutChartView.Segment(40f, Color.parseColor("#F5A623"), "驱动"),
                    DonutChartView.Segment(28f, Color.parseColor("#FF0000"), "空调"),
                    DonutChartView.Segment(22f, Color.parseColor("#FF6B35"), "电池"),
                    DonutChartView.Segment(10f, Color.parseColor("#FF8C69"), "其他")
                )
                donutChart.setSegments(segments)
                donutChart.setCenterText("37%", "能量回收")
            }
        }
    }
}
