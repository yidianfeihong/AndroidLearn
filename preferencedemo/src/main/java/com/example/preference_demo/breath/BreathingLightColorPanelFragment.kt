package com.example.preference_demo.breath

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coui.appcompat.panel.COUIBottomSheetDialogFragment
import com.coui.appcompat.panel.COUIPanelFragment
import com.coui.appcompat.tablayout.COUITabLayout
import com.example.platform.DisplaySizeUtils
import com.example.preference_demo.R
import com.oplus.settings.feature.display.breathinglight.color.BreathingLightColorAccessView
import com.oplus.settings.feature.display.breathinglight.color.BreathingLightColorAdapter
import com.oplus.settings.feature.display.breathinglight.color.BreathingLightColorController

/***********************************************************
 * Copyright (C), 2010-2020, Oplus. All rights reserved.
 * File: Settings
 * Description:
 * Version: 1.0
 * Date : 2022-07-14
 * Author: shiwenming
 *
 * ---------------------Revision History: ---------------------
 * <author> <date> <version> <desc>
 * shiwenming 2022/05/23 1.0 create
</desc></version></date></author> */
class BreathingLightColorPanelFragment : COUIPanelFragment() {

    var callColor: String? = null
    var notificationColor: String? = null
    var gameColor: String? = null
    val selectedColors: MutableList<BreathingLightColor> = mutableListOf()
    lateinit var accessView: BreathingLightColorAccessView

    override fun onCreate(savedInstanceState: Bundle?) {
        retainInstance = true
        super.onCreate(savedInstanceState)
    }

    override fun initView(panelView: View) {
        initToolbar()
        initContent()
    }

    private fun initContent() {
        val view = activity?.let {
            LayoutInflater.from(it)
                .inflate(R.layout.breath_light_color_select_dialog_test, null, false)
        }
        view ?: return
        (contentView as? ViewGroup)?.addView(view)
        val settingsColor = BreathingLightColorController.sSettingsColor ?: return
        settingsColor.selectColor(selectedColors[0].pos)
        val tabLayout = view.findViewById<COUITabLayout>(R.id.tab_layout)
        val recyclerView = view.findViewById<RecyclerView>(R.id.circle_recyclerview)
        val colors = settingsColor.optionColors
        colors ?: return
        val layoutParams = recyclerView.layoutParams as LinearLayout.LayoutParams
        val spanCount: Int
        if (colors.size <= 8) {
            spanCount = 4
            layoutParams.leftMargin = DisplaySizeUtils.dip2px(requireContext(), 60f)
            layoutParams.rightMargin = DisplaySizeUtils.dip2px(requireContext(), 60f)
        } else {
            spanCount = 5
            layoutParams.leftMargin = DisplaySizeUtils.dip2px(requireContext(), 43f)
            layoutParams.rightMargin = DisplaySizeUtils.dip2px(requireContext(), 43f)
        }
        recyclerView.layoutParams = layoutParams
        val layoutManager: GridLayoutManager =
            object : GridLayoutManager(requireContext(), spanCount) {
                override fun canScrollHorizontally(): Boolean {
                    return false
                }

                override fun canScrollVertically(): Boolean {
                    return false
                }
            }
        val adapter = BreathingLightColorAdapter(colors, layoutManager)
        adapter.setOnItemClickListener(object : BreathingLightColorAdapter.OnItemClickListener {
            override fun onItemClick(view: View?, position: Int, checkColor: String?) {
                selectedColors[tabLayout.selectedTabPosition] = BreathingLightColor().apply {
                    pos = position
                    color = checkColor
                }
            }
        })
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        recyclerView.post {
            val width = recyclerView.measuredWidth
            var itemWidth = 0
            val itemView = layoutManager.getChildAt(0)
            if (itemView != null) {
                itemWidth = itemView.width
            }
            val vMargin = DisplaySizeUtils.dip2px(requireContext(), 20f)
            recyclerView.addItemDecoration(
                BreathingLightItemDecoration(
                    spanCount,
                    vMargin,
                    width,
                    itemWidth
                )
            )
        }
        val callTab =
            tabLayout.newTab()
                .setText(requireContext().getText(R.string.breathing_light_color_call))
        val notificationTab =
            tabLayout.newTab()
                .setText(requireContext().getText(R.string.breathing_light_color_notification))
        val gameTab =
            tabLayout.newTab()
                .setText(requireContext().getText(R.string.breathing_light_color_game))
        tabLayout.addTab(callTab)
        tabLayout.addTab(notificationTab)
        tabLayout.addTab(gameTab)

        tabLayout.addOnTabSelectedListener(object : COUITabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: COUITabLayout.Tab?) {
                tab ?: return
                adapter.refreshView(selectedColors[tab.position].pos)

            }

            override fun onTabUnselected(tab: COUITabLayout.Tab?) {

            }

            override fun onTabReselected(tab: COUITabLayout.Tab?) {

            }

        })
    }

    private fun dismiss() {
        if (parentFragment is COUIBottomSheetDialogFragment) {
            (parentFragment as COUIBottomSheetDialogFragment?)!!.dismiss()
        }
    }

    private fun initToolbar() {
        val mToolbar = toolbar
        val layoutParams = mToolbar.layoutParams as LinearLayout.LayoutParams
        layoutParams.bottomMargin = 0
        mToolbar.layoutParams = layoutParams
        mToolbar.visibility = View.VISIBLE
        mToolbar.setTitle(R.string.breathing_light_color)
        mToolbar.isTitleCenterStyle = true

        draggableLinearLayout.setDividerVisibility(false)
        draggableLinearLayout.dragView.setImageDrawable(null)
        val toolbar = toolbar
    }
}