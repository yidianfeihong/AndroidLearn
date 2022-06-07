package com.example.preference_demo

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.animation.PathInterpolator
import androidx.preference.PreferenceViewHolder
import com.coui.appcompat.preference.COUIPreference
import com.example.preference_demo.SpatialAudioConsts.SELECTED
import com.example.preference_demo.breath.SpatialAudioFeedbackButton

/***********************************************************
 * Copyright (C), 2010-2020, Oplus. All rights reserved.
 * File: Settings
 * Description:
 * Version: 1.0
 * Date : 2022-06-23
 * Author: shiwenming
 *
 * ---------------------Revision History: ---------------------
 * <author> <date> <version> <desc>
 * shiwenming 2022/05/23 1.0 create
 */
class SpatialAudioSwitchPreference @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : COUIPreference(context, attrs, defStyleAttr) {

    private lateinit var mModeOffView: SpatialAudioModeView
    private lateinit var mModeFixView: SpatialAudioModeView
    private lateinit var mModeContainerView: ViewGroup
    private lateinit var mModeHeadTrackingView: SpatialAudioModeView


    override fun onBindViewHolder(holder: PreferenceViewHolder) {
        super.onBindViewHolder(holder)
        val view = holder.itemView.findViewById<SpatialAudioFeedbackButton>(R.id.btn_test)
        val view2 = holder.itemView.findViewById<SpatialAudioFeedbackView>(R.id.btn_test2)
        val view3 = holder.itemView.findViewById<SpatialAudioFeedbackButton>(R.id.btn_test3)
        view.setImgRes(R.drawable.spatial_audio_mode_on)
        view2.setImgRes(R.drawable.spatial_audio_mode_on)
        mModeOffView = holder.itemView.findViewById(R.id.mode_off)
        mModeFixView = holder.itemView.findViewById(R.id.mode_fixed)
        mModeHeadTrackingView = holder.itemView.findViewById(R.id.mode_head_tracking)
        mModeOffView.setClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                mModeOffView.updateViewState(SELECTED)
            }
        })
        mModeFixView.setClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                mModeFixView.updateViewState(SELECTED)
            }
        })
        mModeHeadTrackingView.setClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                mModeHeadTrackingView.updateViewState(SELECTED)
            }
        })
//
//        view.setOnClickListener {
//            showAnimation(view)
//        }

        view2.setOnClickListener {
            showAnimation(view2)
        }

        view3.setOnClickListener {
            showAnimation(view3)
        }

    }

    private var currentColor = Color.TRANSPARENT
    private var colorAnimatorSet: AnimatorSet? = null

    private fun showAnimation(view: SpatialAudioFeedbackButton) {
        val startColor = Color.TRANSPARENT
        val targetColor = context.getColor(R.color.spatial_audio_item_selected_bg)
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
            duration = ANIM_APPEAR_DURATION
            playTogether(colorAnimator)
            start()
        }
    }

    private fun showAnimation(view: SpatialAudioFeedbackView) {
        val startColor = Color.TRANSPARENT
        val targetColor = context.getColor(R.color.spatial_audio_item_selected_bg)
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
            duration = ANIM_APPEAR_DURATION
            playTogether(colorAnimator)
            start()
        }
    }

    companion object {
        private val TAG = SpatialAudioSwitchPreference::class.java.simpleName
        private const val ALPHA_ANIM_CONTROL_X1 = 0.33f
        private const val ALPHA_ANIM_CONTROL_Y1 = 0f
        private const val ALPHA_ANIM_CONTROL_X2 = 0.67f
        private const val ALPHA_ANIM_CONTROL_Y2 = 1f
        private const val ANIM_APPEAR_DURATION = 280L
        private const val ANIM_DISAPPEAR_DURATION = 150L
    }

    init {
        layoutResource = R.layout.spatial_audio_content
    }

}