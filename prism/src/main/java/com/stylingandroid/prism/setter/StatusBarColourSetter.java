package com.stylingandroid.prism.setter;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.Window;

import com.stylingandroid.prism.filter.ColourFilter;

class StatusBarColourSetter extends BaseColourSetter {
    private final Window window;
    private final int osVersion;

    public StatusBarColourSetter(Window window, ColourFilter filter) {
        this(window, filter, Build.VERSION.SDK_INT);
    }

    StatusBarColourSetter(Window window, ColourFilter filter, int osVersion) {
        super(filter);
        this.window = window;
        this.osVersion = osVersion;
    }

    @Override
    public void onSetColour(int colour) {
        if (osVersion >= Build.VERSION_CODES.LOLLIPOP) {
            setStatusBarColour(colour);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBarColour(int colour) {
        window.setStatusBarColor(colour);
    }
}
