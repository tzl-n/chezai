# 车载 App 项目结构

## 架构说明

本项目采用 **MVVM (Model-View-ViewModel)** 架构模式，结合以下技术栈：

- **ViewModel**: 管理 UI 相关数据，感知生命周期
- **LiveData**: 可观察的数据持有类
- **OkHttp + Retrofit**: 网络请求框架
- **Kotlin Coroutines**: 异步编程
- **Glide**: 图片加载

## 项目目录结构

```
app/src/main/java/com/example/chezai/
├── network/                  # 网络层
│   ├── ApiClient.kt         # Retrofit 客户端配置 (BASE_URL: http://10.161.10.232:3000/)
│   └── ApiService.kt        # API 接口定义 (7个实际接口)
── data/
│   └── model/
│       └── Models.kt        # 数据模型类 (CarInfo, ChargingInfo, KeyInfo等)
├── viewmodel/               # ViewModel 层
│   ├── BaseViewModel.kt     # 基础 ViewModel (包含safeApiCall)
│   ├── CarViewModel.kt      # 车辆状态 (/api/vehicle)
│   ├── ChargeViewModel.kt   # 充电信息 (/api/charging)
│   ├── KeyViewModel.kt      # 钥匙信息 (/api/keys)
│   ├── HVACViewModel.kt     # 空调控制 (/api/hvac)
│   ├── OTAViewModel.kt      # 系统升级 (/api/ota)
│   ├── CallViewModel.kt     # 通话信息 (/api/call)
│   ├── SecurityViewModel.kt # 安全日志 (/api/security-logs)
│   ├── UserViewModel.kt     # 用户管理
│   └── MessageViewModel.kt  # 消息管理
├── utils/
│   └── ApiHelper.kt         # API 响应处理工具
└── [Activities]             # View 层 (UI 页面 - 24个)
```

## 已创建页面整理

### 1. 主页面
- **MainActivity.kt** - 应用启动页

### 2. 主页相关
- **HomeActivity.kt** - 主页面，包含侧边栏导航

### 3. 车辆控制
- **KeyManagementActivity.kt** - 钥匙管理页面
- **RemoteControlActivity.kt** - 远程控制页面
- **CarEnvironmentControlActivity.kt** - 车内环境控制页面
  - **SeatHeatingFragment.kt** - 座椅加热碎片
  - **TemperatureControlFragment.kt** - 温度调节碎片
  - **SeatAdjustFragment.kt** - 座椅调节碎片
  - **SteeringWheelFragment.kt** - 方向盘碎片

### 4. 挂车相关
- **TrailerExpandActivity.kt** - 挂车展开页面
- **TrailerDarkModeActivity.kt** - 挂车暗黑模式页面

### 5. 充电相关
- **SmartChargeActivity.kt** - 智能充电页面
- **ChargingActivity.kt** - 充电中页面
- **ChargeCompletedActivity.kt** - 充电完成页面

### 6. 行车记录仪
- **DashCamActivity.kt** - 行车记录仪页面
- **DashCamSettingsActivity.kt** - 记录仪设置页面

### 7. 消息管理
- **MessageManageActivity.kt** - 消息管理页面
- **MessageAdapter.kt** - 消息适配器
- **MessageItem.kt** - 消息数据类

### 8. 其他功能
- **CarOwnerServiceActivity.kt** - 车主服务页面
- **CallPhoneActivity.kt** - 呼叫手机页面
- **SystemUpgradeActivity.kt** - 系统升级页面
- **MapNavigationActivity.kt** - 地图导航页面
- **SearchParkingActivity.kt** - 检索车位页面

## API 接口说明

所有接口已配置为可访问：**http://10.161.10.232:3000**

