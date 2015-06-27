package android.support.v7.graphics;

import java.util.List;

public final class PaletteFactory {
    private PaletteFactory() {
        //NO-OP
    }

    public static Palette createPalette(List<Palette.Swatch> swatches, Palette.Generator generator) {
        return new Palette.Builder(swatches).generator(generator).generate();
    }
}
