package com.oplus.settings.feature.display.breathinglight.color

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.preference_demo.breath.BreathingLightColor
import com.example.preference_demo.breath.BreathingLightColorItemView
import com.oplus.settings.feature.display.breathinglight.color.BreathingLightColorAdapter.BlcViewHolder

/***********************************************************
 * Copyright (C), 2010-2020, Oplus. All rights reserved.
 * File: AndroidLearn
 * Description:
 * Version: 1.0
 * Date : 2022-07-04
 * Author: shiwenming
 *
 * ---------------------Revision History: ---------------------
 * <author> <date> <version> <desc>
 * shiwenming 2022/05/23 1.0 create
 ************************************/
class BreathingLightColorAdapter(
    var mData: List<BreathingLightColor>,
    var layoutManager: RecyclerView.LayoutManager
) : RecyclerView.Adapter<BlcViewHolder>() {

    private var oldCheckedPos = -1
    private var mOnItemClickListener: OnItemClickListener? = null

    /**
     * item点击事件回调
     */
    interface OnItemClickListener {
        fun onItemClick(view: View?, position: Int, checkColor: String?)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener?) {
        mOnItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlcViewHolder {
        val itemView = BreathingLightColorItemView(parent.context)
        itemView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        return BlcViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BlcViewHolder, position: Int) {
        val breathLightColor = mData[position]
        (holder.itemView as BreathingLightColorItemView).apply {
            setColor(Color.parseColor(breathLightColor.color))
            if (breathLightColor.checked) {
                setChecked(breathLightColor.checked)
                oldCheckedPos = holder.absoluteAdapterPosition
            }
            setOnClickListener {
                refreshView(holder.layoutPosition)
                mOnItemClickListener?.onItemClick(
                    it,
                    holder.absoluteAdapterPosition,
                    breathLightColor.color
                )
            }
        }
    }

    fun refreshView(newCheckedPos: Int) {
        if (newCheckedPos == oldCheckedPos) {
            return
        }


        layoutManager.findViewByPosition(oldCheckedPos)?.apply {
            (this as BreathingLightColorItemView).setChecked(false)
        }
        layoutManager.findViewByPosition(newCheckedPos)?.apply {
            (this as BreathingLightColorItemView).setChecked(true)
        }
        oldCheckedPos = newCheckedPos
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    class BlcViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}