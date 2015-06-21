package com.stylingandroid.palette;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.graphics.Palette;

import com.stylingandroid.prism.BaseColourChangeTrigger;
import com.stylingandroid.prism.ColourChangeTrigger;
import com.stylingandroid.prism.ColourSetter;
import com.stylingandroid.prism.filter.ColourFilter;

public class PaletteProvider extends BaseColourChangeTrigger implements ColourChangeTrigger {
    private final ColourSetter colourSetter;
    private Palette palette;

    public PaletteProvider(@NonNull ColourSetter colourSetter) {
        this.colourSetter = colourSetter;
    }

    public void setBitmap(@NonNull Bitmap bitmap) {
        new Palette.Builder(bitmap).generate(listener);
    }

    public ColourFilter getVibrantFilter() {
        return vibrantFilter;
    }

    public ColourFilter getLightVibrantFilter() {
        return lightVibrantFilter;
    }

    public ColourFilter getDarkVibrantFilter() {
        return darkVibrantFilter;
    }

    public ColourFilter getMutedFilter() {
        return mutedFilter;
    }

    public ColourFilter getLightMutedFilter() {
        return lightMutedFilter;
    }

    public ColourFilter getDarkMutedFilter() {
        return darkMutedFilter;
    }

    private Palette.PaletteAsyncListener listener = new Palette.PaletteAsyncListener() {
        @Override
        public void onGenerated(Palette newPalette) {
            setPalette(newPalette);
        }
    };

    private ColourFilter vibrantFilter = new ColourFilter() {
        @Override
        public int filter(int colour) {
            return palette.getVibrantColor(colour);
        }
    };

    private ColourFilter lightVibrantFilter = new ColourFilter() {
        @Override
        public int filter(int colour) {
            return palette.getLightVibrantColor(colour);
        }
    };

    private ColourFilter darkVibrantFilter = new ColourFilter() {
        @Override
        public int filter(int colour) {
            return palette.getDarkVibrantColor(colour);
        }
    };

    private ColourFilter mutedFilter = new ColourFilter() {
        @Override
        public int filter(int colour) {
            return palette.getMutedColor(colour);
        }
    };

    private ColourFilter lightMutedFilter = new ColourFilter() {
        @Override
        public int filter(int colour) {
            return palette.getLightMutedColor(colour);
        }
    };

    private ColourFilter darkMutedFilter = new ColourFilter() {
        @Override
        public int filter(int colour) {
            return palette.getDarkMutedColor(colour);
        }
    };

    public void setPalette(Palette palette) {
        this.palette = palette;
        colourSetter.setColour(palette.getVibrantColor(0));
    }
}
