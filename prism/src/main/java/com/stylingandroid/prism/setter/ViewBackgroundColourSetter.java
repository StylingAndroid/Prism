package com.stylingandroid.prism.setter;

import android.support.annotation.ColorInt;
import android.view.View;

import com.stylingandroid.prism.filter.ColourFilter;

class ViewBackgroundColourSetter extends BaseColourSetter {
    private final View view;

    public ViewBackgroundColourSetter(View view, ColourFilter<Integer, Integer> filter) {
        super(filter);
        this.view = view;
    }

    @Override
    public void onSetColour(@ColorInt int colour) {
        view.setBackgroundColor(colour);
    }
}
