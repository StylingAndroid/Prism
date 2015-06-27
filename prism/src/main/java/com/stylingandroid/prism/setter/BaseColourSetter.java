package com.stylingandroid.prism.setter;

import com.stylingandroid.prism.ColourSetter;
import com.stylingandroid.prism.filter.ColourFilter;

public abstract class BaseColourSetter implements ColourSetter {
    private final ColourFilter<Integer, Integer> filter;

    protected BaseColourSetter(ColourFilter<Integer, Integer> filter) {
        this.filter = filter;
    }

    public final void setColour(int colour) {
        onSetColour(filter.filter(colour));
    }

    public abstract void onSetColour(int colour);

}
