package com.example.customviewdemo.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;

import com.example.customviewdemo.R;
import com.example.customviewdemo.DisplaySizeUtils;


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
public class CircleCheckedItemView extends View {

    private float mInnerRadius;
    private float mOutRadius;
    private float mStokeWidth;
    private int mColor;
    private Paint mSolidPaint;
    private Paint mStrokePaint;
    private Paint mShadowPaint;
    private boolean mChecked = true;

    private static final float DEFAULT_WIDTH = 42 * 2f;
    private static final float DEFAULT_HEIGHT = 42 * 2f;
    private static final float DEFAULT_IN_RADIUS = 17f;
    private static final float DEFAULT_OUT_RADIUS = 20f;
    private static final float DEFAULT_STROKE_WIDTH = 2f;

    public CircleCheckedItemView(Context context) {
        this(context, null);
    }

    public CircleCheckedItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleCheckedItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        parseAttr(context, attrs);
        initView();
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setChecked(!mChecked);
            }
        });
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

        mShadowPaint = new Paint();
        mShadowPaint.setAntiAlias(true);
        mShadowPaint.setColor(getContext().getResources().getColor(R.color.breath_light_color_shadow));
        mShadowPaint.setShadowLayer(DisplaySizeUtils.dip2px(getContext(), 6), 0, DisplaySizeUtils.dip2px(getContext(), 2), getContext().getResources().getColor(R.color.breath_light_color_shadow));
        mShadowPaint.setMaskFilter(new BlurMaskFilter(2, BlurMaskFilter.Blur.NORMAL));

        mStrokePaint = new Paint();
        mStrokePaint.setAntiAlias(true);
        mStrokePaint.setStyle(Paint.Style.STROKE);
        mStrokePaint.setStrokeWidth(mStokeWidth);
        mStrokePaint.setColor(mColor);
//        setShadow();
    }

    private void setShadow() {
        ViewOutlineProvider outlineProvider = new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setOval(0, 0, getWidth(), getHeight());
            }
        };
        setOutlineProvider(outlineProvider);
    }


    public void setChecked(boolean checked) {
        mChecked = checked;
        invalidate();
    }


    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(getWidth() / 2f + 4, getHeight() / 2f + 4, mInnerRadius, mShadowPaint);
        canvas.drawCircle(getWidth() / 2f, getHeight() / 2f, mInnerRadius, mSolidPaint);
        if (mChecked) {
            canvas.drawCircle(getWidth() / 2f, getHeight() / 2f, mOutRadius, mStrokePaint);
        }
    }
}
