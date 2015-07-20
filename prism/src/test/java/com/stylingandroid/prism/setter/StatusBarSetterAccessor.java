package com.stylingandroid.prism.setter;

import android.support.annotation.ColorInt;
import android.view.Window;

import com.stylingandroid.prism.filter.Filter;
import com.stylingandroid.prism.filter.IdentityFilter;

public class StatusBarSetterAccessor extends BaseSetter {
    private final SystemChromeSetter systemChromeSetter;

    public static StatusBarSetterAccessor newInstance(Window window, int osVersion) {
        IdentityFilter identityFilter = new IdentityFilter();
        SystemChromeSetter setter = new SystemChromeSetter(window, identityFilter, osVersion);
        return new StatusBarSetterAccessor(setter, identityFilter);
    }

    private StatusBarSetterAccessor(SystemChromeSetter systemChromeSetter, Filter filter) {
        super(filter);
        this.systemChromeSetter = systemChromeSetter;
    }

    @Override
    public void onSetColour(@ColorInt int colour) {
        systemChromeSetter.setColour(colour);
    }
}
