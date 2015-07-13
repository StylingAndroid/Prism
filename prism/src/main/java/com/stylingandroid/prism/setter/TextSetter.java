package com.stylingandroid.prism.setter;

import android.support.annotation.ColorInt;
import android.widget.TextView;

import com.stylingandroid.prism.filter.Filter;

public class TextSetter extends BaseSetter {
    private final TextView view;

    public TextSetter(TextView view, Filter filter) {
        super(filter);
        this.view = view;
    }

    @Override
    public void onSetColour(@ColorInt int colour) {
        view.setTextColor(colour);
    }
}
