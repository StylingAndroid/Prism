package com.stylingandroid.prism;

import android.support.annotation.ColorInt;

public class SetterAdapter implements Setter {
    private final ColorSetter colorSetter;

    public SetterAdapter(ColorSetter colorSetter) {
        this.colorSetter = colorSetter;
    }

    @Override
    public void setColour(@ColorInt int colour) {
        colorSetter.setColor(colour);
    }

    @Override
    public void setColour(@ColorInt int colour, boolean isTransient) {
        colorSetter.setColor(colour, isTransient);
    }
}
