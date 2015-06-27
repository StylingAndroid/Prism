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
public class StatusBarFilterTest {
    private static final int ALPHA_MASK = 0x00FFFFFF;

    @Before
    public void setup() {
        MockUtils.mockColor();
    }

    @Test
    public void givenASpecifiedColourOnlyTheAlphaChanges() {
        int inputColour = ColourUtils.rgb(ColourUtils.HEX_128, ColourUtils.HEX_128, ColourUtils.HEX_128);
        StatusBarFilter statusBarFilter = new StatusBarFilter();
        int expectedColour = inputColour & ALPHA_MASK;
        int outputColour = statusBarFilter.filter(inputColour);
        assertThat(outputColour & ALPHA_MASK).isEqualTo(expectedColour);
    }
}
