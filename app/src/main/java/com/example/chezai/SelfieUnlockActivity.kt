package com.example.chezai

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SelfieUnlockActivity : AppCompatActivity() {
    
    private val CAMERA_PERMISSION_CODE = 100
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_selfie_unlock)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        
        setupUI()
        checkCameraPermission()
    }
    
    private fun setupUI() {
        // 取消按钮
        findViewById<TextView>(R.id.btnCancel).setOnClickListener {
            finish()
        }
        
        // 开始解锁按钮
        findViewById<Button>(R.id.btnStartUnlock).setOnClickListener {
            checkCameraPermission()
        }
    }
    
    private fun checkCameraPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                // 已有权限，启动相机
                Toast.makeText(this, "摄像头权限已授予", Toast.LENGTH_SHORT).show()
                startCamera()
            }
            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.CAMERA
            ) -> {
                // 用户之前拒绝过，需要解释
                showPermissionRationale()
            }
            else -> {
                // 直接请求权限
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.CAMERA),
                    CAMERA_PERMISSION_CODE
                )
            }
        }
    }
    
    private fun showPermissionRationale() {
        Toast.makeText(this, "需要摄像头权限进行自拍解锁", Toast.LENGTH_LONG).show()
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.CAMERA),
            CAMERA_PERMISSION_CODE
        )
    }
    
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        
        when (requestCode) {
            CAMERA_PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 权限被授予
                    Toast.makeText(this, "摄像头权限已授予", Toast.LENGTH_SHORT).show()
                    startCamera()
                } else {
                    // 权限被拒绝
                    Toast.makeText(this, "摄像头权限被拒绝，无法使用自拍解锁", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    
    private fun startCamera() {
        // TODO: 启动相机进行人脸识别
        Toast.makeText(this, "启动相机进行自拍解锁", Toast.LENGTH_SHORT).show()
    }
}
