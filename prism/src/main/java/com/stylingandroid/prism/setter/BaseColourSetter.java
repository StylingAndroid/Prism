package com.stylingandroid.prism.setter;

import android.support.annotation.ColorInt;

import com.stylingandroid.prism.ColourSetter;
import com.stylingandroid.prism.filter.ColourFilter;

public abstract class BaseColourSetter implements ColourSetter {
    private final ColourFilter filter;
    private final boolean transientChanges;
    private int lastColour = 0;

    protected BaseColourSetter(ColourFilter filter) {
        this(filter, true);
    }

    protected BaseColourSetter(ColourFilter filter, boolean transientChanges) {
        this.filter = filter;
        this.transientChanges = transientChanges;
    }

    public final void setColour(@ColorInt int colour) {
        if (colour != lastColour) {
            lastColour = colour;
            onSetColour(filter.filter(colour));
        }
    }

    public final void setColour(@ColorInt int colour, boolean isTransient) {
        if (!isTransient || transientChanges) {
            setColour(colour);
        }
    }

    public abstract void onSetColour(@ColorInt int colour);

}
