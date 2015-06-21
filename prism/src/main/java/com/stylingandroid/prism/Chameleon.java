package com.stylingandroid.prism;

import java.util.List;

class Chameleon implements ColourSetter {
    private final List<ColourSetter> colourSetters;

    Chameleon(List<ColourSetter> colourSetters) {
        this.colourSetters = colourSetters;
    }

    @Override
    public void setColour(int colour) {
        for (ColourSetter colourSetter : colourSetters) {
            colourSetter.setColour(colour);
        }
    }
}
