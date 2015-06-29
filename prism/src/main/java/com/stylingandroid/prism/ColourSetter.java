package com.stylingandroid.prism;

import android.support.annotation.ColorInt;

public interface ColourSetter {
    void setColour(@ColorInt int colour);

    void setColour(@ColorInt int colour, boolean isTransient);
}
