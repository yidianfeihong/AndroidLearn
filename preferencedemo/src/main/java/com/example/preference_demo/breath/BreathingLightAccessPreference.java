package com.example.preference_demo.breath;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.preference.PreferenceViewHolder;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.coui.appcompat.cardlist.COUICardListHelper;
import com.coui.appcompat.panel.COUIBottomSheetDialog;
import com.coui.appcompat.panel.COUIBottomSheetDialogFragment;
import com.coui.appcompat.preference.COUIPreference;
import com.coui.appcompat.tablayout.COUITabLayout;
import com.example.platform.DisplaySizeUtils;
import com.example.preference_demo.R;
import com.oplus.settings.feature.display.breathinglight.color.BreathingLightColorAdapter;
import com.oplus.settings.feature.display.breathinglight.color.BreathingLightColorController;

import java.util.List;

/***********************************************************
 ** Copyright (C), 2010-2020, Oplus. All rights reserved.
 ** File: AndroidLearn
 ** Description: 
 ** Version: 1.0
 ** Date : 2022-06-28
 ** Author: shiwenming
 **
 ** ---------------------Revision History: ---------------------
 ** <author> <date> <version > <desc>
 ** shiwenming 2022/05/23 1.0 create
 ****************************************************************/
public class BreathingLightAccessPreference extends COUIPreference {

    private String TAG = "BreathingLightColor";

    public BreathingLightAccessPreference(Context context) {
        this(context, null);
    }

    public BreathingLightAccessPreference(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BreathingLightAccessPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayoutResource(R.layout.breath_light_color);
    }

    @Override
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        COUICardListHelper.setItemCardBackground(holder.itemView, COUICardListHelper.getPositionInGroup(this));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BreathingLightColorController.loadData(getContext(), new BreathingLightColorController.OnLoadStatusCallback() {
                    @Override
                    public void onFailure() {

                    }

                    @Override
                    public void onSuccess(@NonNull BreathingLightSettingsColor settingsColor) {
                        openDialog(settingsColor);
                    }
                });
            }
        });
    }

    private void openDialog(BreathingLightSettingsColor settingsColor) {
        View view = View.inflate(getContext(), R.layout.breath_light_color_select_dialog, null);
        View confirmImgView = view.findViewById(R.id.iv_confirm);
        View cancelImgView = view.findViewById(R.id.iv_cancel);
        COUITabLayout tabLayout = view.findViewById(R.id.tab_layout);
        RecyclerView recyclerView = view.findViewById(R.id.circle_recyclerview);

        List<BreathingLightColor> colors = settingsColor.optionColors;

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) recyclerView.getLayoutParams();
        int spanCount;
        if (colors.size() <= 8) {
            spanCount = 4;
            layoutParams.leftMargin = DisplaySizeUtils.dip2px(getContext(), 60);
            layoutParams.rightMargin = DisplaySizeUtils.dip2px(getContext(), 60);
        } else {
            spanCount = 5;
            layoutParams.leftMargin = DisplaySizeUtils.dip2px(getContext(), 43);
            layoutParams.rightMargin = DisplaySizeUtils.dip2px(getContext(), 43);
        }
        recyclerView.setLayoutParams(layoutParams);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), spanCount) {
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }

            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(layoutManager);
        BreathingLightColorAdapter adapter = new BreathingLightColorAdapter(colors, layoutManager);
        adapter.setOnItemClickListener(new BreathingLightColorAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(@Nullable View view, int position, @Nullable String checkColor) {

            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.post(() -> {
            int width = recyclerView.getMeasuredWidth();
            int itemWidth = 0;
            View itemView = layoutManager.getChildAt(0);
            if (itemView != null) {
                itemWidth = itemView.getWidth();
            }
            int vMargin = DisplaySizeUtils.dip2px(getContext(), 20);
            recyclerView.addItemDecoration(new BreathingLightItemDecoration(spanCount, vMargin, width, itemWidth));
        });
        COUITabLayout.Tab tab1 = tabLayout.newTab().setText(getContext().getText(R.string.breathing_light_color_call));
        COUITabLayout.Tab tab2 = tabLayout.newTab().setText(getContext().getText(R.string.breathing_light_color_notification));
        COUITabLayout.Tab tab3 = tabLayout.newTab().setText(getContext().getText(R.string.breathing_light_color_game));

        tabLayout.addTab(tab1);
        tabLayout.addTab(tab2);
        tabLayout.addTab(tab3);

        tabLayout.addOnTabSelectedListener(new COUITabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(COUITabLayout.Tab tab) {
                System.out.println();
            }

            @Override
            public void onTabUnselected(COUITabLayout.Tab tab) {
                System.out.println();
            }

            @Override
            public void onTabReselected(COUITabLayout.Tab tab) {
                System.out.println();
            }
        });

        COUIBottomSheetDialog dialog = new COUIBottomSheetDialog(getContext(), com.support.appcompat.R.style.DefaultBottomSheetDialog);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getBehavior().setDraggable(false);
        dialog.setContentView(view);
        dialog.getDragableLinearLayout().getDragView().setVisibility(View.GONE);

        confirmImgView.setOnClickListener(v -> dialog.cancel());
        cancelImgView.setOnClickListener(v -> dialog.cancel());

        dialog.show();
    }

    public void showDialogFragment() {
        COUIBottomSheetDialogFragment dialogFragment = new COUIBottomSheetDialogFragment();
        dialogFragment.setMainPanelFragment(new BreathingLightColorPanelFragment());
        dialogFragment.show(mFragmentManager, "");
    }

    FragmentManager mFragmentManager;

}

