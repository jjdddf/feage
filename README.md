# AI实时换脸+虚拟摄像头应用

Android AI人脸识别、实时换脸和虚拟摄像头应用。

## 功能特性

- 🎭 **实时人脸检测** - 使用MediaPipe进行468个关键点检测
- 🔄 **人脸交换** - OpenCV驱动的高质量人脸交换
- 🎨 **多种滤镜**
  - 美颜滤镜（皮肤平滑、美白）
  - 虚拟妆容
  - 卡通效果
  - 模糊背景
- 📷 **虚拟摄像头** - 系统级集成，支持第三方应用调用
- ⚡ **高性能处理** - TensorFlow Lite GPU加速
- 🔐 **权限管理** - 完整的运行时权限处理

## 技术栈

- **语言**: Kotlin
- **框架**: Android Jetpack (CameraX, Lifecycle, ViewModel)
- **AI模型**:
  - MediaPipe Face Landmarker
  - TensorFlow Lite
  - OpenCV 4.8.0
- **并发**: Kotlin Coroutines
- **日志**: Timber

## 项目结构

```
feage/
├── app/
│   ├── src/main/
│   │   ├── kotlin/com/jinke1/feage/
│   │   │   ├── FeageApplication.kt       # 应用初始化
│   │   │   ├── ui/
│   │   │   │   └── MainActivity.kt        # 主界面
│   │   │   └── service/
│   │   │       ├── FaceSwapService.kt     # 人脸处理服务
│   │   │       └── VirtualCameraService.kt # 虚拟摄像头服务
│   │   ├── res/
│   │   │   ├── layout/activity_main.xml
│   │   │   ├── drawable/button_bg_*.xml
│   │   │   └── values/
│   │   │       ├── colors.xml
│   │   │       └── strings.xml
│   │   ├── assets/
│   │   │   └── face_landmarker.task      # MediaPipe模型
│   │   └── AndroidManifest.xml
│   ├── build.gradle
│   └── proguard-rules.pro
├── build.gradle
└── settings.gradle
```

## 快速开始

### 前置要求
- Android Studio Flamingo或更高版本
- Android SDK 24及以上
- 支持OpenGL 3.0及以上的Android设备

### 安装步骤

1. **克隆仓库**
```bash
git clone https://github.com/jinke1/feage.git
cd feage
```

2. **下载模型**
- 从[MediaPipe官方](https://developers.google.com/mediapipe/solutions/vision/face_landmarker)下载`face_landmarker.task`
- 放入`app/src/main/assets/face_landmarker.task`

3. **构建项目**
```bash
./gradlew build
```

4. **安装应用**
```bash
./gradlew installDebug
```

## 使用说明

### 基本操作

1. **启动应用** - 点击"Start"按钮启动虚拟摄像头
2. **应用滤镜** - 点击"Beauty"应用美颜效果
3. **切换摄像头** - 点击"Switch"在前后摄像头间切换
4. **停止应用** - 点击"Stop"关闭虚拟摄像头

### 权限要求

应用需要以下权限：
- `CAMERA` - 访问设备摄像头
- `READ_EXTERNAL_STORAGE` - 读取存储
- `WRITE_EXTERNAL_STORAGE` - 写入存储

## 核心模块说明

### FaceSwapService
负责人脸检测和处理：
- 使用MediaPipe检测人脸关键点
- 应用OpenCV图像变换
- 支持多种美颜效果

### VirtualCameraService
虚拟摄像头服务：
- 集成CameraX框架
- 提供系统级摄像头接口
- 实时帧处理

### MainActivity
主用户界面：
- 权限请求和管理
- 服务启动/停止控制
- 实时状态显示

## 性能优化

- ✅ 使用TensorFlow Lite GPU加速
- ✅ 异步处理采用Coroutines
- ✅ CameraX背压管理（仅保留最新帧）
- ✅ 双线程处理（UI线程 + 分析线程）

## API参考

### FaceSwapService

```kotlin
// 初始化
val faceSwapService = FaceSwapService(context)

// 处理图像帧
val resultBitmap = faceSwapService.processFrame(imageProxy)

// 释放资源
faceSwapService.release()
```

### VirtualCameraService

```kotlin
// 创建绑定
bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)

// 设置监听
cameraService?.setFrameListener { bitmap ->
    // 处理帧
}

// 停止服务
stopService(intent)
```

## 故障排除

### 摄像头无法启动
- 检查相机权限是否已授予
- 确认设备摄像头未被其他应用占用
- 重启应用

### 人脸检测不准确
- 确保光线充足
- 调整摄像头角度
- 更新MediaPipe模型

### 性能不佳
- 关闭其他后台应用
- 降低图像处理分辨率
- 启用GPU加速

## 许可证

MIT License - 详见 [LICENSE](LICENSE) 文件

## 贡献指南

欢迎提交Issue和Pull Request！

## 作者

- **Jinke1** - 初始作者

## 致谢

- [MediaPipe](https://developers.google.com/mediapipe)
- [OpenCV](https://opencv.org/)
- [TensorFlow Lite](https://www.tensorflow.org/lite)
- [Android Jetpack](https://developer.android.com/jetpack)
