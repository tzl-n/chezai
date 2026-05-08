package com.example.chezai

import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

import com.example.chezai.R

class CarEnvironmentControlActivity : AppCompatActivity() {
    
    private lateinit var tabContainer: LinearLayout
    private var currentTabIndex = 0
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_car_environment_control)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.root)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        
        setupClickListeners()
        setupTabs()
    }
    
    private fun setupClickListeners() {
        // 返回按钮
        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }
        
    }
    
    private fun setupTabs() {
        tabContainer = findViewById(R.id.tabContainer)
        
        val tabs = listOf("座椅加热", "温度调节", "座椅调节", "方向盘")
        
        tabs.forEachIndexed { index, text ->
            val tabView = layoutInflater.inflate(R.layout.tab_item, tabContainer, false) as TextView
            tabView.text = text
            tabView.setPadding(
                resources.getDimensionPixelSize(R.dimen.tab_padding_horizontal),
                resources.getDimensionPixelSize(R.dimen.tab_padding_vertical),
                resources.getDimensionPixelSize(R.dimen.tab_padding_horizontal),
                resources.getDimensionPixelSize(R.dimen.tab_padding_vertical)
            )
            
            // 设置初始样式
            if (index == 0) {
                tabView.setTextColor(Color.WHITE)
                tabView.setBackgroundResource(R.drawable.bg_tab_selected)
            } else {
                tabView.setTextColor(ContextCompat.getColor(this, R.color.tab_unselected))
                tabView.setBackgroundResource(R.drawable.bg_tab_unselected)
            }
            
            // 设置 margin
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(0, 0, resources.getDimensionPixelSize(R.dimen.tab_margin), 0)
            tabView.layoutParams = params
            
            // 点击事件
            val finalIndex = index
            tabView.setOnClickListener {
                switchTab(finalIndex)
            }
            
            tabContainer.addView(tabView)
        }
        
        // 默认显示第一个 Fragment
        loadFragment(SeatHeatingFragment())
    }
    
    private fun switchTab(index: Int) {
        if (currentTabIndex == index) return
        
        currentTabIndex = index
        
        // 更新所有 Tab 样式
        for (i in 0 until tabContainer.childCount) {
            val tabView = tabContainer.getChildAt(i) as TextView
            if (i == index) {
                tabView.setTextColor(Color.WHITE)
                tabView.setBackgroundResource(R.drawable.bg_tab_selected)
            } else {
                tabView.setTextColor(ContextCompat.getColor(this, R.color.tab_unselected))
                tabView.setBackgroundResource(R.drawable.bg_tab_unselected)
            }
        }
        
        // 加载对应 Fragment
        when (index) {
            0 -> loadFragment(SeatHeatingFragment())
            1 -> loadFragment(TemperatureControlFragment())
            2 -> loadFragment(SeatAdjustFragment())
            3 -> loadFragment(SteeringWheelFragment())
        }
    }
    
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}
