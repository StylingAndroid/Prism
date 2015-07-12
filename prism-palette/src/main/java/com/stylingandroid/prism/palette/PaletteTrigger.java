package com.stylingandroid.prism.palette;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.graphics.Palette;

import com.stylingandroid.prism.BaseColourChangeTrigger;
import com.stylingandroid.prism.ColourChangeTrigger;
import com.stylingandroid.prism.filter.ColourFilter;
import com.stylingandroid.prism.filter.CompoundColourFilter;
import com.stylingandroid.prism.filter.GenericColourFilter;

import java.lang.ref.WeakReference;

public class PaletteTrigger extends BaseColourChangeTrigger implements ColourChangeTrigger {
    private Palette palette;

    public PaletteTrigger() {
    }

    public void setBitmap(@NonNull Bitmap bitmap) {
        new Palette.Builder(bitmap).generate(listener);
    }

    public ColourFilter getVibrantFilter(GenericColourFilter<Palette.Swatch, Integer> nextFilter) {
        return new CompoundColourFilter<>(new VibrantFilter(this), nextFilter);
    }

    public ColourFilter getLightVibrantFilter(GenericColourFilter<Palette.Swatch, Integer> nextFilter) {
        return new CompoundColourFilter<>(new LightVibrantFilter(this), nextFilter);
    }

    public ColourFilter getDarkVibrantFilter(GenericColourFilter<Palette.Swatch, Integer> nextFilter) {
        return new CompoundColourFilter<>(new DarkVibrantFilter(this), nextFilter);
    }

    public ColourFilter getMutedFilter(GenericColourFilter<Palette.Swatch, Integer> nextFilter) {
        return new CompoundColourFilter<>(new MutedFilter(this), nextFilter);
    }

    public ColourFilter getLightMutedFilter(GenericColourFilter<Palette.Swatch, Integer> nextFilter) {
        return new CompoundColourFilter<>(new LightMutedFilter(this), nextFilter);
    }

    public ColourFilter getDarkMutedFilter(GenericColourFilter<Palette.Swatch, Integer> nextFilter) {
        return new CompoundColourFilter<>(new DarkMutedFilter(this), nextFilter);
    }

    public GenericColourFilter<Palette.Swatch, Integer> getColour() {
        return rgbColour;
    }

    public GenericColourFilter<Palette.Swatch, Integer> getTitleTextColour() {
        return titleTextColour;
    }

    public GenericColourFilter<Palette.Swatch, Integer> getBodyTextColour() {
        return bodyTextColour;
    }

    private Palette.PaletteAsyncListener listener = new Palette.PaletteAsyncListener() {
        @Override
        public void onGenerated(Palette newPalette) {
            setPalette(newPalette);
        }
    };

    private abstract static class BasePaletteFilter implements GenericColourFilter<Integer, Palette.Swatch> {
        private final WeakReference<PaletteTrigger> parentWeakReference;

        protected BasePaletteFilter(PaletteTrigger parent) {
            this.parentWeakReference = new WeakReference<>(parent);
        }

        @Override
        public Palette.Swatch filter(Integer noValue) {
            Palette palette = getPalette();
            if (palette != null) {
                return doFilter(palette);
            }
            return null;
        }

        protected abstract Palette.Swatch doFilter(Palette palette);

        protected Palette getPalette() {
            PaletteTrigger paletteTrigger = parentWeakReference.get();
            if (paletteTrigger != null) {
                return paletteTrigger.getPalette();
            }
            return null;
        }
    }

    private static class VibrantFilter extends BasePaletteFilter {
        VibrantFilter(PaletteTrigger parent) {
            super(parent);
        }

        @Override
        protected Palette.Swatch doFilter(Palette palette) {
            return palette.getVibrantSwatch();
        }
    }

    private static class LightVibrantFilter extends BasePaletteFilter {
        LightVibrantFilter(PaletteTrigger parent) {
            super(parent);
        }

        @Override
        protected Palette.Swatch doFilter(Palette palette) {
            return palette.getLightVibrantSwatch();
        }
    }

    private static class DarkVibrantFilter extends BasePaletteFilter {
        DarkVibrantFilter(PaletteTrigger parent) {
            super(parent);
        }

        @Override
        protected Palette.Swatch doFilter(Palette palette) {
            return palette.getDarkVibrantSwatch();
        }
    }

    private static class MutedFilter extends BasePaletteFilter {
        MutedFilter(PaletteTrigger parent) {
            super(parent);
        }

        @Override
        protected Palette.Swatch doFilter(Palette palette) {
            return palette.getMutedSwatch();
        }
    }

    private static class LightMutedFilter extends BasePaletteFilter {
        LightMutedFilter(PaletteTrigger parent) {
            super(parent);
        }

        @Override
        protected Palette.Swatch doFilter(Palette palette) {
            return palette.getLightMutedSwatch();
        }
    }

    private static class DarkMutedFilter extends BasePaletteFilter {
        DarkMutedFilter(PaletteTrigger parent) {
            super(parent);
        }

        @Override
        protected Palette.Swatch doFilter(android.support.v7.graphics.Palette palette) {
            return palette.getDarkMutedSwatch();
        }
    }

    private GenericColourFilter<Palette.Swatch, Integer> rgbColour = new GenericColourFilter<Palette.Swatch, Integer>() {
        @Override
        public Integer filter(Palette.Swatch colour) {
            if (colour == null) {
                return Color.TRANSPARENT;
            }
            return colour.getRgb();
        }
    };

    private GenericColourFilter<Palette.Swatch, Integer> titleTextColour = new GenericColourFilter<Palette.Swatch, Integer>() {
        @Override
        public Integer filter(Palette.Swatch colour) {
            if (colour == null) {
                return Color.TRANSPARENT;
            }
            return colour.getTitleTextColor();
        }
    };

    private GenericColourFilter<Palette.Swatch, Integer> bodyTextColour = new GenericColourFilter<Palette.Swatch, Integer>() {
        @Override
        public Integer filter(Palette.Swatch colour) {
            if (colour == null) {
                return Color.TRANSPARENT;
            }
            return colour.getBodyTextColor();
        }
    };

    Palette getPalette() {
        return palette;
    }

    void setPalette(Palette palette) {
        this.palette = palette;
        setColour(palette.getVibrantColor(0), false);
    }

}
