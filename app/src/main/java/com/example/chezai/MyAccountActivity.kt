package com.example.chezai

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MyAccountActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_my_account)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupUI()
    }

    private fun setupUI() {
        // 返回按钮
        findViewById<android.widget.ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        // 账户明细
        findViewById<TextView>(R.id.btnAccountDetail).setOnClickListener {
            Toast.makeText(this, "账户明细", Toast.LENGTH_SHORT).show()
        }

        // 加气卡充值
        findViewById<Button>(R.id.btnRechargeGas).setOnClickListener {
            //跳转到GasStationListActivity
            startActivity(GasStationListActivity.newIntent(this))
        }

        // 加油卡充值
        findViewById<Button>(R.id.btnRechargeOil).setOnClickListener {
            startActivity(GasStationListActivity.newIntent(this))
        }

        // 立即购买
        findViewById<Button>(R.id.btnBuyNow).setOnClickListener {
            Toast.makeText(this, "立即购买电子礼品卡", Toast.LENGTH_SHORT).show()
        }

        // 绑定礼品卡
        findViewById<Button>(R.id.btnBindCard).setOnClickListener {
            Toast.makeText(this, "绑定礼品卡", Toast.LENGTH_SHORT).show()
        }
    }
}
