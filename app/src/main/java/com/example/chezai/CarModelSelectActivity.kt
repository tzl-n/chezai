package com.example.chezai

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CarModelSelectActivity : AppCompatActivity() {
    private lateinit var tabCar: LinearLayout
    private lateinit var tabVersion: LinearLayout
    private lateinit var tabOption: LinearLayout
    private lateinit var tabAppearance: LinearLayout
    private lateinit var tabInterior: LinearLayout
    
    private lateinit var tabCarText: TextView
    private lateinit var tabVersionText: TextView
    private lateinit var tabOptionText: TextView
    private lateinit var tabAppearanceText: TextView
    private lateinit var tabInteriorText: TextView
    
    private lateinit var tabCarLine: View
    private lateinit var tabVersionLine: View
    private lateinit var tabOptionLine: View
    private lateinit var tabAppearanceLine: View
    private lateinit var tabInteriorLine: View
    
    private lateinit var model1: LinearLayout
    private lateinit var model2: LinearLayout
    private lateinit var model1Border: LinearLayout
    private lateinit var model2Border: LinearLayout
    
    private lateinit var priceText: TextView
    
    private var selectedPrice = "19,8900"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_model_select)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 初始化标签页组件
        initTabs()
        
        // 初始化车型选择组件
        initModels()
        
        // 初始化价格显示
        priceText = findViewById(R.id.price_text)
        
        // 返回按钮点击事件
        findViewById<LinearLayout>(R.id.btn_back).setOnClickListener {
            finish()
        }
        
        // 选择版本按钮点击事件
        findViewById<LinearLayout>(R.id.btn_select_version).setOnClickListener {
            val intent = Intent(this, CarDrivingActivity::class.java)
            startActivity(intent)
        }
        
        // 标签页点击事件
        tabCar.setOnClickListener { selectTab(0) }
        tabVersion.setOnClickListener { selectTab(1) }
        tabOption.setOnClickListener { selectTab(2) }
        tabAppearance.setOnClickListener { selectTab(3) }
        tabInterior.setOnClickListener { selectTab(4) }
        
        // 车型选择点击事件
        model1.setOnClickListener { selectModel(1) }
        model2.setOnClickListener { selectModel(2) }
    }
    
    private fun initTabs() {
        tabCar = findViewById(R.id.tab_car)
        tabVersion = findViewById(R.id.tab_version)
        tabOption = findViewById(R.id.tab_option)
        tabAppearance = findViewById(R.id.tab_appearance)
        tabInterior = findViewById(R.id.tab_interior)
        
        tabCarText = findViewById(R.id.tab_car_text)
        tabVersionText = findViewById(R.id.tab_version_text)
        tabOptionText = findViewById(R.id.tab_option_text)
        tabAppearanceText = findViewById(R.id.tab_appearance_text)
        tabInteriorText = findViewById(R.id.tab_interior_text)
        
        tabCarLine = findViewById(R.id.tab_car_line)
        tabVersionLine = findViewById(R.id.tab_version_line)
        tabOptionLine = findViewById(R.id.tab_option_line)
        tabAppearanceLine = findViewById(R.id.tab_appearance_line)
        tabInteriorLine = findViewById(R.id.tab_interior_line)
    }
    
    private fun initModels() {
        model1 = findViewById(R.id.model1)
        model2 = findViewById(R.id.model2)
        model1Border = findViewById(R.id.model1_border)
        model2Border = findViewById(R.id.model2_border)
    }
    
    private fun selectTab(index: Int) {
        // 重置所有标签
        resetTabs()
        
        // 设置选中状态
        when (index) {
            0 -> {
                tabCarText.setTextColor(getColor(R.color.red))
                tabCarLine.visibility = View.VISIBLE
            }
            1 -> {
                tabVersionText.setTextColor(getColor(R.color.red))
                tabVersionLine.visibility = View.VISIBLE
            }
            2 -> {
                tabOptionText.setTextColor(getColor(R.color.red))
                tabOptionLine.visibility = View.VISIBLE
            }
            3 -> {
                tabAppearanceText.setTextColor(getColor(R.color.red))
                tabAppearanceLine.visibility = View.VISIBLE
            }
            4 -> {
                tabInteriorText.setTextColor(getColor(R.color.red))
                tabInteriorLine.visibility = View.VISIBLE
            }
        }
    }
    
    private fun resetTabs() {
        tabCarText.setTextColor(getColor(R.color.grey))
        tabVersionText.setTextColor(getColor(R.color.grey))
        tabOptionText.setTextColor(getColor(R.color.grey))
        tabAppearanceText.setTextColor(getColor(R.color.grey))
        tabInteriorText.setTextColor(getColor(R.color.grey))
        
        tabCarLine.visibility = View.GONE
        tabVersionLine.visibility = View.GONE
        tabOptionLine.visibility = View.GONE
        tabAppearanceLine.visibility = View.GONE
        tabInteriorLine.visibility = View.GONE
    }
    
    private fun selectModel(index: Int) {
        // 重置所有车型边框
        model1Border.setBackgroundColor(getColor(R.color.light_grey))
        model2Border.setBackgroundColor(getColor(R.color.light_grey))
        
        // 设置选中状态
        when (index) {
            1 -> {
                model1Border.setBackgroundColor(getColor(R.color.red))
                selectedPrice = "19,8900"
            }
            2 -> {
                model2Border.setBackgroundColor(getColor(R.color.red))
                selectedPrice = "15,8900"
            }
        }
        
        // 更新价格显示
        priceText.text = selectedPrice
    }
}