package com.stylingandroid.prism.setter;

import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.stylingandroid.prism.ColourSetter;
import com.stylingandroid.prism.filter.ColourFilter;

import java.util.ArrayList;
import java.util.List;

public class ColourSetterFactory {
    private static final List<SetterFactory> FACTORIES = new ArrayList<>();

    static void registerFactory(SetterFactory factory) {
        FACTORIES.add(factory);
    }

    static void unregisterFactory(SetterFactory factory) {
        FACTORIES.remove(factory);
    }

    public static ColourSetter getBackgroundSetter(View view, ColourFilter filter) {
        if (FabColourSetter.isFab(view)) {
            return new FabColourSetter(view, filter);
        }
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
            setter = factory.getBackgroundSetter(view, filter);
        }
        return setter;
    }
}
