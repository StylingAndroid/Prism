package com.stylingandroid.prism.filter;

import android.graphics.Color;

import com.stylingandroid.prism.ColourUtils;
import com.stylingandroid.prism.MockUtils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Color.class)
public class ShadeFilterTest {
    private static final float ONE_HUNDRED_PERCENT = 1f;
    private static final float ZERO_PERCENT = 0f;
    private static final int HEX_102 = 0x66;

    @Before
    public void setup() {
        MockUtils.mockColor();
    }

    @Test
    public void givenASpecificColourForAZeroPercentTintThenTheCorrectOutputColourIsProduced() throws Exception {
        int inputColour = ColourUtils.rgb(ColourUtils.HEX_128, ColourUtils.HEX_128, ColourUtils.HEX_128);
        ShadeFilter shadeFilter = new ShadeFilter(ZERO_PERCENT);
        int outputColour = shadeFilter.filter(inputColour);
        assertThat(outputColour).isEqualTo(inputColour);
    }

    @Test
    public void givenASpecificColourForAOneHundredPercentTintThenTheCorrectOutputColourIsProduced() throws Exception {
        int inputColour = ColourUtils.rgb(ColourUtils.HEX_128, ColourUtils.HEX_128, ColourUtils.HEX_128);
        int expectedColour = ColourUtils.rgb(ColourUtils.HEX_000, ColourUtils.HEX_000, ColourUtils.HEX_000);
        ShadeFilter shadeFilter = new ShadeFilter(ONE_HUNDRED_PERCENT);
        int outputColour = shadeFilter.filter(inputColour);
        assertThat(outputColour).isEqualTo(expectedColour);
    }

    @Test
    public void givenASpecificColourForADefaultTintThenTheCorrectOutputColourIsProduced() throws Exception {
        int inputColour = ColourUtils.rgb(ColourUtils.HEX_128, ColourUtils.HEX_128, ColourUtils.HEX_128);
        int expected = ColourUtils.rgb(HEX_102, HEX_102, HEX_102);
        ShadeFilter shadeFilter = new ShadeFilter();
        int outputColour = shadeFilter.filter(inputColour);
        assertThat(outputColour).isEqualTo(expected);
    }
}
