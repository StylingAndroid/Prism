package com.stylingandroid.prism.setter;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.view.Window;

import com.stylingandroid.prism.filter.Filter;

class SystemChromeSetter extends BaseSetter {
    private final Window window;
    private final int osVersion;

    public SystemChromeSetter(Window window, Filter filter) {
        this(window, filter, Build.VERSION.SDK_INT);
    }

    SystemChromeSetter(Window window, Filter filter, int osVersion) {
        super(filter);
        this.window = window;
        this.osVersion = osVersion;
    }

    @Override
    public void onSetColour(@ColorInt int colour) {
        if (osVersion >= Build.VERSION_CODES.LOLLIPOP) {
            setStatusBarColour(colour);
            setNavigationBarColour(colour);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBarColour(@ColorInt int colour) {
        window.setStatusBarColor(colour);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setNavigationBarColour(@ColorInt int colour) {
        window.setNavigationBarColor(colour);
    }
}
