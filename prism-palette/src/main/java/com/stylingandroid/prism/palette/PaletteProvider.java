package com.stylingandroid.prism.palette;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.graphics.Palette;

import com.stylingandroid.prism.BaseColourChangeTrigger;
import com.stylingandroid.prism.ColourChangeTrigger;
import com.stylingandroid.prism.ColourSetter;
import com.stylingandroid.prism.filter.ChainableColourFilter;

import java.lang.ref.WeakReference;

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
        return new VibrantFilter(this);
    }

    public ChainableColourFilter<Void, Palette.Swatch> getLightVibrantFilter() {
        return new LightVibrantFilter(this);
    }

    public ChainableColourFilter<Void, Palette.Swatch> getDarkVibrantFilter() {
        return new DarkVibrantFilter(this);
    }

    public ChainableColourFilter<Void, Palette.Swatch> getMutedFilter() {
        return new MutedFilter(this);
    }

    public ChainableColourFilter<Void, Palette.Swatch> getLightMutedFilter() {
        return new LightMutedFilter(this);
    }

    public ChainableColourFilter<Void, Palette.Swatch> getDarkMutedFilter() {
        return new DarkMutedFilter(this);
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

    private abstract static class BasePaletteFilter extends ChainableColourFilter<Void, Palette.Swatch> {
        private final WeakReference<PaletteProvider> parentWeakReference;

        protected BasePaletteFilter(PaletteProvider parent) {
            this.parentWeakReference = new WeakReference<>(parent);
            setNextFilter(parent.getColour());
        }

        @Override
        protected Palette.Swatch onFilter(Void noValue) {
            Palette palette = getPalette();
            if (palette != null) {
                return doFilter(palette);
            }
            return null;
        }

        protected abstract Palette.Swatch doFilter(Palette palette);

        protected Palette getPalette() {
            PaletteProvider paletteProvider = parentWeakReference.get();
            if (paletteProvider != null) {
                return paletteProvider.getPalette();
            }
            return null;
        }
    }

    private static class VibrantFilter extends BasePaletteFilter {
        VibrantFilter(PaletteProvider parent) {
            super(parent);
        }

        @Override
        protected Palette.Swatch doFilter(Palette palette) {
            return palette.getVibrantSwatch();
        }
    }

    private static class LightVibrantFilter extends BasePaletteFilter {
        LightVibrantFilter(PaletteProvider parent) {
            super(parent);
        }

        @Override
        protected Palette.Swatch doFilter(Palette palette) {
            return palette.getLightVibrantSwatch();
        }
    }

    private static class DarkVibrantFilter extends BasePaletteFilter {
        DarkVibrantFilter(PaletteProvider parent) {
            super(parent);
        }

        @Override
        protected Palette.Swatch doFilter(Palette palette) {
            return palette.getDarkVibrantSwatch();
        }
    }

    private static class MutedFilter extends BasePaletteFilter {
        MutedFilter(PaletteProvider parent) {
            super(parent);
        }

        @Override
        protected Palette.Swatch doFilter(Palette palette) {
            return palette.getMutedSwatch();
        }
    }

    private static class LightMutedFilter extends BasePaletteFilter {
        LightMutedFilter(PaletteProvider parent) {
            super(parent);
        }

        @Override
        protected Palette.Swatch doFilter(Palette palette) {
            return palette.getLightMutedSwatch();
        }
    }

    private static class DarkMutedFilter extends BasePaletteFilter {
        DarkMutedFilter(PaletteProvider parent) {
            super(parent);
        }

        @Override
        protected Palette.Swatch doFilter(Palette palette) {
            return palette.getDarkMutedSwatch();
        }
    }

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

    public Palette getPalette() {
        return palette;
    }

    public void setPalette(Palette palette) {
        this.palette = palette;
        colourSetter.setColour(palette.getVibrantColor(0));
    }
}
