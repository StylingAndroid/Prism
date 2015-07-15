package com.stylingandroid.prism;

import android.support.annotation.ColorInt;

public interface ColorSetter {
    void setColor(@ColorInt int color);

    void setTransientColor(@ColorInt int color);
}
