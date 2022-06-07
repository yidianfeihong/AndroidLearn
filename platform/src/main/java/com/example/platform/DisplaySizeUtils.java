package com.example.platform;

import android.content.Context;

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
public class DisplaySizeUtils {

    // 方法1
    public static int dip2px(Context ctx, float dp) {
        float scale = ctx.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    // 方法2
//    public static int dp2px(Context ctx, float dp) {
//        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, ctx.getResources().getDisplayMetrics());
//    }

}
