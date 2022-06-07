package com.example.preference_demo;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
public class TestItemDecoration extends RecyclerView.ItemDecoration {
    private int space = 36;
    private int column;

    public TestItemDecoration(int column) {
        this.column = column;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {

        int position = parent.getChildAdapterPosition(view);

        if (position % column == 0) {
            outRect.left = 0; //第一列左边贴边
        } else {
            if (parent.getChildAdapterPosition(view) % column == 1) {
                outRect.left = 0;//第二列移动一个位移间距
            } else {
                outRect.left = 0;//由于第二列已经移动了一个间距，所以第三列要移动两个位移间距就能右边贴边，且item间距相等
            }
        }


        outRect.left = 1;
//        outRect.right = 1;

        if (parent.getChildAdapterPosition(view) >= column) {
            outRect.top = 36;
        } else {
            outRect.top = 0;
        }
    }
}
