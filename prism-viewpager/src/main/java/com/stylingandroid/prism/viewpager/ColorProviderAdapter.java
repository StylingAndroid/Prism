package com.stylingandroid.prism.viewpager;

import android.support.annotation.NonNull;

class ColorProviderAdapter implements ColourProvider {
    private final ColorProvider colorProvider;

    public ColorProviderAdapter(@NonNull ColorProvider colorProvider) {
        this.colorProvider = colorProvider;
    }

    @Override
    public int getColour(int position) {
        return colorProvider.getColor(position);
    }

    @Override
    public int getCount() {
        return colorProvider.getCount();
    }
}
