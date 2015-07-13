package com.stylingandroid.prism.filter;

import android.support.v7.graphics.Palette;

public class PaletteFilter extends CompoundFilter<Palette.Swatch> {

    public PaletteFilter(GenericFilter<Integer, Palette.Swatch> firstFilter, GenericFilter<Palette.Swatch, Integer> secondFilter) {
        super(firstFilter, secondFilter);
    }
}
