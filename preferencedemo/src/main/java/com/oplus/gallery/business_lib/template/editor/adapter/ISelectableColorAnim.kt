package com.oplus.gallery.business_lib.template.editor.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

interface ISelectableColorAnim {

    fun setSelectedAnimView(view: View?)
    fun setSelectedColor(color: Int)
    fun setDisableColor(color: Int)
    fun setUnselectedColor(color: Int)
    fun setSelectedAnimEnable(enable: Boolean)
    fun isSelectedAnimEnable(): Boolean
    fun setSelectedAnimListener(animationListener: Any?)
    fun setSelectedState(selectedState: Int)
    fun isSelected(): Boolean
    fun onViewRecycled(holder: RecyclerView.ViewHolder?)
}
