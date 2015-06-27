package android.support.v7.graphics;

import java.util.List;

public class DummyGenerator extends Palette.Generator {
    private static final int VIBRANT = 0;
    private static final int LIGHT_VIBRANT = 1;
    private static final int DARK_VIBRANT = 2;
    private static final int MUTED = 3;
    private static final int LIGHT_MUTED = 4;
    private static final int DARK_MUTED = 5;

    private List<Palette.Swatch> swatches;

    @Override
    public void generate(List<Palette.Swatch> swatches) {
        this.swatches = swatches;
    }

    @Override
    public Palette.Swatch getVibrantSwatch() {
        return getSwatch(VIBRANT);
    }

    @Override
    public Palette.Swatch getLightVibrantSwatch() {
        return getSwatch(LIGHT_VIBRANT);
    }

    @Override
    public Palette.Swatch getDarkVibrantSwatch() {
        return getSwatch(DARK_VIBRANT);
    }

    @Override
    public Palette.Swatch getMutedSwatch() {
        return getSwatch(MUTED);
    }

    @Override
    public Palette.Swatch getLightMutedSwatch() {
        return getSwatch(LIGHT_MUTED);
    }

    @Override
    public Palette.Swatch getDarkMutedSwatch() {
        return getSwatch(DARK_MUTED);
    }

    private Palette.Swatch getSwatch(int index) {
        if(index < swatches.size()) {
            return swatches.get(index);
        }
        return null;
    }
}
