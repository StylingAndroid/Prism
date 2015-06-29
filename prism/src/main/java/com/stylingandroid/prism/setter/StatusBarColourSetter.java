package com.stylingandroid.prism.setter;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.view.Window;

import com.stylingandroid.prism.filter.ColourFilter;

class StatusBarColourSetter extends BaseColourSetter {
    private final Window window;
    private final int osVersion;

    public StatusBarColourSetter(Window window, ColourFilter<Integer, Integer> filter) {
        this(window, filter, Build.VERSION.SDK_INT);
    }

    StatusBarColourSetter(Window window, ColourFilter<Integer, Integer> filter, int osVersion) {
        super(filter);
        this.window = window;
        this.osVersion = osVersion;
    }

    @Override
    public void onSetColour(@ColorInt int colour) {
        if (osVersion >= Build.VERSION_CODES.LOLLIPOP) {
            setStatusBarColour(colour);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBarColour(@ColorInt int colour) {
        window.setStatusBarColor(colour);
    }
}
