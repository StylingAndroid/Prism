package com.stylingandroid.prism;

import android.support.annotation.ColorInt;

import java.util.ArrayList;
import java.util.List;

public class BaseColourChangeTrigger implements ColourChangeTrigger {
    private final List<ColourSetter> colourSetters = new ArrayList<>();

    @Override
    public void addColourSetter(ColourSetter colourSetter) {
        colourSetters.add(colourSetter);
    }

    @Override
    public void removeColourSetter(ColourSetter colourSetter) {
        colourSetters.remove(colourSetter);
    }

    public boolean hasNoColourSetters() {
        return colourSetters.isEmpty();
    }

    protected void setColour(@ColorInt int colour) {
       setColour(colour, false);
    }

    protected void setColour(@ColorInt int colour, boolean transientChange) {
        for (ColourSetter colourSetter : colourSetters) {
            colourSetter.setColour(colour, transientChange);
        }
    }
}
