package com.stylingandroid.prism.palette;

import android.graphics.Color;
import android.support.v7.graphics.DummyGenerator;
import android.support.v7.graphics.Palette;
import android.support.v7.graphics.PaletteFactory;

import com.stylingandroid.prism.ColourSetter;
import com.stylingandroid.prism.ColourUtils;
import com.stylingandroid.prism.filter.ChainableColourFilter;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Color.class)
public class PaletteProviderTest {
    private static final int VIBRANT = ColourUtils.rgb(ColourUtils.HEX_255, ColourUtils.HEX_000, ColourUtils.HEX_000);
    private static final int LIGHT_VIBRANT = ColourUtils.rgb(ColourUtils.HEX_000, ColourUtils.HEX_255, ColourUtils.HEX_000);
    private static final int DARK_VIBRANT = ColourUtils.rgb(ColourUtils.HEX_000, ColourUtils.HEX_000, ColourUtils.HEX_255);
    private static final int MUTED = ColourUtils.rgb(ColourUtils.HEX_255, ColourUtils.HEX_255, ColourUtils.HEX_000);
    private static final int LIGHT_MUTED = ColourUtils.rgb(ColourUtils.HEX_255, ColourUtils.HEX_000, ColourUtils.HEX_255);
    private static final int DARK_MUTED = ColourUtils.rgb(ColourUtils.HEX_000, ColourUtils.HEX_255, ColourUtils.HEX_255);
    private PaletteProvider paletteProvider;

    @Mock
    private ColourSetter colourSetter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        MockUtils.mockColor();
        List<Palette.Swatch> swatches = new ArrayList<>();
        swatches.add(new Palette.Swatch(VIBRANT, 100));
        swatches.add(new Palette.Swatch(LIGHT_VIBRANT, 100));
        swatches.add(new Palette.Swatch(DARK_VIBRANT, 100));
        swatches.add(new Palette.Swatch(MUTED, 100));
        swatches.add(new Palette.Swatch(LIGHT_MUTED, 100));
        swatches.add(new Palette.Swatch(DARK_MUTED, 100));
        Palette palette = PaletteFactory.createPalette(swatches, new DummyGenerator());
        paletteProvider = new PaletteProvider(colourSetter);
        paletteProvider.setPalette(palette);
    }

    @Test
    public void givenADummyPaletteVibrantColourIsCorrect() {
        ChainableColourFilter<Void, Palette.Swatch> filter = paletteProvider.getVibrantFilter();
        filter.setNextFilter(paletteProvider.getColour());
        int output = filter.filter(null);
        assertThat(output).isEqualTo(VIBRANT);
    }

    @Test
    public void givenADummyPaletteLightVibrantColourIsCorrect() {
        ChainableColourFilter<Void, Palette.Swatch> filter = paletteProvider.getLightVibrantFilter();
        filter.setNextFilter(paletteProvider.getColour());
        int output = filter.filter(null);
        assertThat(output).isEqualTo(LIGHT_VIBRANT);
    }

    @Test
    public void givenADummyPaletteDarkVibrantColourIsCorrect() {
        ChainableColourFilter<Void, Palette.Swatch> filter = paletteProvider.getDarkVibrantFilter();
        filter.setNextFilter(paletteProvider.getColour());
        int output = filter.filter(null);
        assertThat(output).isEqualTo(DARK_VIBRANT);
    }

    @Test
    public void givenADummyPaletteMutedColourIsCorrect() {
        ChainableColourFilter<Void, Palette.Swatch> filter = paletteProvider.getMutedFilter();
        filter.setNextFilter(paletteProvider.getColour());
        int output = filter.filter(null);
        assertThat(output).isEqualTo(MUTED);
    }

    @Test
    public void givenADummyPaletteLightMutedColourIsCorrect() {
        ChainableColourFilter<Void, Palette.Swatch> filter = paletteProvider.getLightMutedFilter();
        filter.setNextFilter(paletteProvider.getColour());
        int output = filter.filter(null);
        assertThat(output).isEqualTo(LIGHT_MUTED);
    }

    @Test
    public void givenADummyPaletteDarkMutedColourIsCorrect() {
        ChainableColourFilter<Void, Palette.Swatch> filter = paletteProvider.getDarkMutedFilter();
        filter.setNextFilter(paletteProvider.getColour());
        int output = filter.filter(null);
        assertThat(output).isEqualTo(DARK_MUTED);
    }
}
