package com.stylingandroid.prism.setter;

import android.view.View;

import com.stylingandroid.prism.filter.ColourFilter;

class ViewBackgroundColourSetter extends BaseColourSetter {
    private final View view;

    public ViewBackgroundColourSetter(View view, ColourFilter filter) {
        super(filter);
        this.view = view;
    }

    @Override
    public void onSetColour(int colour) {
        view.setBackgroundColor(colour);
    }
}
