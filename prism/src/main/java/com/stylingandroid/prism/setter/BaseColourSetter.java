package com.stylingandroid.prism.setter;

import com.stylingandroid.prism.ColourSetter;
import com.stylingandroid.prism.filter.ColourFilter;

public abstract class BaseColourSetter implements ColourSetter {
    private final ColourFilter filter;

    protected BaseColourSetter(ColourFilter filter) {
        this.filter = filter;
    }

    public final void setColour(int colour) {
        onSetColour(filter.filter(colour));
    }

    public abstract void onSetColour(int colour);

}
