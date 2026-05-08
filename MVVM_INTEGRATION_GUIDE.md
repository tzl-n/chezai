#  MVVM 完整集成指南

##  已完成的工作

### 1. ✅ API 接口对接
所有 7 个接口已在 ApiService 中定义并可用：
- ✅ `/api/vehicle` - 车辆状态
- ✅ `/api/charging` - 充电信息
- ✅ `/api/keys` - 钥匙信息
- ✅ `/api/hvac` - 空调控制
- ✅ `/api/ota` - 系统升级
- ✅ `/api/call` - 通话信息
- ✅ `/api/security-logs` - 安全日志

### 2. ✅ 数据模型创建
已在 Models.kt 中创建所有数据类：
- CarInfo - 车辆信息
- ChargingInfo - 充电信息
- KeyInfo - 钥匙信息
- HVACRequest/Response - 空调控制
- OTARequest/Response - 系统升级
- CallInfo - 通话信息
- SecurityLog - 安全日志

### 3. ✅ ViewModel 层
已创建 9 个 ViewModel：
- CarViewModel - 车辆管理
- ChargeViewModel - 充电管理
- KeyViewModel - 钥匙管理
- HVACViewModel - 空调控制
- OTAViewModel - OTA 升级
- CallViewModel - 通话管理
- SecurityViewModel - 安全日志
- UserViewModel - 用户管理
- MessageViewModel - 消息管理

### 4. ✅ 数据缓存机制
- **DataCacheManager.kt** - 支持内存缓存 + SharedPreferences 持久化
- 默认缓存时间：5 分钟
- 自动过期机制

### 5. ✅ 加载状态管理
- **LoadingStateView.kt** - 统一管理 Loading、Empty、Error 三种状态
- 加载进度条
- 空状态提示
- 错误状态 + 重试按钮

### 6. ✅ 下拉刷新
- SwipeRefreshLayout 已添加依赖
- 示例代码中已展示用法

## 📱 使用示例

### 示例 1: 在 Activity 中使用 ViewModel + 加载状态

```kotlin
class VehicleStatusActivity : AppCompatActivity() {
    
    private val carViewModel: CarViewModel by lazy {
        ViewModelProvider(this)[CarViewModel::class.java]
    }
    private lateinit var loadingStateView: LoadingStateView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // 1. 初始化 LoadingStateView
        loadingStateView = LoadingStateView(this)
        setContentView(loadingStateView)
        
        // 2. 观察数据
        carViewModel.carInfo.observe(this) { carInfo ->
            if (carInfo != null) {
                // 更新 UI
                txtPlateNumber.text = carInfo.plateNumber
                loadingStateView.showContent()
            } else {
                loadingStateView.showEmpty("暂无数据")
            }
        }
        
        // 3. 观察加载状态
        carViewModel.isLoading.observe(this) { isLoading ->
            if (isLoading) loadingStateView.showLoading()
        }
        
        // 4. 观察错误
        carViewModel.errorMessage.observe(this) { error ->
            if (error != null) {
                loadingStateView.showError(error) { loadData() }
            }
        }
        
        // 5. 加载数据
        loadData()
    }
    
    private fun loadData() {
        carViewModel.getCarInfo()
    }
}
```

### 示例 2: 添加数据缓存

```kotlin
class VehicleStatusActivity : AppCompatActivity() {
    
    private fun loadData(forceRefresh: Boolean = false) {
        if (!forceRefresh) {
            // 尝试从缓存获取
            val cached = DataCacheManager.getCachedData(
                this, 
                "vehicle_status", 
                CarInfo::class.java
            )
            if (cached != null) {
                _carInfo.value = cached
                return
            }
        }
        
        // 从网络获取
        carViewModel.getCarInfo()
            .onSuccess { carInfo ->
                // 缓存数据
                DataCacheManager.cacheData(this, "vehicle_status", carInfo)
            }
    }
}
```

### 示例 3: 下拉刷新

```kotlin
class VehicleStatusActivity : AppCompatActivity() {
    
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        swipeRefreshLayout.setOnRefreshListener {
            loadData(forceRefresh = true)
        }
        
        carViewModel.isLoading.observe(this) { isLoading ->
            swipeRefreshLayout.isRefreshing = isLoading
        }
    }
}
```

