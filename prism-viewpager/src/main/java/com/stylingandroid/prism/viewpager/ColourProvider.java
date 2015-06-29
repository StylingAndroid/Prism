package com.stylingandroid.prism.viewpager;

import android.support.annotation.ColorInt;

public interface ColourProvider {
    @ColorInt int getColour(int position);
    int getCount();
}
