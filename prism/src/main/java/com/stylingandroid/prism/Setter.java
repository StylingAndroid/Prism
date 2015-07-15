package com.stylingandroid.prism;

import android.support.annotation.ColorInt;

public interface Setter {
    void setColour(@ColorInt int colour);

    void setTransientColour(@ColorInt int colour);
}
