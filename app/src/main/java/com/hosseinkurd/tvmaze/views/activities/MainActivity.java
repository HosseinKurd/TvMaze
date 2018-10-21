package com.hosseinkurd.tvmaze.views.activities;

import android.os.Bundle;
import android.view.View;

import com.hosseinkurd.kurdiautils.toolbox.helpers.LTH;
import com.hosseinkurd.kurdiautils.toolbox.helpers.UIH;
import com.hosseinkurd.kurdiautils.toolbox.utiles.SwipeBackLayout;
import com.hosseinkurd.kurdiautils.views.activities.AbsSwipeActivity;
import com.hosseinkurd.kurdiautils.views.fragments.AbsSwipeFrg;
import com.hosseinkurd.tvmaze.R;
import com.hosseinkurd.tvmaze.views.fragments.MoviesFrg;

public class MainActivity extends AbsSwipeActivity implements SwipeBackLayout.SwipeBackListener {

    @Override
    public void onBackPressed() {
        if (frSwipe != null) {
            onFinished();
            visible(true);
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setResources();
        init();
    }

    @Override
    protected void setPreView() {

    }

    @Override
    protected void setResources() {
        setFragment(MoviesFrg.newInstance(), R.id.fml_holder_activity);
    }

    @Override
    public void setSwipeBackLayout() {
        swipeBackLayout = findViewById(R.id.fml_swipe_holder_activity);
    }

    @Override
    protected void init() {
        setSwipeBackLayout();
        swipeBackLayout.setDragEdge(SwipeBackLayout.DragEdge.TOP);
        swipeBackLayout.setOnSwipeBackListener(this);
        swipeBackLayout.setTranslationY(UIH.getHeight());
    }

    @Override
    protected void visible(boolean hideSwipe) {
        LTH.dLog("TAG_TAG", "Hide Swipe Layout : $hideSwipe");
        if (hideSwipe && frSwipe == null && findViewById(R.id.fml_swipe_holder_activity).getVisibility() == View.VISIBLE) {
            findViewById(R.id.fml_swipe_holder_activity).setVisibility(View.GONE);
        } else if (!hideSwipe && findViewById(R.id.fml_swipe_holder_activity).getVisibility() == View.GONE) {
            findViewById(R.id.fml_swipe_holder_activity).setVisibility(View.VISIBLE);
        }
        // findViewById(R.id.fml_holder_activity).setVisibility(if (hideSwipe) View.VISIBLE else View.GONE)
    }

    @Override
    public void onViewPositionChanged(float fractionAnchor, float fractionScreen) {
        LTH.dLog("TAG_TAG", "onViewPositionChanged --> fractionAnchor :  , " + fractionAnchor + " : $fractionScreen" + fractionScreen);
    }

    @Override
    public void onFinished() {
        if (frSwipe != null) {
            LTH.dLog("TAG_TAG", "****************************** onFinished!");
            getSupportFragmentManager().beginTransaction().remove(frSwipe).commit();
            frSwipe = null;
            swipeBackLayout.setTranslationY(UIH.getHeight());
            visible(true);
        }
    }

    @Override
    public void onAddFragment(AbsSwipeFrg fragment) {
        LTH.dLog("TAG_TAG", "onAddFragment!");
        visible(false);
        frSwipe = fragment;
        getSupportFragmentManager().beginTransaction().replace(R.id.fml_swipe_holder_activity, fragment, fragment.getClass().getSimpleName()).commit();
        swipeBackLayout.animate().translationY(0f);// .setDuration(700)
    }

}