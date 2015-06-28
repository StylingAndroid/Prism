package com.stylingandroid.prism.setter;

import com.stylingandroid.prism.ColourSetter;
import com.stylingandroid.prism.filter.ColourFilter;

public abstract class BaseColourSetter implements ColourSetter {
    private final ColourFilter<Integer, Integer> filter;
    private final boolean transientChanges;
    private int lastColour = 0;

    protected BaseColourSetter(ColourFilter<Integer, Integer> filter) {
        this(filter, true);
    }

    protected BaseColourSetter(ColourFilter<Integer, Integer> filter, boolean transientChanges) {
        this.filter = filter;
        this.transientChanges = transientChanges;
    }

    public final void setColour(int colour) {
        if (colour != lastColour) {
            lastColour = colour;
            onSetColour(filter.filter(colour));
        }
    }

    public final void setColour(int colour, boolean isTransient) {
        if (!isTransient || transientChanges) {
            setColour(colour);
        }
    }

    public abstract void onSetColour(int colour);

}
