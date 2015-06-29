package com.stylingandroid.prism;

import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.stylingandroid.prism.filter.ColourFilter;
import com.stylingandroid.prism.filter.IdentityFilter;
import com.stylingandroid.prism.filter.StatusBarFilter;
import com.stylingandroid.prism.setter.ColourSetterFactory;

import java.util.ArrayList;
import java.util.List;

public final class Prism {
    private static final ColourFilter<Integer, Integer> IDENTITY_COLOUR_FILTER = new IdentityFilter();
    private static final ColourFilter<Integer, Integer> STATUS_BAR_COLOUR_FILTER = new StatusBarFilter();
    private final List<ColourSetter> colourSetters;
    private final ColourChangeTrigger colourChangeTrigger;

    public static Prism newInstance(ColourChangeTrigger colourChangeTrigger) {
        List<ColourSetter> colourSetters = new ArrayList<>();
        return new Prism(colourChangeTrigger, colourSetters);
    }

    private Prism(ColourChangeTrigger colourChangeTrigger, List<ColourSetter> colourSetters) {
        this.colourChangeTrigger = colourChangeTrigger;
        this.colourSetters = colourSetters;
    }

    public Prism background(View view) {
        return background(view, IDENTITY_COLOUR_FILTER);
    }

    public Prism background(View view, ColourFilter<Integer, Integer> filter) {
        return add(ColourSetterFactory.getBackgroundSetter(view, filter));
    }

    public Prism background(Window window) {
        return background(window, STATUS_BAR_COLOUR_FILTER);
    }

    public Prism background(Window window, ColourFilter<Integer, Integer> filter) {
        return add(ColourSetterFactory.getBackgroundSetter(window, filter));
    }

    public Prism add(ColourSetter colourSetter) {
        colourSetters.add(colourSetter);
        return this;
    }

    public Prism text(TextView view) {
        return text(view, IDENTITY_COLOUR_FILTER);
    }

    public Prism text(TextView view, ColourFilter<Integer, Integer> filter) {
        return add(ColourSetterFactory.getTextSetter(view, filter));
    }

    public Prism colour(View view) {
        return colour(view, IDENTITY_COLOUR_FILTER);
    }

    public Prism colour(View view, ColourFilter<Integer, Integer> filter) {
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
