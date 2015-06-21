package com.stylingandroid.prism;

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

    protected void setColour(int colour) {
        for (ColourSetter colourSetter : colourSetters) {
            colourSetter.setColour(colour);
        }
    }
}
