package com.aabb

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.Bitmap
import android.os.Bundle
import android.osIBinder
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var faceSwapService: FaceSwapService? = null
    private var cameraService: VirtualCameraService? = null
    private var serviceConnection: ServiceConnection? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 初始化换脸服务
        faceSwapService = FaceSwapService(this)

        // 绑定虚拟相机服务
        val intent = Intent(this, VirtualCameraService::class.java)
        serviceConnection = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                // 获取service实例
                cameraService = (service as VirtualCameraService.LocalBinder).getService()

                // 帧回调：拿到原始画面，执行换脸
                cameraService?.setFrameListener { originBitmap: Bitmap ->
                    val swappedBitmap = faceSwapService?.processFrame(originBitmap)
                    // 把换脸后的画面推送回虚拟相机输出
                    swappedBitmap?.let {
                        cameraService?.pushOutputFrame(it)
                    }
                }
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                cameraService = null
            }
        }
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onDestroy() {
        // 释放资源，防止内存泄漏
        faceSwapService?.release()
        serviceConnection?.let { unbindService(it) }
        stopService(Intent(this, VirtualCameraService::class.java))
        super.onDestroy()
    }
}
