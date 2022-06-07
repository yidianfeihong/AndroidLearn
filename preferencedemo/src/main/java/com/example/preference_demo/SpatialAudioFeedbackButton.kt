package com.example.preference_demo.breath

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.coui.appcompat.pressfeedback.COUIPressFeedbackHelper
import com.example.platform.DisplaySizeUtils
import com.example.preference_demo.R
import com.example.preference_demo.SpatialAudioFeedbackView

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
class SpatialAudioFeedbackButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var mColor = Color.TRANSPARENT
    private var mSolidRadius = 0f
    private var mStrokeRadius = 0f
    var pressFeedbackHelper: COUIPressFeedbackHelper
    private var mStokeWidth = 0f
    private lateinit var mSolidPaint: Paint
    private lateinit var mStrokePaint: Paint
    private var iconResId = 0
    var iconDrawable: Drawable? = null


    init {
        initView()
        pressFeedbackHelper =
            COUIPressFeedbackHelper(this, COUIPressFeedbackHelper.CARD_PRESS_FEEDBACK)
    }

    private fun initView() {
        mSolidPaint = Paint()
        mSolidPaint.isAntiAlias = true
        mSolidPaint.color = mColor

        mStrokePaint = Paint()
        mStrokePaint.isAntiAlias = true
        mStokeWidth = DisplaySizeUtils.dip2px(context, DEFAULT_STROKE_WIDTH).toFloat()
        mStrokePaint.color = context.getColor(R.color.breath_light_color_shadow)
        mStrokePaint.style = Paint.Style.STROKE
        mStrokePaint.strokeWidth = mStokeWidth
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

    fun setColor(color: Int) {
        mColor = color
        mSolidPaint.color = mColor
        postInvalidate()
    }


    fun setImgRes(resId: Int) {
        if ((resId <= 0) || (iconResId == resId)) {
            return
        }
        iconResId = resId
        iconDrawable = ContextCompat.getDrawable(context, iconResId)
        postInvalidate()
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
//        mSolidRadius = width / 2f
//        mStrokeRadius = width / 2 - mStokeWidth / 2
//        canvas.drawCircle(width / 2f, height / 2f, mSolidRadius, mSolidPaint)
//        canvas.drawCircle(width / 2f, height / 2f, mStrokeRadius, mStrokePaint)
        drawSolidCircle(canvas)
        drawStroke(canvas)
        drawCenterIcon(canvas)
    }

    private fun drawSolidCircle(canvas: Canvas) {
        mSolidRadius = width / 2.0f
        canvas.drawCircle(width / 2.0f, height / 2.0f, mSolidRadius, mSolidPaint)
    }

    private fun drawStroke(canvas: Canvas) {
        mStrokeRadius = width / 2.0f - DisplaySizeUtils.dip2px(
            context,
            DEFAULT_STROKE_WIDTH
        ) / 2.0f
        canvas.drawCircle(width / 2f, height / 2f, mStrokeRadius, mStrokePaint)
    }

    private fun drawCenterIcon(canvas: Canvas) {
        if (iconResId != Resources.ID_NULL) {
            val drawable = iconDrawable ?: return
            drawable.state = drawableState
            drawable.bounds = updateImageRectF()
            drawable.draw(canvas)
        }
    }

    private fun updateImageRectF(): Rect {
        val rect = Rect()
        val height = DisplaySizeUtils.dip2px(context, SpatialAudioFeedbackView.DEFAULT_IMG_SIZE)
        val width = DisplaySizeUtils.dip2px(context, SpatialAudioFeedbackView.DEFAULT_IMG_SIZE)
        rect.left = DisplaySizeUtils.dip2px(context, SpatialAudioFeedbackView.DEFAULT_IMG_MARGIN)
        rect.top = DisplaySizeUtils.dip2px(context, SpatialAudioFeedbackView.DEFAULT_IMG_MARGIN)
        rect.right = rect.left + width
        rect.bottom = rect.top + height
        return rect
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.e("wenming", "执行动画")
                pressFeedbackHelper.executeFeedbackAnimator(true)
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                Log.e("wenming", "取消动画")
                pressFeedbackHelper.executeFeedbackAnimator(
                    false
                )
            }
        }
        return super.onTouchEvent(event)
    }


    companion object {
        const val DEFAULT_WIDTH = 48f
        const val DEFAULT_HEIGHT = 48f
        const val DEFAULT_STROKE_WIDTH = 1f
    }

}