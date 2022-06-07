package com.example.preference_demo

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.animation.PathInterpolator
import android.widget.LinearLayout
import android.widget.TextView
import com.example.preference_demo.SpatialAudioConsts.*

/***********************************************************
 * Copyright (C), 2010-2020, Oplus. All rights reserved.
 * File: AndroidLearn
 * Description: 空间音频模式选项的view
 * Version: 1.0
 * Date : 2022-06-22
 * Author: shiwenming
 *
 * ---------------------Revision History: ---------------------
 * <author> <date> <version> <desc>
 * shiwenming 2022/05/23 1.0 create*/
class SpatialAudioModeView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private lateinit var mRootView: View
    private lateinit var mFeedbackView: SpatialAudioFeedbackView
    private lateinit var mDescView: TextView

    @SpatialAudioConsts.SpatialAudioItemState
    private var mState: Int = UNSELECTED
    private var mImgResSelected = 0
    private var mImgResUnselected = 0
    private var mImgResUnavailable = 0
    private var mDesc: String? = null
    private var mSelectedBgColor: Int = 0
    private var mUnSelectedBgColor: Int = 0

    init {
        initView()
        bindData(attrs)
    }

    fun setClickListener(mLeftOnClickListener: OnClickListener?) {
        mFeedbackView.setOnClickListener(mLeftOnClickListener)
    }

    private fun initView() {
        mRootView = LayoutInflater.from(context)
            .inflate(R.layout.spatial_audio_model_item, this, true)
        mFeedbackView = mRootView.findViewById(R.id.feedback)
        mDescView = mRootView.findViewById(R.id.mode_desc)
        gravity = Gravity.CENTER
        orientation = VERTICAL
        mSelectedBgColor = context.getColor(R.color.spatial_audio_item_selected_bg)
        mUnSelectedBgColor = Color.TRANSPARENT
    }

    private fun bindData(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SpatialAudioModeView)
        mImgResSelected = typedArray.getResourceId(
            R.styleable.SpatialAudioModeView_spatial_audio_mode_icon_selected,
            0
        )
        mImgResUnselected =
            typedArray.getResourceId(
                R.styleable.SpatialAudioModeView_spatial_audio_mode_icon_unselected,
                0
            )
        mImgResUnavailable =
            typedArray.getResourceId(
                R.styleable.SpatialAudioModeView_spatial_audio_mode_icon_unavailable,
                0
            )
        mDesc = typedArray.getString(R.styleable.SpatialAudioModeView_spatial_audio_mode_desc)
        mDescView.text = mDesc
        mFeedbackView.setImgRes(mImgResUnselected)
        typedArray.recycle()
    }

    fun setDesc(text: String?) {
        mDescView.text = text
    }

    fun updateViewState(@SpatialAudioConsts.SpatialAudioItemState state: Int) {
        if (mState == state) {
            return
        }
        mState = state
        refreshUI()
    }

    @SuppressLint("PrivateResource")
    fun refreshUI() {

        when (mState) {
            UNSELECTED -> {
                if (mImgResUnselected > 0) {
                    mFeedbackView.setImgRes(mImgResUnselected)
                }
                showAnimation(
                    mFeedbackView,
                    currentColor,
                    mUnSelectedBgColor,
                    ANIM_DISAPPEAR_DURATION
                )
                mFeedbackView.showStroke(context.getColor(R.color.coui_color_secondary_neutral1))
                mDescView.setTextColor(context.getColor(R.color.coui_color_secondary_neutral1))
            }
            SELECTED -> {
//                if (mImgResSelected > 0) {
//                    mFeedbackView.setImgRes(mImgResSelected)
//                }
                showAnimation(
                    mFeedbackView,
                    currentColor,
                    mSelectedBgColor,
                    ANIM_APPEAR_DURATION
                )
//                mDescView.setTextColor(context.getColor(R.color.coui_color_secondary_neutral1))
//                mFeedbackView.hideStroke()
            }
            UNAVAILABLE -> {
                if (mImgResUnavailable > 0) {
                    mFeedbackView.setImgRes(mImgResUnavailable)
                }
                showAnimation(
                    mFeedbackView,
                    currentColor,
                    mUnSelectedBgColor,
                    ANIM_DISAPPEAR_DURATION
                )
                mFeedbackView.showStroke(context.getColor(R.color.coui_color_disabled_neutral1))
                mDescView.setTextColor(context.getColor(R.color.coui_color_disabled_neutral1))
            }
        }
    }

    private var currentColor = Color.TRANSPARENT
    private var colorAnimatorSet: AnimatorSet? = null

    private fun showAnimation(
        view: SpatialAudioFeedbackView,
        startColor: Int,
        targetColor: Int,
        animationDuration: Long
    ) {
        val colorAnimator = ValueAnimator.ofArgb(startColor, targetColor).apply {
            addUpdateListener { animator ->
                currentColor = animator.animatedValue as Int
                view.setColor(currentColor)
            }
        }
        colorAnimatorSet = AnimatorSet().apply {
            interpolator = PathInterpolator(
                ALPHA_ANIM_CONTROL_X1,
                ALPHA_ANIM_CONTROL_Y1,
                ALPHA_ANIM_CONTROL_X2,
                ALPHA_ANIM_CONTROL_Y2
            )
            duration = animationDuration
            playTogether(colorAnimator)
            start()
        }
    }

    companion object {
        private val TAG = SpatialAudioModeView::class.java.simpleName
        private const val ALPHA_ANIM_CONTROL_X1 = 0.33f
        private const val ALPHA_ANIM_CONTROL_Y1 = 0f
        private const val ALPHA_ANIM_CONTROL_X2 = 0.67f
        private const val ALPHA_ANIM_CONTROL_Y2 = 1f
        private const val ANIM_APPEAR_DURATION = 280L
        private const val ANIM_DISAPPEAR_DURATION = 150L
    }


}