package com.aabb

import android.content.Context
import android.graphics.Bitmap
import androidx.camera.core.ImageProxy

class FaceSwapService(context: Context) {
    // 原有方法：接收ImageProxy相机原始帧
    fun processFrame(imageProxy: ImageProxy): Bitmap {
        // MediaPipe人脸处理逻辑占位
        return Bitmap.createBitmap(500,500,Bitmap.Config.ARGB_8888)
    }

    // 新增重载，接收Bitmap
    fun processFrame(bitmap: Bitmap): Bitmap {
        //送入mediapipe执行换脸
        return bitmap
    }

    fun release() {
        //释放模型资源
    }
}
