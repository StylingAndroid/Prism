package com.stylingandroid.prism.setter;

import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.stylingandroid.prism.ColourSetter;
import com.stylingandroid.prism.filter.ColourFilter;

import java.util.ArrayList;
import java.util.List;

public final class ColourSetterFactory {
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
        return new ViewBackgroundColourSetter(view, filter);
    }

    public static ColourSetter getBackgroundSetter(Window window, ColourFilter filter) {
        return new StatusBarColourSetter(window, filter);
    }

    public static ColourSetter getTextSetter(TextView view, ColourFilter filter) {
        return new TextColourSetter(view, filter);
    }

    public static ColourSetter getColourSetter(View view, ColourFilter filter) {
        ColourSetter setter = null;
        for (SetterFactory factory : FACTORIES) {
            setter = factory.getColourSetter(view, filter);
        }
        if (setter == null) {
            if (FabColourSetter.isFab(view)) {
                setter = new FabColourSetter(view, filter);
            } else {
                setter = new ViewBackgroundColourSetter(view, filter);
            }
        }
        return setter;
    }
}
