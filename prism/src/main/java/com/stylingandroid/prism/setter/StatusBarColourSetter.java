package com.stylingandroid.prism.setter;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.Window;

import com.stylingandroid.prism.filter.ColourFilter;

class StatusBarColourSetter extends BaseColourSetter {
    private final Window window;

    public StatusBarColourSetter(Window window, ColourFilter filter) {
        super(filter);
        this.window = window;
    }

    @Override
    public void onSetColour(int colour) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setStatusBarColour(colour);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBarColour(int colour) {
        window.setStatusBarColor(colour);
    }
}
