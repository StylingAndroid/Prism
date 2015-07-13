package com.stylingandroid.prism.setter;

import android.support.annotation.ColorInt;
import android.view.View;

import com.stylingandroid.prism.filter.Filter;

class ViewBackgroundSetter extends BaseSetter {
    private final View view;

    public ViewBackgroundSetter(View view, Filter filter) {
        super(filter);
        this.view = view;
    }

    @Override
    public void onSetColour(@ColorInt int colour) {
        view.setBackgroundColor(colour);
    }
}
