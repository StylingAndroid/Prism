package com.stylingandroid.prism.filter;

public abstract class BaseColourFilter implements ColourFilter {
    private int lastInput = 0;
    private int lastOutput;

    public BaseColourFilter() {
        lastOutput = onFilter(lastInput);
    }

    public final int filter(int colour) {
        if (lastInput != colour) {
            lastInput = colour;
            lastOutput = onFilter(colour);
        }
        return lastOutput;
    }

    public abstract int onFilter(int colour);
}
