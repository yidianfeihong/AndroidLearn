package com.example.preference_demo.breath;

import java.util.List;

/***********************************************************
 ** Copyright (C), 2010-2020, Oplus. All rights reserved.
 ** File: AndroidLearn
 ** Description: 
 ** Version: 1.0
 ** Date : 2022-07-05
 ** Author: shiwenming
 **
 ** ---------------------Revision History: ---------------------
 ** <author> <date> <version > <desc>
 ** shiwenming 2022/05/23 1.0 create
 ****************************************************************/
public class BreathingLightSettingsColor {

    public String callDefaultColor;
    public String notificationDefaultColor;
    public String gameDefaultColor;
    public List<BreathingLightColor> optionColors;

    public void selectColor(int position) {
        if (optionColors == null || optionColors.size() <= position) {
            return;
        }
        optionColors.get(position).setChecked(true);
        for (int i = 0; i < optionColors.size(); i++) {
            BreathingLightColor breathLightColor = optionColors.get(i);
            if (i == position) {
                breathLightColor.setChecked(true);
            } else {
                breathLightColor.setChecked(false);
            }
        }
    }

    @Override
    public String toString() {
        return "BreathingLightSettingsColor{"
                + "callDefaultColor='" + callDefaultColor + '\''
                + ", notificationDefaultColor='" + notificationDefaultColor + '\''
                + ", gameDefaultColor='" + gameDefaultColor + '\''
                + ", optionColors=" + optionColors + '}';
    }
}
