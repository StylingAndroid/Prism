package com.stylingandroid.prism.filter;

import android.graphics.Color;

public class StatusBarFilter extends BaseColourFilter {
    private static final int ALPHA = 0x7F;

    @Override
    public int onFilter(int colour) {
        return Color.argb(ALPHA, Color.red(colour), Color.green(colour), Color.blue(colour));
    }
}
