package com.example.chezai

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MessageManageActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MessageAdapter
    private val messages = mutableListOf<MessageItem>()
    
    // UI组件
    private lateinit var tvTitle: TextView
    private lateinit var btnEdit: TextView
    private lateinit var btnCancel: TextView
    private lateinit var layoutBottomBar: LinearLayout
    private lateinit var layoutSelectAll: LinearLayout
    private lateinit var cbSelectAll: CheckBox
    private lateinit var tvSelectedCount: TextView
    private lateinit var btnDelete: Button
    
    private var isEditMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_message_manage)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.root)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initViews()
        setupRecyclerView()
        loadMockData()
        setupListeners()
    }

    private fun initViews() {
        tvTitle = findViewById(R.id.tvTitle)
        btnEdit = findViewById(R.id.btnEdit)
        btnCancel = findViewById(R.id.btnCancel)
        layoutBottomBar = findViewById(R.id.layoutBottomBar)
        layoutSelectAll = findViewById(R.id.layoutSelectAll)
        cbSelectAll = findViewById(R.id.cbSelectAll)
        tvSelectedCount = findViewById(R.id.tvSelectedCount)
        btnDelete = findViewById(R.id.btnDelete)
    }

    private fun setupRecyclerView() {
        recyclerView = findViewById(R.id.recyclerViewMessages)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun loadMockData() {
        // 模拟数据
        messages.clear()
        messages.add(MessageItem("我是消息的标题", "2023-09-08 12:00:09", false))
        messages.add(MessageItem("我是消息的标题", "2023-09-08 12:00:09", true))
        messages.add(MessageItem("我是消息的标题", "2023-09-08 12:00:09", true))
        messages.add(MessageItem("我是消息的标题", "2023-09-08 12:00:09", true))
        messages.add(MessageItem("我是消息的标题", "2023-09-08 12:00:09", true))
        messages.add(MessageItem("我是消息的标题", "2023-09-08 12:00:09", true))
        messages.add(MessageItem("我是消息的标题", "2023-09-08 12:00:09", true))
        messages.add(MessageItem("我是消息的标题", "2023-09-08 12:00:09", true))

        adapter = MessageAdapter(messages, 
            onItemClick = { message ->
                Toast.makeText(this, "查看详情: ${message.title}", Toast.LENGTH_SHORT).show()
            },
            onSelectionChanged = { updateSelectionUI() }
        )
        recyclerView.adapter = adapter
    }

    private fun setupListeners() {
        // 返回按钮
        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            if (isEditMode) {
                exitEditMode()
            } else {
                finish()
            }
        }

        // 编辑按钮
        btnEdit.setOnClickListener {
            enterEditMode()
        }

        // 取消按钮
        btnCancel.setOnClickListener {
            exitEditMode()
        }

        // 全选
        layoutSelectAll.setOnClickListener {
            if (adapter.getSelectedCount() == messages.size) {
                adapter.clearSelection()
            } else {
                adapter.selectAll()
            }
        }

        // 删除按钮
        btnDelete.setOnClickListener {
            if (adapter.getSelectedCount() == 0) {
                Toast.makeText(this, "请先选择要删除的消息", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            
            AlertDialog.Builder(this)
                .setTitle("删除消息")
                .setMessage("确定要删除选中的 ${adapter.getSelectedCount()} 条消息吗？")
                .setPositiveButton("删除") { _, _ ->
                    adapter.deleteSelected()
                    updateSelectionUI()
                    Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show()
                    
                    // 如果没有消息了，退出编辑模式
                    if (messages.isEmpty()) {
                        exitEditMode()
                    }
                }
                .setNegativeButton("取消", null)
                .show()
        }
    }

    private fun enterEditMode() {
        isEditMode = true
        adapter.isEditMode = true
        btnEdit.visibility = View.GONE
        btnCancel.visibility = View.VISIBLE
        layoutBottomBar.visibility = View.VISIBLE
        updateSelectionUI()
    }

    private fun exitEditMode() {
        isEditMode = false
        adapter.isEditMode = false
        adapter.clearSelection()
        btnEdit.visibility = View.VISIBLE
        btnCancel.visibility = View.GONE
        layoutBottomBar.visibility = View.GONE
    }

    private fun updateSelectionUI() {
        val selectedCount = adapter.getSelectedCount()
        tvSelectedCount.text = "已选 ($selectedCount)"
        cbSelectAll.isChecked = selectedCount == messages.size && messages.isNotEmpty()
    }
}
