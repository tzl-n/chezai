package com.example.chezai.example

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.chezai.R
import com.example.chezai.utils.LoadingStateView
import com.example.chezai.utils.ToastUtils
import com.example.chezai.viewmodel.CarViewModel

/**
 * 示例 Activity - 展示如何使用 MVVM + 加载状态 + 下拉刷新
 * 可以复制此模式到其他页面
 */
class VehicleStatusExampleActivity : AppCompatActivity() {
    
    // 1. 声明 ViewModel
    private val carViewModel: CarViewModel by lazy {
        ViewModelProvider(this)[CarViewModel::class.java]
    }
    
    private lateinit var loadingStateView: LoadingStateView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var txtPlateNumber: TextView
    private lateinit var txtBatteryLevel: TextView
    private lateinit var txtRange: TextView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        // 2. 初始化 LoadingStateView
        loadingStateView = LoadingStateView(this)
        setContentView(loadingStateView)
        
        ViewCompat.setOnApplyWindowInsetsListener(loadingStateView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        
        // 3. 初始化 UI
        initViews()
        
        // 4. 观察 ViewModel 数据
        observeViewModel()
        
        // 5. 加载数据
        loadData()
    }
    
    private fun initViews() {
        // 创建内容布局
        val contentLayout = loadingStateView.getContentContainer()
        
        val swipeRefresh = SwipeRefreshLayout(this).apply {
            layoutParams = android.widget.FrameLayout.LayoutParams(
                android.widget.FrameLayout.LayoutParams.MATCH_PARENT,
                android.widget.FrameLayout.LayoutParams.MATCH_PARENT
            )
        }
        contentLayout.addView(swipeRefresh)
        
        // 创建内容视图
        val contentView = layoutInflater.inflate(R.layout.layout_vehicle_info, swipeRefresh, false)
        swipeRefresh.addView(contentView)
        
        swipeRefreshLayout = swipeRefresh
        txtPlateNumber = contentView.findViewById(R.id.txtPlateNumber)
        txtBatteryLevel = contentView.findViewById(R.id.txtBatteryLevel)
        txtRange = contentView.findViewById(R.id.txtRange)
        
        // 设置下拉刷新
        swipeRefreshLayout.setOnRefreshListener {
            loadData()
        }
        
        // 设置重试按钮
        loadingStateView.getContentContainer()
    }
    
    private fun observeViewModel() {
        // 观察车辆信息
        carViewModel.carInfo.observe(this) { carInfo ->
            if (carInfo != null) {
                // 更新 UI
                txtPlateNumber.text = carInfo.plateNumber
                txtBatteryLevel.text = "${carInfo.batteryLevel}%"
                txtRange.text = "${carInfo.range}km"
                
                // 显示内容
                loadingStateView.showContent()
                swipeRefreshLayout.isRefreshing = false
            } else {
                // 显示空状态
                loadingStateView.showEmpty("暂无车辆信息")
                swipeRefreshLayout.isRefreshing = false
            }
        }
        
        // 观察加载状态
        carViewModel.isLoading.observe(this) { isLoading ->
            if (isLoading) {
                loadingStateView.showLoading()
            }
        }
        
        // 观察错误信息
        carViewModel.errorMessage.observe(this) { error ->
            if (error != null) {
                ToastUtils.showError(this, error)
                loadingStateView.showError(error) {
                    // 重试按钮点击
                    loadData()
                }
                swipeRefreshLayout.isRefreshing = false
            }
        }
    }
    
    private fun loadData() {
        // 开始加载
        if (!swipeRefreshLayout.isRefreshing) {
            loadingStateView.showLoading()
        }
        
        // 调用 ViewModel 获取数据
        carViewModel.getCarInfo()
    }
}
