package com.stylingandroid.prism;

import android.support.annotation.ColorInt;

import java.util.ArrayList;
import java.util.List;

public class BaseTrigger implements Trigger {
    private final List<Setter> setters = new ArrayList<>();

    @Override
    public void addSetter(Setter setter) {
        setters.add(setter);
    }

    @Override
    public void removeSetter(Setter setter) {
        setters.remove(setter);
    }

    public boolean hasNoColourSetters() {
        return setters.isEmpty();
    }

    protected void setColour(@ColorInt int colour) {
        for (Setter setter : setters) {
            setter.setColour(colour);
        }
    }

    protected void setTransientColour(@ColorInt int colour) {
        for (Setter setter : setters) {
            setter.setTransientColour(colour);
        }
    }
}
