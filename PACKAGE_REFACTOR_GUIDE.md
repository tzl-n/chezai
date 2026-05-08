#  项目包结构重构指南

##  目标

将24个 Activity 按功能模块重新组织到不同的包中，提升代码可维护性和可读性。

## 📂 新的包结构

```
com.example.chezai/
├── data/                      # 数据层
│   ── model/
│       └── Models.kt
├── network/                   # 网络层
│   ├── ApiClient.kt
│   └── ApiService.kt
├── repository/                # 数据仓库层
│   └── Repository.kt
├── utils/                     # 工具类
│   ├── ApiHelper.kt
│   ├── PreferencesUtils.kt
│   ├── ToastUtils.kt
│   └── ViewExtensions.kt
├── viewmodel/                 # ViewModel层
│   ├── BaseViewModel.kt
│   ├── CallViewModel.kt
│   ├── CarViewModel.kt
│   ├── ChargeViewModel.kt
│   ├── HVACViewModel.kt
│   ├── KeyViewModel.kt
│   ├── MessageViewModel.kt
│   ├── OTAViewModel.kt
│   ├── SecurityViewModel.kt
│   ── UserViewModel.kt
├── ui/                        # UI层（按功能模块分组）
│   ├── main/                  # 主流程 (4个)
│   │   ├── MainActivity.kt
│   │   └── HomeActivity.kt
│   ├── vehicle/               # 车辆控制 (5个)
│   │   ├── KeyManagementActivity.kt
│   │   ├── RemoteControlActivity.kt
│   │   ├── CarEnvironmentControlActivity.kt
│   │   ├── SeatHeatingFragment.kt
│   │   ├── TemperatureControlFragment.kt
│   │   ├── SeatAdjustFragment.kt
│   │   ── SteeringWheelFragment.kt
│   ├── trailer/               # 挂车功能 (2个)
│   │   ├── TrailerExpandActivity.kt
│   │   └── TrailerDarkModeActivity.kt
│   ├── charge/                # 充电管理 (3个)
│   │   ├── SmartChargeActivity.kt
│   │   ├── ChargingActivity.kt
│   │   └── ChargeCompletedActivity.kt
│   ├── dashcam/               # 行车记录仪 (2个)
│   │   ├── DashCamActivity.kt
│   │   └── DashCamSettingsActivity.kt
│   ├── message/               # 消息管理 (1个)
│   │   └── MessageManageActivity.kt
│   ├── service/               # 车主服务 (3个)
│   │   ├── CarOwnerServiceActivity.kt
│   │   ├── CallPhoneActivity.kt
│   │   └── MapNavigationActivity.kt
│   └── parking/               # 停车功能 (1个)
│       └── SearchParkingActivity.kt
└── [辅助类]
    ├── MessageAdapter.kt
    ── MessageItem.kt
```

##  文件移动清单

### 1. **主流程包** (ui/main/)
```
MainActivity.kt              → ui/main/MainActivity.kt
HomeActivity.kt              → ui/main/HomeActivity.kt
```

### 2. **车辆控制包** (ui/vehicle/)
```
KeyManagementActivity.kt     → ui/vehicle/KeyManagementActivity.kt
RemoteControlActivity.kt     → ui/vehicle/RemoteControlActivity.kt
CarEnvironmentControlActivity.kt → ui/vehicle/CarEnvironmentControlActivity.kt
SeatHeatingFragment.kt       → ui/vehicle/SeatHeatingFragment.kt
TemperatureControlFragment.kt → ui/vehicle/TemperatureControlFragment.kt
SeatAdjustFragment.kt        → ui/vehicle/SeatAdjustFragment.kt
SteeringWheelFragment.kt     → ui/vehicle/SteeringWheelFragment.kt
```

### 3. **挂车功能包** (ui/trailer/)
```
TrailerExpandActivity.kt     → ui/trailer/TrailerExpandActivity.kt
TrailerDarkModeActivity.kt   → ui/trailer/TrailerDarkModeActivity.kt
```

### 4. **充电管理包** (ui/charge/)
```
SmartChargeActivity.kt       → ui/charge/SmartChargeActivity.kt
ChargingActivity.kt          → ui/charge/ChargingActivity.kt
ChargeCompletedActivity.kt   → ui/charge/ChargeCompletedActivity.kt
```

### 5. **行车记录仪包** (ui/dashcam/)
```
DashCamActivity.kt           → ui/dashcam/DashCamActivity.kt
DashCamSettingsActivity.kt   → ui/dashcam/DashCamSettingsActivity.kt
```

### 6. **消息管理包** (ui/message/)
```
MessageManageActivity.kt     → ui/message/MessageManageActivity.kt
```

### 7. **车主服务包** (ui/service/)
```
CarOwnerServiceActivity.kt   → ui/service/CarOwnerServiceActivity.kt
CallPhoneActivity.kt         → ui/service/CallPhoneActivity.kt
MapNavigationActivity.kt     → ui/service/MapNavigationActivity.kt
```

### 8. **停车功能包** (ui/parking/)
```
SearchParkingActivity.kt     → ui/parking/SearchParkingActivity.kt
```

### 9. **辅助类保留在主包**
```
MessageAdapter.kt            → 保持在主包
MessageItem.kt               → 保持在主包
```

## 🔧 重构步骤

### 方式一：使用 Android Studio 重构功能（推荐）

