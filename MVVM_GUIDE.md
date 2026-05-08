# MVVM 架构使用指南

##  项目架构总览

本项目采用 **MVVM + Repository** 架构模式，实现了清晰的分层结构：

```
─────────────────────────────────────────┐
│            View Layer (UI)              │
│   Activity / Fragment / Layout XML      │
─────────────────┬───────────────────────
                  │ 观察 LiveData
─────────────────▼───────────────────────┐
│          ViewModel Layer                 │
│   CarViewModel, ChargeViewModel...      │
│   (感知生命周期，管理 UI 数据)           │
└─────────────────┬───────────────────────┘
                  │ 调用业务逻辑
┌─────────────────▼───────────────────────┐
│         Repository Layer                 │
│   Repository (统一数据访问接口)          │
└─────────────────┬───────────────────────┘
                  │ 调用 API
┌─────────────────▼───────────────────────┐
│          Network Layer                   │
│   ApiClient + ApiService + OkHttp       │
└─────────────────────────────────────────┘
```

##  核心组件说明

### 1. **网络层 (Network Layer)**
- **ApiClient.kt**: Retrofit 客户端配置
  - BASE_URL: `http://10.161.10.232:3000/`
  - 配置了日志拦截器
  - 超时时间: 30秒
  
- **ApiService.kt**: API 接口定义
  - 8个实际可用的 REST API 接口
  - 使用协程 suspend 函数

### 2. **数据仓库层 (Repository Layer)**
- **Repository.kt**: 统一数据访问入口
  - 整合所有 API 调用
  - 提供简洁的接口给 ViewModel
  - 使用 ApiHelper 统一处理响应

### 3. **ViewModel 层 (ViewModel Layer)**
- **BaseViewModel.kt**: 基础 ViewModel
  - `safeApiCall()`: 安全的 API 调用封装
  - 自动处理 loading 状态
  - 自动捕获和传递错误信息
  
- **各功能 ViewModel**:
  - CarViewModel: 车辆状态
  - ChargeViewModel: 充电管理
  - KeyViewModel: 钥匙管理
  - HVACViewModel: 空调控制
  - OTAViewModel: OTA 升级
  - CallViewModel: 通话管理
  - SecurityViewModel: 安全日志

### 4. **工具类 (Utils)**
- **ApiHelper.kt**: API 响应处理
- **ToastUtils.kt**: Toast 提示
- **PreferencesUtils.kt**: 本地存储
- **ViewExtensions.kt**: View 操作扩展

## 📝 使用示例

### 示例 1: 在 Activity 中获取车辆信息

```kotlin
class VehicleStatusActivity : AppCompatActivity() {
    
    // 1. 声明 ViewModel (使用委托属性)
    private val carViewModel: CarViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicle_status)
        
        // 2. 观察数据变化
        carViewModel.carInfo.observe(this) { carInfo ->
            // 更新 UI
            txtPlateNumber.text = carInfo.plateNumber
            txtBatteryLevel.text = "${carInfo.batteryLevel}%"
            txtRange.text = "${carInfo.range}km"
            txtSpeed.text = "${carInfo.speed}km/h"
        }
        
        // 3. 观察加载状态
        carViewModel.isLoading.observe(this) { isLoading ->
            progressBar.isVisible = isLoading
        }
        
        // 4. 观察错误信息
        carViewModel.errorMessage.observe(this) { error ->
            ToastUtils.showError(this, error)
        }
        
        // 5. 获取数据
        carViewModel.getCarInfo()
    }
}
```

### 示例 2: 控制空调

```kotlin
class HVACControlActivity : AppCompatActivity() {
    
    private val hvacViewModel: HVACViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // 观察空调控制结果
        hvacViewModel.hvacResponse.observe(this) { response ->
            if (response.success) {
                ToastUtils.showSuccess(this, "空调控制成功")
                updateUI(response)
            }
        }
    }
    
    private fun openAC() {
        // 调用 ViewModel 方法
        hvacViewModel.controlHVAC(
            action = "open",
            temperature = 24,
            mode = "cool"
        )
    }
    
    private fun closeAC() {
        hvacViewModel.controlHVAC(action = "close")
    }
}
```

### 示例 3: 充电管理

```kotlin
class ChargingActivity : AppCompatActivity() {
    
    private val chargeViewModel: ChargeViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // 观察充电信息
        chargeViewModel.chargingInfo.observe(this) { info ->
            updateChargingUI(info)
        }
        
        // 观察电量
        chargeViewModel.batteryLevel.observe(this) { level ->
            progressBar.progress = level
            txtBatteryLevel.text = "$level%"
        }
        
        // 获取充电信息
        chargeViewModel.getChargingInfo()
    }
    
    private fun startCharging() {
        chargeViewModel.startCharging()
    }
    
    private fun stopCharging() {
        chargeViewModel.stopCharging()
    }
}
```

