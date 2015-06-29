package com.stylingandroid.prism;

import android.support.annotation.ColorInt;

import java.util.List;

class Chameleon implements ColourSetter {
    private final List<ColourSetter> colourSetters;

    Chameleon(List<ColourSetter> colourSetters) {
        this.colourSetters = colourSetters;
    }

    @Override
    public void setColour(@ColorInt int colour) {
        setColour(colour, false);
    }

    @Override
    public void setColour(@ColorInt int colour, boolean transientChange) {
        for (ColourSetter colourSetter : colourSetters) {
            colourSetter.setColour(colour, transientChange);
        }
    }
}
