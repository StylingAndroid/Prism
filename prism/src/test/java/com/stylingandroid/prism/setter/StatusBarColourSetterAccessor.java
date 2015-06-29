package com.stylingandroid.prism.setter;

import android.support.annotation.ColorInt;
import android.view.Window;

import com.stylingandroid.prism.filter.ColourFilter;
import com.stylingandroid.prism.filter.IdentityFilter;

public class StatusBarColourSetterAccessor extends BaseColourSetter {
    private final StatusBarColourSetter statusBarColourSetter;

    public static StatusBarColourSetterAccessor newInstance(Window window, int osVersion) {
        IdentityFilter identityFilter = new IdentityFilter();
        StatusBarColourSetter setter = new StatusBarColourSetter(window, identityFilter, osVersion);
        return new StatusBarColourSetterAccessor(setter, identityFilter);
    }

    private StatusBarColourSetterAccessor(StatusBarColourSetter statusBarColourSetter, ColourFilter<Integer, Integer> filter) {
        super(filter);
        this.statusBarColourSetter = statusBarColourSetter;
    }

    @Override
    public void onSetColour(@ColorInt int colour) {
        statusBarColourSetter.setColour(colour);
    }
}
