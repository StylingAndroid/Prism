package com.stylingandroid.prism;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.stylingandroid.prism.filter.ColourFilter;
import com.stylingandroid.prism.filter.StatusBarFilter;
import com.stylingandroid.prism.setter.ColourSetterFactory;

import java.util.ArrayList;
import java.util.List;

public final class Prism {
    private static final ColourFilter STATUS_BAR_COLOUR_FILTER = new StatusBarFilter();
    private final List<ColourSetter> colourSetters;
    private final ColourChangeTrigger colourChangeTrigger;

    public static Prism newInstance(@NonNull ColourChangeTrigger colourChangeTrigger) {
        List<ColourSetter> colourSetters = new ArrayList<>();
        return new Prism(colourChangeTrigger, colourSetters);
    }

    public static Prism newInstance() {
        List<ColourSetter> colourSetters = new ArrayList<>();
        return new Prism(null, colourSetters);
    }

    private Prism(@Nullable ColourChangeTrigger colourChangeTrigger, @NonNull List<ColourSetter> colourSetters) {
        this.colourChangeTrigger = colourChangeTrigger;
        this.colourSetters = colourSetters;
    }

    public Prism background(@NonNull View view) {
        return background(view, ColourSetterFactory.IDENTITY_COLOUR_FILTER);
    }

    public Prism background(@NonNull View view, @NonNull ColourFilter filter) {
        return add(ColourSetterFactory.getBackgroundSetter(view, filter));
    }

    public Prism background(@NonNull Window window) {
        return background(window, STATUS_BAR_COLOUR_FILTER);
    }

    public Prism background(@NonNull Window window, @NonNull ColourFilter filter) {
        return add(ColourSetterFactory.getBackgroundSetter(window, filter));
    }

    public Prism add(@NonNull ColourSetter colourSetter) {
        colourSetters.add(colourSetter);
        return this;
    }

    public Prism text(@NonNull TextView view) {
        return text(view, ColourSetterFactory.IDENTITY_COLOUR_FILTER);
    }

    public Prism text(@NonNull TextView view, @NonNull ColourFilter filter) {
        return add(ColourSetterFactory.getTextSetter(view, filter));
    }

    public Prism colour(@NonNull View view) {
        return colour(view, ColourSetterFactory.IDENTITY_COLOUR_FILTER);
    }

    public Prism colour(@NonNull View view, @NonNull ColourFilter filter) {
        return add(ColourSetterFactory.getColourSetter(view, filter));
    }

    public ColourSetter build() {
        Chameleon chameleon = new Chameleon(colourSetters);
        if (colourChangeTrigger != null) {
            colourChangeTrigger.addColourSetter(chameleon);
        }
        return chameleon;
    }
}
