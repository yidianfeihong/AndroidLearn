package com.oplus.settings.feature.display.breathinglight.color

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.TextUtils
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import com.example.platform.DisplaySizeUtils
import com.example.preference_demo.R

/***********************************************************
 * Copyright (C), 2010-2020, Oplus. All rights reserved.
 * File: AndroidLearn
 * Description:
 * Version: 1.0
 * Date : 2022-07-01
 * Author: shiwenming
 *
 * ---------------------Revision History: ---------------------
 * <author> <date> <version> <desc>
 * shiwenming 2022/05/23 1.0 create
 ************************************/
class BreathingLightColorAccessView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private lateinit var mInnerPaint: Paint
    private lateinit var mOuterPaint: Paint
    private var mLeftColor = 0
    private var mMidColor = 0
    private var mRightColor = 0
    private var mStrokeColor = 0
    private var mInnerRadius = 0f
    private var mOuterRadius = 0f
    private var mStrokeWidth = 0f
    private fun parseAttrs(context: Context, attrs: AttributeSet?) {
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.BreathLightColorAccessView)
        mLeftColor = typedArray.getColor(
            R.styleable.BreathLightColorAccessView_blc_left_color,
            Color.TRANSPARENT
//            Color.parseColor("#2D40E9")
        )
        mMidColor = typedArray.getColor(
            R.styleable.BreathLightColorAccessView_blc_mid_color,
            Color.TRANSPARENT
//            Color.parseColor("#2DA74E")
        )
        mRightColor = typedArray.getColor(
            R.styleable.BreathLightColorAccessView_blc_right_color,
            Color.TRANSPARENT
//            Color.parseColor("#FB6A35")
        )
        mStrokeColor =
            typedArray.getColor(
                R.styleable.BreathLightColorAccessView_blc_stroke_color,
                Color.WHITE
            )
        mInnerRadius = typedArray.getDimension(
            R.styleable.BreathLightColorAccessView_blc_inner_radius,
            DEFAULT_INNER_RADIUS
        )
        mOuterRadius = typedArray.getDimension(
            R.styleable.BreathLightColorAccessView_blc_outer_radius,
            DEFAULT_OUTER_RADIUS
        )
        mStrokeWidth = typedArray.getDimension(
            R.styleable.BreathLightColorAccessView_blc_stroke_width,
            DEFAULT_STOKE_WIDTH
        )
        typedArray.recycle()
    }

    private fun initView() {
        mInnerPaint = Paint()
        mInnerPaint.isAntiAlias = true
        mOuterPaint = Paint()
        mOuterPaint.style = Paint.Style.STROKE
        mOuterPaint.isAntiAlias = true
        mOuterPaint.color = mStrokeColor
        mOuterPaint.strokeWidth = DisplaySizeUtils.dip2px(context, mStrokeWidth).toFloat()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val minWidth = DisplaySizeUtils.dip2px(context, DEFAULT_WIDTH)
        val minHeight = DisplaySizeUtils.dip2px(context, DEFAULT_HEIGHT)
        if (layoutParams.width == ViewGroup.LayoutParams.WRAP_CONTENT && layoutParams.height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(minWidth, minHeight)
        } else if (layoutParams.width == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(minWidth, heightSize)
        } else if (layoutParams.height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(widthSize, minHeight)
        }
    }

    fun setColor(leftColor: Int, midColor: Int, rightColor: Int) {
        mLeftColor = leftColor
        mMidColor = midColor
        mRightColor = rightColor
        invalidate()
    }

    fun setColor(leftColor: String?, midColor: String?, rightColor: String?) {
        if (TextUtils.isEmpty(leftColor) || TextUtils.isEmpty(midColor) || TextUtils.isEmpty(
                rightColor
            )
        ) {
            return
        }
        setColor(
            Color.parseColor(leftColor),
            Color.parseColor(midColor),
            Color.parseColor(rightColor)
        )
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val innerRadius = DisplaySizeUtils.dip2px(context, mInnerRadius).toFloat()
        val outerRadius = DisplaySizeUtils.dip2px(context, mOuterRadius).toFloat()
        val moveStep = (DisplaySizeUtils.dip2px(context, 10.5f) * test).toFloat()
        mInnerPaint.color = mRightColor
        canvas.drawCircle(width / 2f + moveStep, height / 2f, innerRadius, mInnerPaint)
        canvas.drawCircle(width / 2f + moveStep, height / 2f, outerRadius, mOuterPaint)
        mInnerPaint.color = mMidColor
        canvas.drawCircle(width / 2f, height / 2f, innerRadius, mInnerPaint)
        canvas.drawCircle(width / 2f, height / 2f, outerRadius, mOuterPaint)
        mInnerPaint.color = mLeftColor
        canvas.drawCircle(width / 2f - moveStep, height / 2f, innerRadius, mInnerPaint)
        canvas.drawCircle(width / 2f - moveStep, height / 2f, outerRadius, mOuterPaint)
    }

    companion object {
        private const val test = 1
        private const val DEFAULT_WIDTH = (41 * test).toFloat()
        private const val DEFAULT_HEIGHT = (24 * test).toFloat()
        private const val DEFAULT_INNER_RADIUS = (7 * test).toFloat()
        private const val DEFAULT_OUTER_RADIUS = (8 * test).toFloat()
        private const val DEFAULT_STOKE_WIDTH = (2 * test).toFloat()
    }

    init {
        parseAttrs(context, attrs)
        initView()
    }
}