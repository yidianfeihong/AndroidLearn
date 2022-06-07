package com.example.preference_demo;

import androidx.annotation.IntDef;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

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
public class SpatialAudioConsts {
    public static final int UNAVAILABLE = -1;
    public static final int UNSELECTED = 0;
    public static final int SELECTED = 1;

    @IntDef({UNSELECTED, SELECTED, UNAVAILABLE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface SpatialAudioItemState {

    }

    public static final int MODE_UNAVAILABLE = -1;
    public static final int MODE_OFF = 0;
    public static final int MODE_FIX = 1;
    public static final int MODE_HEAD_TRACKING = 2;

    @IntDef({MODE_OFF, MODE_FIX, MODE_HEAD_TRACKING, MODE_UNAVAILABLE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface SpatialAudioMode {

    }

}