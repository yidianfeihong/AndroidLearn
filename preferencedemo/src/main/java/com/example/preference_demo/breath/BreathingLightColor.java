package com.example.preference_demo.breath;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/***********************************************************
 ** Copyright (C), 2010-2020, Oplus. All rights reserved.
 ** File: AndroidLearn
 ** Description:
 ** Version: 1.0
 ** Date : 2022-07-04
 ** Author: shiwenming
 **
 ** ---------------------Revision History: ---------------------
 ** <author> <date> <version > <desc>
 ** shiwenming 2022/05/23 1.0 create
 ****************************************************************/
public class BreathingLightColor implements Serializable {

    public String color;
    public List<String> lastColors = new ArrayList<>();
    public boolean checked;
    public int pos = -1;

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "BreathingLightColor{"
                + "color='" + color + '\''
                + ", lastColors=" + lastColors
                + '}';
    }
}
