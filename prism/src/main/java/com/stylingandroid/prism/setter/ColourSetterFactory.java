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
            return new FabSetter(view, getNullSafeFilter(filter));
        }
        return new ViewBackgroundSetter(view, getNullSafeFilter(filter));
    }

    public static Setter getBackgroundSetter(Window window, Filter filter) {
        return new SystemChromeSetter(window, getNullSafeFilter(filter));
    }

    public static Setter getTextSetter(TextView view, Filter filter) {
        return new TextSetter(view, getNullSafeFilter(filter));
    }

    public static Setter getColourSetter(View view, Filter filter) {
        Setter setter = null;
        for (SetterFactory factory : FACTORIES) {
            setter = factory.getColourSetter(view, getNullSafeFilter(filter));
        }
        if (setter == null) {
            if (FabSetter.isFab(view)) {
                setter = new FabSetter(view, getNullSafeFilter(filter));
            } else {
                setter = new ViewBackgroundSetter(view, getNullSafeFilter(filter));
            }
        }
        return setter;
    }

    public static Setter getColorSetter(View view, Filter filter) {
        return getColourSetter(view, filter);
    }

    private static Filter getNullSafeFilter(Filter filter) {
        if (filter != null) {
            return filter;
        }
        return IDENTITY_COLOUR_FILTER;
    }
}
