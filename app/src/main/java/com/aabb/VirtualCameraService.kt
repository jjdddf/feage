package com.aabb

import android.app.Service
import android.content.Intent
import android.graphics.Bitmap
import android.os.Binder
import android.os.IBinder

class VirtualCameraService : Service() {
    private val binder = LocalBinder()
    private var frameListener: ((Bitmap) -> Unit)? = null
    private var outputFrameCallback: ((Bitmap) -> Unit)? = null

    inner class LocalBinder : Binder() {
        fun getService(): VirtualCameraService = this@VirtualCameraService
    }

    // 设置原始画面监听
    fun setFrameListener(listener: (Bitmap) -> Unit) {
        frameListener = listener
    }

    // 推送换脸后的画面输出给虚拟相机
    fun pushOutputFrame(bitmap: Bitmap){
        outputFrameCallback?.invoke(bitmap)
    }

    //相机捕获到原始画面，触发回调
    fun onCaptureFrame(bitmap: Bitmap) {
        frameListener?.invoke(bitmap)
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }
}
