package com.stylingandroid.prism.filter;

import android.graphics.Color;

public class TintFilter extends RepeatableFilter {
    private static final float DEFAULT_TINT_FACTOR = 0.2f;
    private static final int HEX_FF = 0xFF;
    private final float tintFactor;

    public TintFilter() {
        this(DEFAULT_TINT_FACTOR);
    }

    public TintFilter(float tintFactor) {
        super();
        this.tintFactor = tintFactor;
    }

    @Override
    public Integer onFilter(Integer colour) {
        int red = Color.red(colour);
        int green = Color.green(colour);
        int blue = Color.blue(colour);
        return Color.argb(
                Color.alpha(colour),
                red + (int) ((float) (HEX_FF - red) * tintFactor),
                green + (int) ((float) (HEX_FF - green) * tintFactor),
                blue + (int) ((float) (HEX_FF - blue) * tintFactor));
    }
}
