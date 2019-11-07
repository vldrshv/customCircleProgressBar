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
        initBaseProgressView()//init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initBaseProgressView()//init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        initBaseProgressView()//attrs, defStyle)
    }
/*
//    private fun init(attrs: AttributeSet?, defStyle: Int) {
//        // Load attributes
//        val a = context.obtainStyledAttributes(
//            attrs, R.styleable.CustomView, defStyle, 0
//        )
//
//        _exampleString = a.getString(
//            R.styleable.CustomView_exampleString
//        )
//        _exampleColor = a.getColor(
//            R.styleable.CustomView_exampleColor,
//            exampleColor
//        )
//        // Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
//        // values that should fall on pixel boundaries.
//        _exampleDimension = a.getDimension(
//            R.styleable.CustomView_exampleDimension,
//            exampleDimension
//        )
//
//        if (a.hasValue(R.styleable.CustomView_exampleDrawable)) {
//            exampleDrawable = a.getDrawable(
//                R.styleable.CustomView_exampleDrawable
//            )
//            exampleDrawable?.callback = this
//        }
//
//        a.recycle()
//
//        // Set up a default TextPaint object
//        textPaint = TextPaint().apply {
//            flags = Paint.ANTI_ALIAS_FLAG
//            textAlign = Paint.Align.LEFT
//        }
//
//        // Update TextPaint and text measurements from attributes
//        invalidateTextPaintAndMeasurements()
//    }
//
//    private fun invalidateTextPaintAndMeasurements() {
//        textPaint?.let {
//            it.textSize = exampleDimension
//            it.color = exampleColor
//            textWidth = it.measureText(exampleString)
//            textHeight = it.fontMetrics.bottom
//        }
//    }
//
//    override fun onDraw(canvas: Canvas) {
//        super.onDraw(canvas)
//
//        // TODO: consider storing these as member variables to reduce
//        // allocations per draw cycle.
//        val paddingLeft = paddingLeft
//        val paddingTop = paddingTop
//        val paddingRight = paddingRight
//        val paddingBottom = paddingBottom
//
//        val contentWidth = width - paddingLeft - paddingRight
//        val contentHeight = height - paddingTop - paddingBottom
//
//        exampleString?.let {
//            // Draw the text.
//            canvas.drawText(
//                it,
//                paddingLeft + (contentWidth - textWidth) / 2,
//                paddingTop + (contentHeight + textHeight) / 2,
//                textPaint!!
//            )
//        }
//
//        // Draw the example drawable on top of the text.
//        exampleDrawable?.let {
//            it.setBounds(
//                paddingLeft, paddingTop,
//                paddingLeft + contentWidth, paddingTop + contentHeight
//            )
//            it.draw(canvas)
//        }
//    }
*/
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

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
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
