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
import com.example.platform.DisplaySizeUtils;


/***********************************************************
 ** Copyright (C), 2010-2020, Oplus. All rights reserved.
 ** File: AndroidLearn
 ** Description: 
 ** Version: 1.0
 ** Date : 2022-06-30
 ** Author: shiwenming
 **
 ** ---------------------Revision History: ---------------------
 ** <author> <date> <version > <desc>
 ** shiwenming 2022/05/23 1.0 create
 ****************************************************************/
public class BreathLightColorItemView extends View {

    private float mInnerRadius;
    private float mOutRadius;
    private float mStokeWidth;
    private int mColor;
    private Paint mSolidPaint;
    private Paint mStrokePaint;
    private boolean mChecked = true;


    private static final float DEFAULT_WIDTH = 42 * 3f;
    private static final float DEFAULT_HEIGHT = 42 * 3f;
    private static final float DEFAULT_IN_RADIUS = 17f;
    private static final float DEFAULT_OUT_RADIUS = 20f;
    private static final float DEFAULT_STROKE_WIDTH = 2f;
    private static final float DEFAULT_ELEVATION = 0f;


    public BreathLightColorItemView(Context context) {
        this(context, null);
    }

    public BreathLightColorItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BreathLightColorItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        parseAttr(context, attrs);
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        initView();
        setOnClickListener(v -> setChecked(!mChecked));
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

    private void parseAttr(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BreathLightColorItemView);
        mColor = typedArray.getColor(R.styleable.BreathLightColorItemView_blc_color, Color.BLUE);
        mInnerRadius = typedArray.getDimension(R.styleable.BreathLightColorItemView_blc_inner_radius, DisplaySizeUtils.dip2px(context, DEFAULT_IN_RADIUS));
        mOutRadius = typedArray.getDimension(R.styleable.BreathLightColorItemView_blc_outer_radius, DisplaySizeUtils.dip2px(context, DEFAULT_OUT_RADIUS));
        mStokeWidth = typedArray.getDimension(R.styleable.BreathLightColorItemView_blc_stroke_width, DisplaySizeUtils.dip2px(context, DEFAULT_STROKE_WIDTH));
        typedArray.recycle();
    }

    private void initView() {
        mSolidPaint = new Paint();
        mSolidPaint.setAntiAlias(true);
        mSolidPaint.setColor(mColor);
        mSolidPaint.setShadowLayer(DisplaySizeUtils.dip2px(getContext(), 6), 0, DisplaySizeUtils.dip2px(getContext(), 2), getContext().getResources().getColor(R.color.breath_light_color_shadow));
        mStrokePaint = new Paint();
        mStrokePaint.setAntiAlias(true);
        mStrokePaint.setStyle(Paint.Style.STROKE);
        mStrokePaint.setStrokeWidth(mStokeWidth);
        mStrokePaint.setColor(mColor);
    }

    public void setChecked(boolean checked) {
        mChecked = checked;
        invalidate();
    }

    public void setColor(int color) {
        mColor = color;
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(getWidth() / 2f, getHeight() / 2f, mInnerRadius, mSolidPaint);
        if (mChecked) {
            canvas.drawCircle(getWidth() / 2f, getHeight() / 2f, mOutRadius, mStrokePaint);
        }
    }
}
