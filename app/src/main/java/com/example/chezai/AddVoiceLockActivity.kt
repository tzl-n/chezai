package com.example.chezai

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AddVoiceLockActivity : AppCompatActivity() {
    
    private var isRecording = false
    private var recordProgress = 0
    private var recordName = ""
    private val handler = Handler(Looper.getMainLooper())
    private val recordRunnable = object : Runnable {
        override fun run() {
            if (recordProgress < 10) {
                recordProgress++
                handler.postDelayed(this, 1000)
            } else {
                finishRecording()
            }
        }
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_voice_lock)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        
        setupUI()
    }
    
    private fun setupUI() {
        // 返回按钮
        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }
        
        // 录音名称输入框
        val etRecordName = findViewById<EditText>(R.id.etRecordName)
        
        // 录音按钮 - 长按录音
        val btnRecord = findViewById<ImageView>(R.id.btnRecord)
        btnRecord.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    startRecording()
                    true
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    if (isRecording) {
                        finishRecording()
                    }
                    true
                }
                else -> false
            }
        }
        
        // 音频锁作用单选按钮
        val rgLockAction = findViewById<RadioGroup>(R.id.rgLockAction)
        rgLockAction.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbUnlock -> {
                    Toast.makeText(this, "选择了解锁车门", Toast.LENGTH_SHORT).show()
                }
                R.id.rbLock -> {
                    Toast.makeText(this, "选择了关闭车门", Toast.LENGTH_SHORT).show()
                }
            }
        }
        
        // 设置为默认开关
        val switchDefault = findViewById<Switch>(R.id.switchDefault)
        switchDefault.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(this, if (isChecked) "已设置为默认" else "已取消默认", Toast.LENGTH_SHORT).show()
        }
    }
    
    private fun startRecording() {
        isRecording = true
        recordProgress = 0
        Toast.makeText(this, "开始录音", Toast.LENGTH_SHORT).show()
        handler.post(recordRunnable)
    }
    
    private fun finishRecording() {
        isRecording = false
        handler.removeCallbacks(recordRunnable)
        
        // 保存录音名称
        recordName = findViewById<EditText>(R.id.etRecordName).text.toString()
        if (recordName.isEmpty()) {
            recordName = "未命名录音"
        }
        
        // 切换到录音后的布局
        setContentView(R.layout.activity_add_voice_lock_recorded)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        
        setupRecordedUI()
    }
    
    private fun setupRecordedUI() {
        // 返回按钮
        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }
        
        // 显示录音名称
        val tvRecordName = findViewById<TextView>(R.id.tvRecordName)
        tvRecordName.text = recordName
        
        // 显示录音时长
        val tvRecordDuration = findViewById<TextView>(R.id.tvRecordDuration)
        tvRecordDuration.text = "${recordProgress}S"
        
        // 显示录音进度条
        val layoutRecordProgress = findViewById<LinearLayout>(R.id.layoutRecordProgress)
        layoutRecordProgress.visibility = LinearLayout.VISIBLE
        
        // 隐藏录音前布局，显示录音后布局
        findViewById<LinearLayout>(R.id.layoutBeforeRecord).visibility = LinearLayout.GONE
        findViewById<LinearLayout>(R.id.layoutAfterRecord).visibility = LinearLayout.VISIBLE
        
        // 音频锁作用单选按钮
        val rgLockAction = findViewById<RadioGroup>(R.id.rgLockAction)
        rgLockAction.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbUnlock -> {
                    Toast.makeText(this, "选择了解锁车门", Toast.LENGTH_SHORT).show()
                }
                R.id.rbLock -> {
                    Toast.makeText(this, "选择了关闭车门", Toast.LENGTH_SHORT).show()
                }
            }
        }
        
        // 设置为默认开关
        val switchDefault = findViewById<Switch>(R.id.switchDefault)
        switchDefault.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(this, if (isChecked) "已设置为默认" else "已取消默认", Toast.LENGTH_SHORT).show()
        }
        
        // 保存音频锁按钮
        findViewById<Button>(R.id.btnSave).setOnClickListener {
            Toast.makeText(this, "保存音频锁", Toast.LENGTH_SHORT).show()
            finish()
        }
        
        // 重新录音按钮
        findViewById<Button>(R.id.btnRerecord).setOnClickListener {
            // 重新加载录音前布局
            setContentView(R.layout.activity_add_voice_lock)
            
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
            
            setupUI()
        }
    }
}
