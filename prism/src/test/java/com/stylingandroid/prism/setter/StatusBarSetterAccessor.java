package com.stylingandroid.prism.setter;

import android.support.annotation.ColorInt;
import android.view.Window;

import com.stylingandroid.prism.filter.Filter;
import com.stylingandroid.prism.filter.IdentityFilter;

public class StatusBarSetterAccessor extends BaseSetter {
    private final StatusBarSetter statusBarColourSetter;

    public static StatusBarSetterAccessor newInstance(Window window, int osVersion) {
        IdentityFilter identityFilter = new IdentityFilter();
        StatusBarSetter setter = new StatusBarSetter(window, identityFilter, osVersion);
        return new StatusBarSetterAccessor(setter, identityFilter);
    }

    private StatusBarSetterAccessor(StatusBarSetter statusBarColourSetter, Filter filter) {
        super(filter);
        this.statusBarColourSetter = statusBarColourSetter;
    }

    @Override
    public void onSetColour(@ColorInt int colour) {
        statusBarColourSetter.setColour(colour);
    }
}
