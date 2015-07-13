package com.stylingandroid.prism.filter;

public abstract class RepeatableFilter implements Filter {
    private int lastInput = 0;
    private int lastOutput;

    public RepeatableFilter() {
        lastOutput = onFilter(lastInput);
    }

    public final Integer filter(Integer colour) {
        if (lastInput != colour) {
            lastInput = colour;
            lastOutput = onFilter(colour);
        }
        return lastOutput;
    }

    public abstract Integer onFilter(Integer colour);
}
