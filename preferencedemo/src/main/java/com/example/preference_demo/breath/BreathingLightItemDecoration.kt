package com.example.preference_demo.breath

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/***********************************************************
 * Copyright (C), 2010-2020, Oplus. All rights reserved.
 * File: AndroidLearn
 * Description:
 * Version: 1.0
 * Date : 2022-07-05
 * Author: shiwenming
 *
 * ---------------------Revision History: ---------------------
 * <author> <date> <version> <desc>
 * shiwenming 2022/05/23 1.0 create
</desc></version></date></author> */
class BreathingLightItemDecoration(// 列数
    private val spanCount: Int, // 单位是px不是dp
    private val vMargin: Int, private val width: Int, private val itemWidth: Int
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val totalCount = parent.adapter!!.itemCount
        outRect.top = vMargin / 2
        outRect.bottom = vMargin / 2
        val w = (width.toDouble() - itemWidth * spanCount) / (spanCount * (spanCount - 1))
        val p = position % spanCount
        outRect.left = (w * p).toInt()


        // 最上面的一排（columns个item）
        if (position < spanCount) outRect.top = 0
        // 最下面一排
        val tem = (Math.ceil(totalCount.toDouble() / spanCount) * spanCount).toInt()
        if (position >= tem - spanCount) outRect.bottom = 0
    }
}