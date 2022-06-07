package com.example.preference_demo;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/***********************************************************
 ** Copyright (C), 2010-2020, Oplus. All rights reserved.
 ** File: AndroidLearn
 ** Description: 
 ** Version: 1.0
 ** Date : 2022-06-22
 ** Author: shiwenming
 **
 ** ---------------------Revision History: ---------------------
 ** <author> <date> <version > <desc>
 ** shiwenming 2022/05/23 1.0 create
 ****************************************************************/
public class SpatialAudioModeView1 extends LinearLayout {
    protected Context mContext;
    private View mRootView;
    private ImageView mImageView;
    private TextView mTextView;
    private State mState;
    private View.OnClickListener mClickListener;
    private int mIdleImgRes;
    private int mEnabledImgRes;
    private int mDisabledImgRes;
    private String mDesc;

    public enum State {
        idle, enabled, disabled
    }

    public void setClickListener(OnClickListener mLeftOnClickListener) {
        this.mClickListener = mLeftOnClickListener;
        mRootView.setOnClickListener(mClickListener);
    }

    public SpatialAudioModeView1(Context context) {
        this(context, null);
    }

    public SpatialAudioModeView1(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SpatialAudioModeView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
//        bindData(attrs);
    }


    private void initView(Context context) {
        mContext = context;
        mRootView = LayoutInflater.from(mContext).inflate(R.layout.spatial_audio_item, this, true);
        mImageView = mRootView.findViewById(R.id.mode_icon);
        mTextView = mRootView.findViewById(R.id.mode_desc);
        mRootView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setState(State.enabled);
                refreshState();
            }
        });
    }

//    private void bindData(AttributeSet attrs) {
//        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.SpatialAudioModeView);
//        mIdleImgRes = typedArray.getResourceId(R.styleable.SpatialAudioModeView_icon_idle, 0);
//        mEnabledImgRes = typedArray.getResourceId(R.styleable.SpatialAudioModeView_icon_enabled, 0);
//        mDisabledImgRes = typedArray.getResourceId(R.styleable.SpatialAudioModeView_icon_disabled, 0);
//        mDesc = typedArray.getString(R.styleable.SpatialAudioModeView_desc);
//        mTextView.setText(mDesc);
//        setState(State.idle);
//        refreshState();
//    }


    public void setDesc(String text) {
        mTextView.setText(text);
    }

    public void setIcon(int resId) {
        mImageView.setImageResource(resId);
    }

    public State getState() {
        return mState;
    }

    public void setState(State state) {
        mState = state;
    }

    public void refreshState() {
        switch (mState) {
            case idle:
                setIcon(mIdleImgRes);
                setDesc(mDesc);
                break;
            case enabled:
                setIcon(mEnabledImgRes);
                setDesc(mDesc);
                break;
            case disabled:
                setIcon(mDisabledImgRes);
                setDesc(mDesc);
                mTextView.setTextColor(Color.parseColor("#4D000000"));
                break;
            default:
                break;
        }

    }
}
