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
public class TintFilterTest {
    private static final float ONE_HUNDRED_PERCENT = 1f;
    private static final float ZERO_PERCENT = 0f;
    private static final int HEX_153 = 0x99;

    @Before
    public void setup() {
        MockUtils.mockColor();
    }

    @Test
    public void givenASpecificColourForAOneHundredPercentTintThenTheCorrectOutputColourIsProduced() throws Exception {
        int inputColour = ColourUtils.rgb(ColourUtils.HEX_128, ColourUtils.HEX_128, ColourUtils.HEX_128);
        int expectedColour = ColourUtils.rgb(ColourUtils.HEX_255, ColourUtils.HEX_255, ColourUtils.HEX_255);
        TintFilter tintFilter = new TintFilter(ONE_HUNDRED_PERCENT);
        int outputColour = tintFilter.filter(inputColour);
        assertThat(outputColour).isEqualTo(expectedColour);
    }

    @Test
    public void givenASpecificColourForAZeroPercentTintThenTheCorrectOutputColourIsProduced() throws Exception {
        int inputColour = ColourUtils.rgb(ColourUtils.HEX_128, ColourUtils.HEX_128, ColourUtils.HEX_128);
        TintFilter tintFilter = new TintFilter(ZERO_PERCENT);
        int outputColour = tintFilter.filter(inputColour);
        assertThat(outputColour).isEqualTo(inputColour);
    }

    @Test
    public void givenASpecificColourForADefaultTintThenTheCorrectOutputColourIsProduced() throws Exception {
        int inputColour = ColourUtils.rgb(ColourUtils.HEX_128, ColourUtils.HEX_128, ColourUtils.HEX_128);
        int expected = ColourUtils.rgb(HEX_153, HEX_153, HEX_153);
        TintFilter tintFilter = new TintFilter();
        int outputColour = tintFilter.filter(inputColour);
        assertThat(outputColour).isEqualTo(expected);
    }
}