### 示例 4: 钥匙管理

```kotlin
class KeyManagementActivity : AppCompatActivity() {
    
    private val keyViewModel: KeyViewModel by viewModels()
    private lateinit var adapter: KeyAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // 观察钥匙列表
        keyViewModel.keys.observe(this) { keys ->
            adapter.submitList(keys)
        }
        
        // 获取钥匙列表
        keyViewModel.getKeys()
    }
}
```

## 🔧 ViewModel 方法说明

### CarViewModel (车辆)
```kotlin
- getCarInfo(): 获取车辆状态
- toggleLock(lock: Boolean): 控制车锁
```

### ChargeViewModel (充电)
```kotlin
- getChargingInfo(): 获取充电信息
- startCharging(): 开始充电
- stopCharging(): 停止充电
```

### KeyViewModel (钥匙)
```kotlin
- getKeys(): 获取钥匙列表
```

### HVACViewModel (空调)
```kotlin
- controlHVAC(action, temperature?, mode?): 控制空调
```

### OTAViewModel (升级)
```kotlin
- updateOTA(version, forceUpdate?): 执行 OTA 升级
```

### CallViewModel (通话)
```kotlin
- getCallInfo(): 获取通话信息
```

### SecurityViewModel (安全)
```kotlin
- getSecurityLogs(): 获取安全日志
```

## 📱 在 Fragment 中使用

```kotlin
class VehicleFragment : Fragment(R.layout.fragment_vehicle) {
    
    private val carViewModel: CarViewModel by viewModels()
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        carViewModel.carInfo.observe(viewLifecycleOwner) { carInfo ->
            // 更新 UI
        }
        
        carViewModel.getCarInfo()
    }
}
```

## 🎨 UI 绑定建议

### 使用 DataBinding (推荐)

```xml
<layout>
    <data>
        <variable
            name="carInfo"
            type="com.example.chezai.data.model.CarInfo" />
    </data>
    
    <LinearLayout>
        <TextView
            android:text="@{carInfo.plateNumber}" />
        <TextView
            android:text="@{String.valueOf(carInfo.batteryLevel) + '%'}" />
    </LinearLayout>
</layout>
```

### 使用 ViewBinding

```kotlin
class VehicleActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityVehicleBinding
    private val carViewModel: CarViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVehicleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        carViewModel.carInfo.observe(this) { carInfo ->
            binding.txtPlateNumber.text = carInfo.plateNumber
            binding.txtBatteryLevel.text = "${carInfo.batteryLevel}%"
        }
    }
}
```

##  刷新数据

### 下拉刷新

```kotlin
swipeRefreshLayout.setOnRefreshListener {
    carViewModel.getCarInfo()
}

carViewModel.isLoading.observe(this) { isLoading ->
    swipeRefreshLayout.isRefreshing = isLoading
}
```

### 定时刷新

```kotlin
private val refreshHandler = Handler(Looper.getMainLooper())
private val refreshRunnable = object : Runnable {
    override fun run() {
        carViewModel.getCarInfo()
        refreshHandler.postDelayed(this, 5000) // 每5秒刷新
    }
}

override fun onResume() {
    super.onResume()
    refreshHandler.post(refreshRunnable)
}

override fun onPause() {
    super.onPause()
    refreshHandler.removeCallbacks(refreshRunnable)
}
```

## ⚠️ 注意事项

1. **ViewModel 生命周期**: ViewModel 会存活到关联的 Activity/Fragment 完全销毁
2. **避免内存泄漏**: 不要在 ViewModel 中持有 View 或 Context 的引用
3. **协程作用域**: ViewModel 中使用 `viewModelScope`，会自动在 ViewModel 销毁时取消
4. **LiveData 观察者**: 使用 `viewLifecycleOwner` 而不是 `this` (在 Fragment 中)
5. **错误处理**: BaseViewModel 已处理异常，通过 `errorMessage` LiveData 暴露

## 🚀 快速开始

1. **Sync Gradle** 下载依赖
2. **选择需要对接的页面**
3. **在 Activity 中声明对应的 ViewModel**
4. **观察 LiveData 并更新 UI**
5. **调用 ViewModel 方法获取/操作数据**

## 📚 相关文件

- [ApiClient.kt](app/src/main/java/com/example/chezai/network/ApiClient.kt) - 网络配置
- [Repository.kt](app/src/main/java/com/example/chezai/repository/Repository.kt) - 数据仓库
- [BaseViewModel.kt](app/src/main/java/com/example/chezai/viewmodel/BaseViewModel.kt) - 基础 ViewModel
- [PROJECT_STRUCTURE.md](PROJECT_STRUCTURE.md) - 项目结构说明
