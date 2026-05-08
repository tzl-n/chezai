package com.example.chezai

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CarDetailActivity : AppCompatActivity() {

    private val bottomBar: LinearLayout by lazy { findViewById<LinearLayout>(R.id.bottomBar) }
    private val zl: TextView by lazy { findViewById<TextView>(R.id.zl) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_detail)

        zl.setOnClickListener {
            Toast.makeText(this,"租赁成功",Toast.LENGTH_SHORT).show()
            zl.setText("已租赁")
        }
    }
}
