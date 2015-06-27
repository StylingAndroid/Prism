package com.stylingandroid.prism.filter;

import com.stylingandroid.prism.ColourUtils;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(Parameterized.class)
public class IdentityFilterTest {
    private final int colour;
    private IdentityFilter identityFilter;

    public IdentityFilterTest(int colour) {
        this.colour = colour;
    }

    @Before
    public void setup() {
        identityFilter = new IdentityFilter();
    }

    @Test
    public void givenVariousInputsTheOutputAlwaysMatchesTheInput() {
        int output = identityFilter.filter(colour);
        assertThat(output).isEqualTo(colour);
    }

    @Parameterized.Parameters
    public static Collection<Integer> sampleColours() {
        return Arrays.asList(
                ColourUtils.rgb(ColourUtils.HEX_000, ColourUtils.HEX_000, ColourUtils.HEX_000),
                ColourUtils.rgb(ColourUtils.HEX_255, ColourUtils.HEX_000, ColourUtils.HEX_000),
                ColourUtils.rgb(ColourUtils.HEX_000, ColourUtils.HEX_255, ColourUtils.HEX_000),
                ColourUtils.rgb(ColourUtils.HEX_000, ColourUtils.HEX_000, ColourUtils.HEX_255),
                ColourUtils.rgb(ColourUtils.HEX_255, ColourUtils.HEX_255, ColourUtils.HEX_000),
                ColourUtils.rgb(ColourUtils.HEX_255, ColourUtils.HEX_000, ColourUtils.HEX_255),
                ColourUtils.rgb(ColourUtils.HEX_000, ColourUtils.HEX_255, ColourUtils.HEX_255),
                ColourUtils.rgb(ColourUtils.HEX_255, ColourUtils.HEX_255, ColourUtils.HEX_255));
    }
}
