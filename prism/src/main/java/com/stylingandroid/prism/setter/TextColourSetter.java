package com.stylingandroid.prism.setter;

import android.support.annotation.ColorInt;
import android.widget.TextView;

import com.stylingandroid.prism.filter.ColourFilter;

public class TextColourSetter extends BaseColourSetter {
    private final TextView view;

    public TextColourSetter(TextView view, ColourFilter filter) {
        super(filter);
        this.view = view;
    }

    @Override
    public void onSetColour(@ColorInt int colour) {
        view.setTextColor(colour);
    }
}
