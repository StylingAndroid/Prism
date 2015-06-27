package com.stylingandroid.prism.palette;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.graphics.Palette;

import com.stylingandroid.prism.BaseColourChangeTrigger;
import com.stylingandroid.prism.ColourChangeTrigger;
import com.stylingandroid.prism.ColourSetter;
import com.stylingandroid.prism.filter.ChainableColourFilter;

public class PaletteProvider extends BaseColourChangeTrigger implements ColourChangeTrigger {
    private final ColourSetter colourSetter;
    private Palette palette;

    public PaletteProvider(@NonNull ColourSetter colourSetter) {
        this.colourSetter = colourSetter;
    }

    public void setBitmap(@NonNull Bitmap bitmap) {
        new Palette.Builder(bitmap).generate(listener);
    }

    public ChainableColourFilter<Void, Palette.Swatch> getVibrantFilter() {
        return vibrantFilter;
    }

    public ChainableColourFilter<Void, Palette.Swatch> getLightVibrantFilter() {
        return lightVibrantFilter;
    }

    public ChainableColourFilter<Void, Palette.Swatch> getDarkVibrantFilter() {
        return darkVibrantFilter;
    }

    public ChainableColourFilter<Void, Palette.Swatch> getMutedFilter() {
        return mutedFilter;
    }

    public ChainableColourFilter<Void, Palette.Swatch> getLightMutedFilter() {
        return lightMutedFilter;
    }

    public ChainableColourFilter<Void, Palette.Swatch> getDarkMutedFilter() {
        return darkMutedFilter;
    }

    public ChainableColourFilter<Palette.Swatch, Integer> getColour() {
        return rgbColour;
    }

    public ChainableColourFilter<Palette.Swatch, Integer> getTitleTextColour() {
        return titleTextColour;
    }

    public ChainableColourFilter<Palette.Swatch, Integer> getBodyTextColour() {
        return bodyTextColour;
    }

    private Palette.PaletteAsyncListener listener = new Palette.PaletteAsyncListener() {
        @Override
        public void onGenerated(Palette newPalette) {
            setPalette(newPalette);
        }
    };

    private ChainableColourFilter<Void, Palette.Swatch> vibrantFilter = new ChainableColourFilter<Void, Palette.Swatch>() {
        @Override
        public Palette.Swatch onFilter(Void noValue) {
            return palette.getVibrantSwatch();
        }
    };

    private ChainableColourFilter<Void, Palette.Swatch> lightVibrantFilter = new ChainableColourFilter<Void, Palette.Swatch>() {
        @Override
        public Palette.Swatch onFilter(Void noValue) {
            return palette.getLightVibrantSwatch();
        }
    };

    private ChainableColourFilter<Void, Palette.Swatch> darkVibrantFilter = new ChainableColourFilter<Void, Palette.Swatch>() {
        @Override
        public Palette.Swatch onFilter(Void noValue) {
            return palette.getDarkVibrantSwatch();
        }
    };

    private ChainableColourFilter<Void, Palette.Swatch> mutedFilter = new ChainableColourFilter<Void, Palette.Swatch>() {
        @Override
        public Palette.Swatch onFilter(Void noValue) {
            return palette.getMutedSwatch();
        }
    };

    private ChainableColourFilter<Void, Palette.Swatch> lightMutedFilter = new ChainableColourFilter<Void, Palette.Swatch>() {
        @Override
        public Palette.Swatch onFilter(Void noValue) {
            return palette.getLightMutedSwatch();
        }
    };

    private ChainableColourFilter<Void, Palette.Swatch> darkMutedFilter = new ChainableColourFilter<Void, Palette.Swatch>() {
        @Override
        public Palette.Swatch onFilter(Void noValue) {
            return palette.getDarkMutedSwatch();
        }
    };

    private ChainableColourFilter<Palette.Swatch, Integer> rgbColour = new ChainableColourFilter<Palette.Swatch, Integer>() {
        @Override
        protected Integer onFilter(Palette.Swatch colour) {
            return colour.getRgb();
        }
    };

    private ChainableColourFilter<Palette.Swatch, Integer> titleTextColour = new ChainableColourFilter<Palette.Swatch, Integer>() {
        @Override
        protected Integer onFilter(Palette.Swatch colour) {
            return colour.getTitleTextColor();
        }
    };

    private ChainableColourFilter<Palette.Swatch, Integer> bodyTextColour = new ChainableColourFilter<Palette.Swatch, Integer>() {
        @Override
        protected Integer onFilter(Palette.Swatch colour) {
            return colour.getBodyTextColor();
        }
    };

    public void setPalette(Palette palette) {
        this.palette = palette;
        colourSetter.setColour(palette.getVibrantColor(0));
    }
}
