package com.stylingandroid.prism.setter;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.widget.EdgeEffect;

import com.stylingandroid.prism.filter.Filter;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
final class GlowSetterLollipop extends ViewPagerGlowSetter {
    private final EdgeEffect leftEdgeEffect;
    private final EdgeEffect rightEdgeEffect;

    public GlowSetterLollipop(Filter filter, EdgeEffect leftEdgeEffect, EdgeEffect rightEdgeEffect) {
        super(filter);
        this.leftEdgeEffect = leftEdgeEffect;
        this.rightEdgeEffect = rightEdgeEffect;
    }

    @Override
    public void onSetColour(@ColorInt int colour) {
        leftEdgeEffect.setColor(colour);
        rightEdgeEffect.setColor(colour);
    }
}
