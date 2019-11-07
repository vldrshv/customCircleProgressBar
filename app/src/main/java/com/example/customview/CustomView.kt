package com.example.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View

/**
 * TODO: document your custom view class.
 */
class CustomView : View {


    constructor(context: Context) : super(context) {
        initBaseProgressView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initBaseProgressView()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        initBaseProgressView()
    }
    private var size = 0
    private var paint = Paint()
    private var rectF = RectF()
    private var progressAngle = 0f


    private fun initBaseProgressView() {
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = Color.GREEN
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 8f
        paint.strokeCap = Paint.Cap.BUTT
        rectF = RectF(500f, 500f, 800f, 800f)
    }

    public override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawArc(rectF, 0f, progressAngle, false, paint)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val xPadding = paddingLeft + paddingRight
        val yPadding = paddingTop + paddingBottom
        val width = measuredWidth - xPadding
        val height = measuredHeight - yPadding
        if (width < height)
            size = width
        else
            size = height

        setMeasuredDimension(size + xPadding, size + yPadding)
    }

    public override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }

    public override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
    }

    fun setProgress(progress: Float) {
        progressAngle += (progress * 3.6f)
        invalidate()
    }
}
