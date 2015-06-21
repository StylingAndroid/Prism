package com.stylingandroid.prism.viewpager;

import android.support.v4.view.ViewPager;

import com.stylingandroid.prism.ColourSetter;

public class ViewPagerSetter implements ColourSetter {
    private final ViewPager viewPager;

    public ViewPagerSetter(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    @Override
    public void setColour(int colour) {

    }
}
