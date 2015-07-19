package com.stylingandroid.prism.palette;

import android.graphics.Color;
import android.support.v7.graphics.DummyGenerator;
import android.support.v7.graphics.Palette;
import android.support.v7.graphics.PaletteFactory;

import com.stylingandroid.prism.ColourUtils;
import com.stylingandroid.prism.filter.Filter;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Color.class)
public class PaletteTriggerTest {
    private static final int VIBRANT = ColourUtils.rgb(ColourUtils.HEX_255, ColourUtils.HEX_000, ColourUtils.HEX_000);
    private static final int LIGHT_VIBRANT = ColourUtils.rgb(ColourUtils.HEX_000, ColourUtils.HEX_255, ColourUtils.HEX_000);
    private static final int DARK_VIBRANT = ColourUtils.rgb(ColourUtils.HEX_000, ColourUtils.HEX_000, ColourUtils.HEX_255);
    private static final int MUTED = ColourUtils.rgb(ColourUtils.HEX_255, ColourUtils.HEX_255, ColourUtils.HEX_000);
    private static final int LIGHT_MUTED = ColourUtils.rgb(ColourUtils.HEX_255, ColourUtils.HEX_000, ColourUtils.HEX_255);
    private static final int DARK_MUTED = ColourUtils.rgb(ColourUtils.HEX_000, ColourUtils.HEX_255, ColourUtils.HEX_255);
    private PaletteTrigger paletteTrigger;

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
        paletteTrigger = new PaletteTrigger();
        paletteTrigger.setPalette(palette);
    }

    @Test
    public void givenADummyPaletteVibrantColourIsCorrect() {
        Filter filter = paletteTrigger.getVibrantFilter();
        int output = filter.filter(null);
        assertThat(output).isEqualTo(VIBRANT);
    }

    @Test
    public void givenADummyPaletteLightVibrantColourIsCorrect() {
        Filter filter = paletteTrigger.getLightVibrantFilter();
        int output = filter.filter(null);
        assertThat(output).isEqualTo(LIGHT_VIBRANT);
    }

    @Test
    public void givenADummyPaletteDarkVibrantColourIsCorrect() {
        Filter filter = paletteTrigger.getDarkVibrantFilter();
        int output = filter.filter(null);
        assertThat(output).isEqualTo(DARK_VIBRANT);
    }

    @Test
    public void givenADummyPaletteMutedColourIsCorrect() {
        Filter filter = paletteTrigger.getMutedFilter();
        int output = filter.filter(null);
        assertThat(output).isEqualTo(MUTED);
    }

    @Test
    public void givenADummyPaletteLightMutedColourIsCorrect() {
        Filter filter = paletteTrigger.getLightMutedFilter();
        int output = filter.filter(null);
        assertThat(output).isEqualTo(LIGHT_MUTED);
    }

    @Test
    public void givenADummyPaletteDarkMutedColourIsCorrect() {
        Filter filter = paletteTrigger.getDarkMutedFilter();
        int output = filter.filter(null);
        assertThat(output).isEqualTo(DARK_MUTED);
    }
}
