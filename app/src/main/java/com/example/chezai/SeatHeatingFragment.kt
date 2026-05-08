package com.example.chezai

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class SeatHeatingFragment : Fragment() {
    
    private var popupWindow: PopupWindow? = null
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_seat_heating, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // 立即开启按钮
        view.findViewById<Button>(R.id.btnEnable).setOnClickListener {
            showSeatHeatingPopup()
        }
    }
    
    private fun showSeatHeatingPopup() {
        if (popupWindow != null && popupWindow!!.isShowing) {
            return
        }
        
        // 加载 PopupWindow 布局
        val popupView = LayoutInflater.from(context).inflate(R.layout.popup_seat_heating, null)
        
        // 创建 PopupWindow
        popupWindow = PopupWindow(
            popupView,
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            true
        )
        
        // 设置背景为透明
        popupWindow!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        
        // 设置点击外部关闭
        popupWindow!!.isOutsideTouchable = true
        
        // 设置动画效果（从底部弹出）
        popupWindow!!.animationStyle = R.style.BottomSheetAnimation
        
        // 通风等级滑块
        val seekBarVentilation = popupView.findViewById<SeekBar>(R.id.seekBarVentilation)
        val tvVentilationLevel = popupView.findViewById<TextView>(R.id.tvVentilationLevel)
        seekBarVentilation.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tvVentilationLevel.text = progress.toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
        
        // 定时加热滑块
        val seekBarTimer = popupView.findViewById<SeekBar>(R.id.seekBarTimer)
        val tvTimerValue = popupView.findViewById<TextView>(R.id.tvTimerValue)
        seekBarTimer.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tvTimerValue.text = progress.toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
        
        // 保存设置按钮
        popupView.findViewById<Button>(R.id.btnSaveSettings).setOnClickListener {
            Toast.makeText(context, "设置已保存", Toast.LENGTH_SHORT).show()
            popupWindow?.dismiss()
        }
        
        // 顶部拖拽条点击关闭
        popupView.findViewById<LinearLayout>(R.id.dragHandle).setOnClickListener {
            popupWindow?.dismiss()
        }
        
        // 显示 PopupWindow 在底部
        popupWindow?.showAtLocation(
            requireActivity().window.decorView,
            Gravity.BOTTOM,
            0,
            0
        )
        
        // 设置背景变暗
        setBackgroundAlpha(0.5f)
        
        // PopupWindow 关闭时恢复背景亮度
        popupWindow?.setOnDismissListener {
            setBackgroundAlpha(1.0f)
        }
    }
    
    private fun setBackgroundAlpha(alpha: Float) {
        val layoutParams = requireActivity().window.attributes
        layoutParams.alpha = alpha
        requireActivity().window.attributes = layoutParams
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        popupWindow?.dismiss()
        popupWindow = null
    }
}
