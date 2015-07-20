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
public class SystemChromeFilterTest {
    private static final int HEX_102 = 0x66;

    @Before
    public void setup() {
        MockUtils.mockColor();
    }

    @Test
    public void givenASpecifiedColourThenCorrectColourIsProduced() {
        int inputColour = ColourUtils.rgb(ColourUtils.HEX_128, ColourUtils.HEX_128, ColourUtils.HEX_128);
        int expected = ColourUtils.rgb(HEX_102, HEX_102, HEX_102);
        SystemChromeFilter systemChromeFilter = new SystemChromeFilter();
        int outputColour = systemChromeFilter.filter(inputColour);
        assertThat(outputColour).isEqualTo(expected);
    }
}
