package com.stylingandroid.prism.setter;

import android.support.annotation.ColorInt;

import com.stylingandroid.prism.Setter;
import com.stylingandroid.prism.filter.Filter;

public abstract class BaseSetter implements Setter {
    private final Filter filter;
    private final boolean transientChanges;
    private int lastColour = 0;

    protected BaseSetter(Filter filter) {
        this(filter, true);
    }

    protected BaseSetter(Filter filter, boolean transientChanges) {
        this.filter = filter;
        this.transientChanges = transientChanges;
    }

    @Override
    public final void setColour(@ColorInt int colour) {
        if (colour != lastColour) {
            lastColour = colour;
            onSetColour(filter.filter(colour));
        }
    }

    @Override
    public final void setTransientColour(@ColorInt int colour) {
        if (transientChanges) {
            setColour(colour);
        }
    }

    public abstract void onSetColour(@ColorInt int colour);

}
