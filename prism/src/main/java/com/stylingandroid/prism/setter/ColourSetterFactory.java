package com.stylingandroid.prism.setter;

import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.stylingandroid.prism.Setter;
import com.stylingandroid.prism.filter.Filter;
import com.stylingandroid.prism.filter.IdentityFilter;

import java.util.ArrayList;
import java.util.List;

public final class ColourSetterFactory {
    public static final Filter IDENTITY_COLOUR_FILTER = new IdentityFilter();
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

    public static Setter getBackgroundSetter(View view, Filter filter) {
        if (FabSetter.isFab(view)) {
            return new FabSetter(view, getSafeColourFilter(filter));
        }
        return new ViewBackgroundSetter(view, getSafeColourFilter(filter));
    }

    public static Setter getBackgroundSetter(Window window, Filter filter) {
        return new StatusBarSetter(window, getSafeColourFilter(filter));
    }

    public static Setter getTextSetter(TextView view, Filter filter) {
        return new TextSetter(view, getSafeColourFilter(filter));
    }

    public static Setter getColourSetter(View view, Filter filter) {
        Setter setter = null;
        for (SetterFactory factory : FACTORIES) {
            setter = factory.getColourSetter(view, getSafeColourFilter(filter));
        }
        if (setter == null) {
            if (FabSetter.isFab(view)) {
                setter = new FabSetter(view, getSafeColourFilter(filter));
            } else {
                setter = new ViewBackgroundSetter(view, getSafeColourFilter(filter));
            }
        }
        return setter;
    }

    private static Filter getSafeColourFilter(Filter filter) {
        if (filter != null) {
            return filter;
        }
        return IDENTITY_COLOUR_FILTER;
    }
}
