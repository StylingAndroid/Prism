package com.stylingandroid.prism.setter;

import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.stylingandroid.prism.ColourSetter;
import com.stylingandroid.prism.filter.ColourFilter;
import com.stylingandroid.prism.filter.IdentityFilter;

import java.util.ArrayList;
import java.util.List;

public final class ColourSetterFactory {
    public static final ColourFilter IDENTITY_COLOUR_FILTER = new IdentityFilter();
    private static final List<SetterFactory> FACTORIES = new ArrayList<>();

    private ColourSetterFactory() {
        //NO-OP
    }

    static void registerFactory(SetterFactory factory) {
        FACTORIES.add(factory);
    }

    static void unregisterFactory(SetterFactory factory) {
        FACTORIES.remove(factory);
    }

    public static ColourSetter getBackgroundSetter(View view, ColourFilter filter) {
        if (FabColourSetter.isFab(view)) {
            return new FabColourSetter(view, getSafeColourFilter(filter));
        }
        return new ViewBackgroundColourSetter(view, getSafeColourFilter(filter));
    }

    public static ColourSetter getBackgroundSetter(Window window, ColourFilter filter) {
        return new StatusBarColourSetter(window, getSafeColourFilter(filter));
    }

    public static ColourSetter getTextSetter(TextView view, ColourFilter filter) {
        return new TextColourSetter(view, getSafeColourFilter(filter));
    }

    public static ColourSetter getColourSetter(View view, ColourFilter filter) {
        ColourSetter setter = null;
        for (SetterFactory factory : FACTORIES) {
            setter = factory.getColourSetter(view, getSafeColourFilter(filter));
        }
        if (setter == null) {
            if (FabColourSetter.isFab(view)) {
                setter = new FabColourSetter(view, getSafeColourFilter(filter));
            } else {
                setter = new ViewBackgroundColourSetter(view, getSafeColourFilter(filter));
            }
        }
        return setter;
    }

    private static ColourFilter getSafeColourFilter(ColourFilter colourFilter) {
        if (colourFilter != null) {
            return colourFilter;
        }
        return IDENTITY_COLOUR_FILTER;
    }
}
