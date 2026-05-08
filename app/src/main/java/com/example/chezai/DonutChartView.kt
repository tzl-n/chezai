package com.example.chezai

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlin.math.min

class DonutChartView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    data class Segment(
        val percentage: Float,
        val color: Int,
        val label: String
    )

    private val segments = mutableListOf<Segment>()
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var centerText = "37%"
    private var centerSubText = "能量回收"

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val width = width.toFloat()
        val height = height.toFloat()
        val centerX = width / 2
        val centerY = height / 2
        val radius = min(width, height) / 2 - 40
        val strokeWidth = radius * 0.25f

        // 绘制背景圆环
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = strokeWidth
        paint.color = Color.parseColor("#F5F5F5")
        canvas.drawCircle(centerX, centerY, radius - strokeWidth / 2, paint)

        // 绘制各个分段
        var startAngle = -90f
        val totalPercentage = segments.sumOf { it.percentage.toDouble() }.toFloat()

        segments.forEach { segment ->
            val sweepAngle = (segment.percentage / totalPercentage) * 360f
            paint.color = segment.color
            canvas.drawArc(
                centerX - radius + strokeWidth / 2,
                centerY - radius + strokeWidth / 2,
                centerX + radius - strokeWidth / 2,
                centerY + radius - strokeWidth / 2,
                startAngle,
                sweepAngle,
                false,
                paint
            )
            startAngle += sweepAngle
        }

        // 绘制中心文字
        paint.style = Paint.Style.FILL
        paint.textAlign = Paint.Align.CENTER
        
        // 百分比文字
        paint.color = Color.parseColor("#333333")
        paint.textSize = radius * 0.35f
        paint.typeface = Typeface.DEFAULT_BOLD
        val textBounds = Rect()
        paint.getTextBounds(centerText, 0, centerText.length, textBounds)
        val textHeight = textBounds.height()
        canvas.drawText(centerText, centerX, centerY - textHeight / 4, paint)

        // 说明文字
        paint.color = Color.parseColor("#666666")
        paint.textSize = radius * 0.15f
        paint.typeface = Typeface.DEFAULT
        val subTextBounds = Rect()
        paint.getTextBounds(centerSubText, 0, centerSubText.length, subTextBounds)
        val subTextHeight = subTextBounds.height()
        canvas.drawText(centerSubText, centerX, centerY + textHeight / 2 + subTextHeight / 2, paint)
    }

    fun setSegments(segments: List<Segment>) {
        this.segments.clear()
        this.segments.addAll(segments)
        invalidate()
    }

    fun setCenterText(text: String, subText: String) {
        this.centerText = text
        this.centerSubText = subText
        invalidate()
    }
}