##  各页面集成建议

### 1. **车辆控制页面** (KeyManagementActivity)

```kotlin
class KeyManagementActivity : AppCompatActivity() {
    
    private val carViewModel: CarViewModel by lazy {
        ViewModelProvider(this)[CarViewModel::class.java]
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // 获取车辆信息
        carViewModel.carInfo.observe(this) { carInfo ->
            // 显示车辆状态
        }
        
        carViewModel.getCarInfo()
    }
}
```

### 2. **充电管理页面** (ChargingActivity)

```kotlin
class ChargingActivity : AppCompatActivity() {
    
    private val chargeViewModel: ChargeViewModel by lazy {
        ViewModelProvider(this)[ChargeViewModel::class.java]
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        chargeViewModel.chargingInfo.observe(this) { info ->
            // 更新充电状态
            txtChargingStatus.text = if (info.isCharging) "充电中" else "未充电"
            progressBar.progress = info.batteryLevel
        }
        
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

### 3. **钥匙管理页面** (KeyManagementActivity)

```kotlin
class KeyManagementActivity : AppCompatActivity() {
    
    private val keyViewModel: KeyViewModel by lazy {
        ViewModelProvider(this)[KeyViewModel::class.java]
    }
    private lateinit var adapter: KeyAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        keyViewModel.keys.observe(this) { keys ->
            adapter.submitList(keys)
        }
        
        keyViewModel.getKeys()
    }
}
```

### 4. **远程控制页面** (RemoteControlActivity)

```kotlin
class RemoteControlActivity : AppCompatActivity() {
    
    private val carViewModel: CarViewModel by lazy {
        ViewModelProvider(this)[CarViewModel::class.java]
    }
    private val hvacViewModel: HVACViewModel by lazy {
        ViewModelProvider(this)[HVACViewModel::class.java]
    }
    
    private fun controlEngine() {
        // 控制发动机
    }
    
    private fun controlLock() {
        carViewModel.toggleLock(true)
    }
    
    private fun controlAC() {
        hvacViewModel.controlHVAC("open", 24, "cool")
    }
}
```

### 5. **消息管理页面** (MessageManageActivity)

```kotlin
class MessageManageActivity : AppCompatActivity() {
    
    private val messageViewModel: MessageViewModel by lazy {
        ViewModelProvider(this)[MessageViewModel::class.java]
    }
    private lateinit var adapter: MessageAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        messageViewModel.messages.observe(this) { messages ->
            adapter.submitList(messages)
        }
        
        messageViewModel.getMessages()
    }
}
```

##  快速集成模板

复制以下代码到您的 Activity：

```kotlin
class YourActivity : AppCompatActivity() {
    
    // 1. 声明 ViewModel
    private val viewModel: YourViewModel by lazy {
        ViewModelProvider(this)[YourViewModel::class.java]
    }
    
    // 2. 声明 UI 组件
    private lateinit var loadingStateView: LoadingStateView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // 3. 初始化 LoadingStateView
        loadingStateView = LoadingStateView(this)
        setContentView(loadingStateView)
        
        // 4. 初始化 UI
        initViews()
        
        // 5. 观察 ViewModel
        observeViewModel()
        
        // 6. 加载数据
        loadData()
    }
    
    private fun initViews() {
        // 添加 SwipeRefreshLayout
        // 添加内容视图
        swipeRefreshLayout.setOnRefreshListener { loadData() }
    }
    
    private fun observeViewModel() {
        viewModel.data.observe(this) { data ->
            if (data != null) {
                updateUI(data)
                loadingStateView.showContent()
                swipeRefreshLayout.isRefreshing = false
            } else {
                loadingStateView.showEmpty()
            }
        }
        
        viewModel.isLoading.observe(this) { isLoading ->
            if (isLoading) loadingStateView.showLoading()
        }
        
        viewModel.errorMessage.observe(this) { error ->
            if (error != null) {
                ToastUtils.showError(this, error)
                loadingStateView.showError(error) { loadData() }
                swipeRefreshLayout.isRefreshing = false
            }
        }
    }
    
    private fun loadData() {
        viewModel.getData()
    }
    
    private fun updateUI(data: YourDataType) {
        // 更新 UI
    }
}
```

## 🔧 自定义 ViewModel 方法

在您的 ViewModel 中添加方法：

```kotlin
class YourViewModel : BaseViewModel() {
    
