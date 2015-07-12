package com.stylingandroid.prism.filter;

import android.support.v7.graphics.Palette;

public class PaletteColourFilter extends CompoundColourFilter<Palette.Swatch> {

    public PaletteColourFilter(GenericColourFilter<Integer, Palette.Swatch> firstFilter, GenericColourFilter<Palette.Swatch, Integer> secondFilter) {
        super(firstFilter, secondFilter);
    }
}
