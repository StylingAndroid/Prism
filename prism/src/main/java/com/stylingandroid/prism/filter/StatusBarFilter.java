package com.stylingandroid.prism.filter;

import android.graphics.Color;

public class StatusBarFilter extends BaseColourFilter {
    @Override
    public int onFilter(int colour) {
        return Color.argb(0x7F, Color.red(colour), Color.green(colour), Color.blue(colour));
    }
}
