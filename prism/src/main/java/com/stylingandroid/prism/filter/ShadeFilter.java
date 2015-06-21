package com.stylingandroid.prism.filter;

import android.graphics.Color;

public class ShadeFilter extends BaseColourFilter {
    private static final float DEFAULT_SHADE_FACTOR = 0.2f;
    private final float shadeFactor;

    public ShadeFilter() {
        this(DEFAULT_SHADE_FACTOR);
    }

    public ShadeFilter(float shadeFactor) {
        super();
        this.shadeFactor = shadeFactor;
    }

    @Override
    public int onFilter(int colour) {
        return Color.argb(
                Color.alpha(colour),
                (int) ((float) Color.red(colour) * shadeFactor),
                (int) ((float) Color.green(colour) * shadeFactor),
                (int) ((float) Color.blue(colour) * shadeFactor));
    }
}