    private val _data = MutableLiveData<YourDataType>()
    val data: LiveData<YourDataType> = _data
    
    fun getData() {
        safeApiCall(
            apiCall = {
                // 1. 尝试缓存
                val cached = DataCacheManager.getCachedData(
                    appContext, // 需要注入 Context
                    "your_key",
                    YourDataType::class.java
                )
                if (cached != null) return@safeApiCall cached
                
                // 2. 从网络获取
                val response = ApiClient.apiService.yourApi()
                ApiHelper.handleResponse(response)
            },
            onSuccess = { result ->
                _data.value = result
                
                // 3. 缓存数据
                DataCacheManager.cacheData(
                    appContext,
                    "your_key",
                    result
                )
            }
        )
    }
}
```

## ✅ 检查清单

在集成到每个页面时，检查以下项目：

- [ ] 声明对应的 ViewModel
- [ ] 使用 LoadingStateView 管理加载状态
- [ ] 添加 SwipeRefreshLayout 支持下拉刷新
- [ ] 观察 ViewModel 的 LiveData
- [ ] 处理 loading、data、empty、error 四种状态
- [ ] 实现重试逻辑
- [ ] （可选）添加数据缓存

##  完整功能对照表

| 功能 | 状态 | 文件位置 |
|-----|------|----------|
| API 接口定义 | ✅ 完成 | network/ApiService.kt |
| 数据模型 | ✅ 完成 | data/model/Models.kt |
| Repository 层 | ✅ 完成 | repository/Repository.kt |
| BaseViewModel | ✅ 完成 | viewmodel/BaseViewModel.kt |
| CarViewModel | ✅ 完成 | viewmodel/CarViewModel.kt |
| ChargeViewModel | ✅ 完成 | viewmodel/ChargeViewModel.kt |
| KeyViewModel | ✅ 完成 | viewmodel/KeyViewModel.kt |
| HVACViewModel | ✅ 完成 | viewmodel/HVACViewModel.kt |
| OTAViewModel | ✅ 完成 | viewmodel/OTAViewModel.kt |
| CallViewModel | ✅ 完成 | viewmodel/CallViewModel.kt |
| SecurityViewModel | ✅ 完成 | viewmodel/SecurityViewModel.kt |
| 数据缓存 | ✅ 完成 | utils/DataCacheManager.kt |
| 加载状态管理 | ✅ 完成 | utils/LoadingStateView.kt |
| 下拉刷新 | ✅ 完成 | build.gradle.kts + 示例 |
| Toast 工具 | ✅ 完成 | utils/ToastUtils.kt |
| 完整示例 | ✅ 完成 | example/VehicleStatusExampleActivity.kt |

## 🚀 下一步

1. **选择要集成的页面**（建议从 VehicleStatusExampleActivity 开始）
2. **复制示例代码**并根据实际需求调整
3. **测试 API 调用**，查看返回数据格式
4. **根据实际 JSON 结构**调整 Models.kt 中的数据类
5. **逐个页面集成** ViewModel

## 💡 最佳实践

1. **一个页面对应一个主 ViewModel**
2. **使用 LoadingStateView** 统一管理加载状态
3. **添加下拉刷新** 提升用户体验
4. **重要数据使用缓存** 减少网络请求
5. **统一错误处理** 使用 BaseViewModel.safeApiCall()
6. **观察 LiveData 时使用 viewLifecycleOwner**（Fragment 中）

## 📞 需要帮助？

如需为特定页面实现完整的 MVVM 集成，请告诉我页面名称，我可以：
1. 生成完整的 Activity 代码
2. 对接相应的 ViewModel
3. 添加加载状态和下拉刷新
4. 实现数据缓存
