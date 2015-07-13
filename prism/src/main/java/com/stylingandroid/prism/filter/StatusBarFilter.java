package com.stylingandroid.prism.filter;

import android.graphics.Color;

public class StatusBarFilter extends RepeatableFilter {
    private static final int ALPHA = 0x7F;

    @Override
    public Integer onFilter(Integer colour) {
        return Color.argb(ALPHA, Color.red(colour), Color.green(colour), Color.blue(colour));
    }
}