1. **右键点击文件** → Refactor → Move
2. **选择目标包** → 确认
3. **Android Studio 会自动**：
   - 移动文件到新位置
   - 更新 package 声明
   - 更新所有引用该文件的 import 语句

### 方式二：手动移动文件

#### 步骤 1：创建新包目录

在 `app/src/main/java/com/example/chezai/` 下创建：
```bash
mkdir -p ui/main
mkdir -p ui/vehicle
mkdir -p ui/trailer
mkdir -p ui/charge
mkdir -p ui/dashcam
mkdir -p ui/message
mkdir -p ui/service
mkdir -p ui/parking
```

#### 步骤 2：移动文件并修改包声明

**示例：MainActivity.kt**

原文件：
```kotlin
package com.example.chezai
```

新文件位置：`ui/main/MainActivity.kt`
```kotlin
package com.example.chezai.ui.main
```

#### 步骤 3：更新 AndroidManifest.xml

```xml
<!-- 修改前 -->
<activity android:name=".MainActivity" />

<!-- 修改后 -->
<activity android:name=".ui.main.MainActivity" />
```

#### 步骤 4：更新所有 Intent 引用

**示例：HomeActivity.kt 中的跳转**

```kotlin
// 修改前
val intent = Intent(this, TrailerExpandActivity::class.java)

// 修改后
import com.example.chezai.ui.trailer.TrailerExpandActivity
val intent = Intent(this, TrailerExpandActivity::class.java)
```

## 📋 需要更新的引用

### 跨包跳转的 Intent（需要添加 import）

1. **HomeActivity.kt** 需要导入：
   ```kotlin
   import com.example.chezai.ui.trailer.TrailerExpandActivity
   import com.example.chezai.ui.service.CarOwnerServiceActivity
   import com.example.chezai.ui.dashcam.DashCamActivity
   import com.example.chezai.ui.vehicle.KeyManagementActivity
   import com.example.chezai.ui.vehicle.SystemUpgradeActivity
   import com.example.chezai.ui.charge.SmartChargeActivity
   ```

2. **KeyManagementActivity.kt** 需要导入：
   ```kotlin
   import com.example.chezai.ui.vehicle.CarEnvironmentControlActivity
   import com.example.chezai.ui.service.CallPhoneActivity
   ```

3. **CarEnvironmentControlActivity.kt** 的 Fragment：
   ```kotlin
   import com.example.chezai.ui.vehicle.SeatHeatingFragment
   import com.example.chezai.ui.vehicle.TemperatureControlFragment
   import com.example.chezai.ui.vehicle.SeatAdjustFragment
   import com.example.chezai.ui.vehicle.SteeringWheelFragment
   ```

## ✅ 验证清单

重构完成后，检查以下项目：

- [ ] 所有文件已移动到正确的包
- [ ] 所有 package 声明已更新
- [ ] AndroidManifest.xml 中的 Activity 路径已更新
- [ ] 所有跨包 Intent 已添加正确的 import
- [ ] 项目可以成功编译
- [ ] 所有页面跳转功能正常

## 🎨 包结构优势

### 重构前的问题
- ❌ 所有24个 Activity 混在同一个包
- ❌ 难以快速定位特定功能的代码
-  不利于团队协作
- ❌ 代码结构混乱

### 重构后的优势
- ✅ 按功能模块清晰分组
- ✅ 快速定位相关代码
- ✅ 便于多人协作开发
- ✅ 符合 Android 最佳实践
- ✅ 易于扩展和维护

##  快速执行命令

### 使用 PowerShell 移动文件：

```powershell
# 主流程
Move-Item MainActivity.kt ui\main\
Move-Item HomeActivity.kt ui\main\

# 车辆控制
Move-Item KeyManagementActivity.kt ui\vehicle\
Move-Item RemoteControlActivity.kt ui\vehicle\
Move-Item CarEnvironmentControlActivity.kt ui\vehicle\
Move-Item SeatHeatingFragment.kt ui\vehicle\
Move-Item TemperatureControlFragment.kt ui\vehicle\
Move-Item SeatAdjustFragment.kt ui\vehicle\
Move-Item SteeringWheelFragment.kt ui\vehicle\

# 挂车功能
Move-Item TrailerExpandActivity.kt ui\trailer\
Move-Item TrailerDarkModeActivity.kt ui\trailer\

# 充电管理
Move-Item SmartChargeActivity.kt ui\charge\
Move-Item ChargingActivity.kt ui\charge\
Move-Item ChargeCompletedActivity.kt ui\charge\

# 行车记录仪
Move-Item DashCamActivity.kt ui\dashcam\
Move-Item DashCamSettingsActivity.kt ui\dashcam\

# 消息管理
Move-Item MessageManageActivity.kt ui\message\

# 车主服务
Move-Item CarOwnerServiceActivity.kt ui\service\
Move-Item CallPhoneActivity.kt ui\service\
Move-Item MapNavigationActivity.kt ui\service\

# 停车功能
Move-Item SearchParkingActivity.kt ui\parking\
```

## 💡 建议

1. **在重构前提交当前代码到 Git**
2. **使用 Android Studio 的 Refactor 功能**（会自动处理所有引用）
3. **每移动一个包就编译一次**，确保没有错误
4. **更新 AndroidManifest.xml 后立即测试**

## 📞 需要帮助？

如果需要我协助执行具体的文件移动和包声明修改，请告诉我，我可以：
1. 逐个文件修改包声明
2. 更新 AndroidManifest.xml
3. 修复所有跨包引用
4. 验证编译是否成功
