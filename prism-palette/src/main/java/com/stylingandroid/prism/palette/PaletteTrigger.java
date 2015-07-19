package com.stylingandroid.prism.palette;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.graphics.Palette;

import com.stylingandroid.prism.BaseTrigger;
import com.stylingandroid.prism.Trigger;
import com.stylingandroid.prism.filter.Filter;
import com.stylingandroid.prism.filter.CompoundFilter;
import com.stylingandroid.prism.filter.GenericFilter;

import java.lang.ref.WeakReference;

public class PaletteTrigger extends BaseTrigger implements Trigger {
    private Palette palette;

    public PaletteTrigger() {
    }

    public void setBitmap(@NonNull Bitmap bitmap) {
        new Palette.Builder(bitmap).generate(listener);
    }

    public Filter getVibrantFilter() {
        return getVibrantFilter(getColour());
    }

    public Filter getVibrantFilter(GenericFilter<Palette.Swatch, Integer> nextFilter) {
        return new CompoundFilter<>(new VibrantFilter(this), nextFilter);
    }

    public Filter getLightVibrantFilter() {
        return getLightVibrantFilter(getColour());
    }

    public Filter getLightVibrantFilter(GenericFilter<Palette.Swatch, Integer> nextFilter) {
        return new CompoundFilter<>(new LightVibrantFilter(this), nextFilter);
    }

    public Filter getDarkVibrantFilter() {
        return getDarkVibrantFilter(getColour());
    }

    public Filter getDarkVibrantFilter(GenericFilter<Palette.Swatch, Integer> nextFilter) {
        return new CompoundFilter<>(new DarkVibrantFilter(this), nextFilter);
    }

    public Filter getMutedFilter() {
        return getMutedFilter(getColour());
    }

    public Filter getMutedFilter(GenericFilter<Palette.Swatch, Integer> nextFilter) {
        return new CompoundFilter<>(new MutedFilter(this), nextFilter);
    }

    public Filter getLightMutedFilter() {
        return getLightMutedFilter(getColour());
    }

    public Filter getLightMutedFilter(GenericFilter<Palette.Swatch, Integer> nextFilter) {
        return new CompoundFilter<>(new LightMutedFilter(this), nextFilter);
    }

    public Filter getDarkMutedFilter() {
        return getDarkMutedFilter(getColour());
    }

    public Filter getDarkMutedFilter(GenericFilter<Palette.Swatch, Integer> nextFilter) {
        return new CompoundFilter<>(new DarkMutedFilter(this), nextFilter);
    }

    public GenericFilter<Palette.Swatch, Integer> getColour() {
        return rgbColour;
    }

    public GenericFilter<Palette.Swatch, Integer> getColor() {
        return rgbColour;
    }

    public GenericFilter<Palette.Swatch, Integer> getTitleTextColour() {
        return titleTextColour;
    }

    public GenericFilter<Palette.Swatch, Integer> getTitleTextColor() {
        return getTitleTextColour();
    }

    public GenericFilter<Palette.Swatch, Integer> getBodyTextColour() {
        return bodyTextColour;
    }

    public GenericFilter<Palette.Swatch, Integer> getBodyTextColor() {
        return getBodyTextColour();
    }

    private Palette.PaletteAsyncListener listener = new Palette.PaletteAsyncListener() {
        @Override
        public void onGenerated(Palette newPalette) {
            setPalette(newPalette);
        }
    };

    private abstract static class BasePaletteFilter implements GenericFilter<Integer, Palette.Swatch> {
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

    private GenericFilter<Palette.Swatch, Integer> rgbColour = new GenericFilter<Palette.Swatch, Integer>() {
        @Override
        public Integer filter(Palette.Swatch colour) {
            if (colour == null) {
                return Color.TRANSPARENT;
            }
            return colour.getRgb();
        }
    };

    private GenericFilter<Palette.Swatch, Integer> titleTextColour = new GenericFilter<Palette.Swatch, Integer>() {
        @Override
        public Integer filter(Palette.Swatch colour) {
            if (colour == null) {
                return Color.TRANSPARENT;
            }
            return colour.getTitleTextColor();
        }
    };

    private GenericFilter<Palette.Swatch, Integer> bodyTextColour = new GenericFilter<Palette.Swatch, Integer>() {
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
        setColour(palette.getVibrantColor(0));
    }

}
