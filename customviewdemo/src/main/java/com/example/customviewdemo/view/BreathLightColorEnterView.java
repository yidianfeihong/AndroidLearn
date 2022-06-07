package com.example.customviewdemo.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.example.customviewdemo.R;
import com.example.customviewdemo.DisplaySizeUtils;

/***********************************************************
 ** Copyright (C), 2010-2020, Oplus. All rights reserved.
 ** File: AndroidLearn
 ** Description: 
 ** Version: 1.0
 ** Date : 2022-07-01
 ** Author: shiwenming
 **
 ** ---------------------Revision History: ---------------------
 ** <author> <date> <version > <desc>
 ** shiwenming 2022/05/23 1.0 create
 ****************************************************************/
public class BreathLightColorEnterView extends View {

    private static int test = 1;

    private static final float DEFAULT_WIDTH = 41 * test;
    private static final float DEFAULT_HEIGHT = 24 * test;
    private static final float DEFAULT_INNER_RADIUS = 7 * test;
    private static final float DEFAULT_OUTER_RADIUS = 8 * test;
    private static final float DEFAULT_STOKE_WIDTH = 2 * test;
    private Paint mInnerPaint;
    private Paint mOuterPaint;
    private int mLeftColor;
    private int mMidColor;
    private int mRightColor;
    private int mStrokeColor;
    private float mInnerRadius;
    private float mOuterRadius;
    private float mStrokeWidth;


    public BreathLightColorEnterView(Context context) {
        this(context, null);
    }

    public BreathLightColorEnterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BreathLightColorEnterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        parseAttrs(context, attrs);
        init();
    }

    private void parseAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BreathLightColorEnterView);
        mLeftColor = typedArray.getColor(R.styleable.BreathLightColorEnterView_blc_left_color, Color.parseColor("#2D40E9"));
        mMidColor = typedArray.getColor(R.styleable.BreathLightColorEnterView_blc_mid_color, Color.parseColor("#2DA74E"));
        mRightColor = typedArray.getColor(R.styleable.BreathLightColorEnterView_blc_right_color, Color.parseColor("#FB6A35"));
        mStrokeColor = typedArray.getColor(R.styleable.BreathLightColorEnterView_blc_stroke_color, Color.WHITE);
        mInnerRadius = typedArray.getDimension(R.styleable.BreathLightColorEnterView_blc_inner_radius, DEFAULT_INNER_RADIUS);
        mOuterRadius = typedArray.getDimension(R.styleable.BreathLightColorEnterView_blc_outer_radius, DEFAULT_OUTER_RADIUS);
        mStrokeWidth = typedArray.getDimension(R.styleable.BreathLightColorEnterView_blc_stroke_width, DEFAULT_STOKE_WIDTH);
        typedArray.recycle();
    }

    private void init() {
        mInnerPaint = new Paint();
        mInnerPaint.setAntiAlias(true);

        mOuterPaint = new Paint();
        mOuterPaint.setStyle(Paint.Style.STROKE);
        mOuterPaint.setAntiAlias(true);
        mOuterPaint.setColor(mStrokeColor);
        mOuterPaint.setStrokeWidth(DisplaySizeUtils.dip2px(getContext(), mStrokeWidth));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int minWidth = DisplaySizeUtils.dip2px(getContext(), DEFAULT_WIDTH);
        int minHeight = DisplaySizeUtils.dip2px(getContext(), DEFAULT_HEIGHT);

        if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT && getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(minWidth, minHeight);
        } else if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(minWidth, heightSize);
        } else if (getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(widthSize, minHeight);
        }
    }


    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float innerRadius = DisplaySizeUtils.dip2px(getContext(), mInnerRadius);
        float outerRadius = DisplaySizeUtils.dip2px(getContext(), mOuterRadius);

        float moveStep = DisplaySizeUtils.dip2px(getContext(), 10.5f) * test;

        mInnerPaint.setColor(mRightColor);
        canvas.drawCircle(getWidth() / 2f + moveStep, getHeight() / 2f, innerRadius, mInnerPaint);
        canvas.drawCircle(getWidth() / 2f + moveStep, getHeight() / 2f, outerRadius, mOuterPaint);

        mInnerPaint.setColor(mMidColor);
        canvas.drawCircle(getWidth() / 2f, getHeight() / 2f, innerRadius, mInnerPaint);
        canvas.drawCircle(getWidth() / 2f, getHeight() / 2f, outerRadius, mOuterPaint);

        mInnerPaint.setColor(mLeftColor);
        canvas.drawCircle(getWidth() / 2f - moveStep, getHeight() / 2f, innerRadius, mInnerPaint);
        canvas.drawCircle(getWidth() / 2f - moveStep, getHeight() / 2f, outerRadius, mOuterPaint);

    }


}