| 接口路径 | 方法 | ViewModel | 说明 |
|---------|------|-----------|------|
| `/api/all` | GET | - | 获取所有数据 |
| `/api/vehicle` | GET | CarViewModel | 车辆状态 |
| `/api/charging` | GET | ChargeViewModel | 充电信息 |
| `/api/keys` | GET | KeyViewModel | 钥匙信息 |
| `/api/hvac` | POST | HVACViewModel | 空调控制 |
| `/api/ota` | POST | OTAViewModel | 系统升级 |
| `/api/call` | GET | CallViewModel | 通话信息 |
| `/api/security-logs` | GET | SecurityViewModel | 安全日志 |

## 使用示例

### 1. 在 Activity 中使用 ViewModel

```kotlin
class VehicleActivity : AppCompatActivity() {
    
    private val carViewModel: CarViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // 观察车辆数据
        carViewModel.carInfo.observe(this) { carInfo ->
            // 更新 UI
            txtPlateNumber.text = carInfo.plateNumber
            txtBatteryLevel.text = "${carInfo.batteryLevel}%"
            txtRange.text = "${carInfo.range}km"
        }
        
        // 观察加载状态
        carViewModel.isLoading.observe(this) { isLoading ->
            progressBar.isVisible = isLoading
        }
        
        // 观察错误信息
        carViewModel.errorMessage.observe(this) { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }
        
        // 获取车辆信息
        carViewModel.getCarInfo()
    }
}
```

### 2. 在 ViewModel 中调用 API

```kotlin
class CarViewModel : BaseViewModel() {
    
    fun getCarInfo() {
        safeApiCall(
            apiCall = {
                val response = ApiClient.apiService.getVehicleStatus()
                ApiHelper.handleResponse(response)
            },
            onSuccess = { carInfo ->
                _carInfo.value = carInfo
            }
        )
    }
}
```

## 依赖库版本

- OkHttp: 4.12.0
- Retrofit: 2.9.0
- Lifecycle: 2.6.2
- Coroutines: 1.7.3
- Glide: 4.16.0

## 数据模型

### 车辆信息 (CarInfo)
```kotlin
data class CarInfo(
    val id: String,              // 车辆ID
    val plateNumber: String,     // 车牌号
    val model: String,           // 车型
    val batteryLevel: Int,       // 电量百分比
    val fuelLevel: Int,          // 油量百分比
    val range: Int,              // 续航里程(km)
    val status: String,          // 车辆状态
    val speed: Int,              // 速度(km/h)
    val mileage: Int,            // 里程(km)
    val engineStatus: String,    // 发动机状态
    val lockStatus: String       // 车锁状态
)
```

### 充电信息 (ChargingInfo)
```kotlin
data class ChargingInfo(
    val isCharging: Boolean,     // 是否充电中
    val batteryLevel: Int,       // 电量百分比
    val chargingPower: Int,      // 充电功率(kW)
    val estimatedTime: Int,      // 预计时间(分钟)
    val temperature: Int         // 电池温度(°C)
)
```

### 钥匙信息 (KeyInfo)
```kotlin
data class KeyInfo(
    val id: String,              // 钥匙ID
    val name: String,            // 钥匙名称
    val type: String,            // 钥匙类型
    val isActive: Boolean,       // 是否激活
    val lastUsed: String         // 最后使用时间
)
```

## 注意事项

1. ✅ **BASE_URL 已配置**: `http://10.161.10.232:3000/`
2. ✅ **所有 7 个接口已定义**: ApiService.kt 中包含完整的 API 接口
3. ✅ **网络权限已添加**: INTERNET 和 ACCESS_NETWORK_STATE
4. ✅ **响应处理统一**: 使用 ApiHelper 统一处理 API 响应
5. ✅ **错误处理完善**: BaseViewModel 的 safeApiCall 自动处理异常
6. ✅ **生命周期感知**: ViewModel 与 Activity/Fragment 生命周期绑定

## 下一步建议

1. 测试各个 API 接口的返回数据结构
2. 根据实际返回的 JSON 结构调整 Models.kt 中的数据类
3. 为各个 Activity 添加对应的 ViewModel 绑定
4. 实现加载动画和错误提示 UI
5. 添加下拉刷新功能
6. 实现数据缓存机制
