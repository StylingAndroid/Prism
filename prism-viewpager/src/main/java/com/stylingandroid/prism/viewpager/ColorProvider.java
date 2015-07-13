package com.stylingandroid.prism.viewpager;

import android.support.annotation.ColorInt;

public interface ColorProvider {
    @ColorInt int getColor(int position);
    int getCount();
}
