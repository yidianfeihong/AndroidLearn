package com.example.preference_demo.breath

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
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
 * Date : 2022-06-30
 * Author: shiwenming
 *
 * ---------------------Revision History: ---------------------
 * <author> <date> <version> <desc>
 * shiwenming 2022/05/23 1.0 create
 ************************************/
class BreathingLightColorItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var mInnerRadius = 0f
    private var mOutRadius = 0f
    private var mStokeWidth = 0f
    private var mColor = 0
    private lateinit var mSolidPaint: Paint
    private lateinit var mStrokePaint: Paint
    private var mChecked = false
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

    private fun parseAttr(context: Context, attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.BreathLightColorItemView)
        mColor = typedArray.getColor(R.styleable.BreathLightColorItemView_blc_color, Color.BLUE)
        mInnerRadius = typedArray.getDimension(
            R.styleable.BreathLightColorItemView_blc_inner_radius,
            DisplaySizeUtils.dip2px(context, DEFAULT_IN_RADIUS).toFloat()
        )
        mOutRadius = typedArray.getDimension(
            R.styleable.BreathLightColorItemView_blc_outer_radius,
            DisplaySizeUtils.dip2px(context, DEFAULT_OUT_RADIUS).toFloat()
        )
        mStokeWidth = typedArray.getDimension(
            R.styleable.BreathLightColorItemView_blc_stroke_width,
            DisplaySizeUtils.dip2px(context, DEFAULT_STROKE_WIDTH).toFloat()
        )
        typedArray.recycle()
    }

    private fun initView() {
        mSolidPaint = Paint()
        mSolidPaint!!.isAntiAlias = true
        mSolidPaint!!.color = mColor
        mSolidPaint!!.setShadowLayer(
            DisplaySizeUtils.dip2px(context, 6f).toFloat(), 0f, DisplaySizeUtils.dip2px(
                context, 2f
            ).toFloat(),
            context.getColor(R.color.breath_light_color_shadow)
        )
        mStrokePaint = Paint()
        mStrokePaint!!.isAntiAlias = true
        mStrokePaint!!.style = Paint.Style.STROKE
        mStrokePaint!!.strokeWidth = mStokeWidth
        mStrokePaint!!.color = mColor
    }

    fun setChecked(checked: Boolean) {
        mChecked = checked
        invalidate()
    }

    fun setColor(color: Int) {
        mColor = color
        mSolidPaint!!.color = mColor
        mStrokePaint!!.color = mColor
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle(width / 2f, height / 2f, mInnerRadius, mSolidPaint)
        if (mChecked) {
            canvas.drawCircle(width / 2f, height / 2f, mOutRadius, mStrokePaint)
        }
    }

    companion object {
        const val DEFAULT_WIDTH = 42f
        const val DEFAULT_HEIGHT = 42f
        const val DEFAULT_IN_RADIUS = 17f
        const val DEFAULT_OUT_RADIUS = 20f
        const val DEFAULT_STROKE_WIDTH = 2f
    }

    init {
        parseAttr(context, attrs)
        setLayerType(LAYER_TYPE_SOFTWARE, null)
        initView()
    }
}