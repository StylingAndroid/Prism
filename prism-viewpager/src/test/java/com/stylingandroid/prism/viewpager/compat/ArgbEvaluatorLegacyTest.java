package com.stylingandroid.prism.viewpager.compat;

import android.support.annotation.ColorInt;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(Parameterized.class)
public class ArgbEvaluatorLegacyTest {
    private final float fraction;
    @ColorInt
    private final int expected;

    private ArgbEvaluatorCompat argbEvaluator;

    @ColorInt
    private int startColour;

    @ColorInt
    private int endColour;

    @Before
    public void setUp() throws Exception {
        argbEvaluator = new ArgbEvaluatorCompat.ArgbEvaluatorLegacy();
        startColour = 0x000000;
        endColour = 0x0000FF;
    }

    public ArgbEvaluatorLegacyTest(float fraction, @ColorInt int expected) {
        this.fraction = fraction;
        this.expected = expected;
    }

    @Test
    public void givenTheLegacyArgbEvaluatorAndASpecificFractionThenTheCorrectColourIsCalculated() {
        int calculated = (int)argbEvaluator.evaluate(fraction, startColour, endColour);
        assertThat(calculated).isEqualTo(expected);
    }

    @Parameterized.Parameters
    public static Collection values() {
        return Arrays.asList(new Object[][] {
                                     {0f, 0},
                                     {0.1f, 25},
                                     {0.2f, 51},
                                     {0.3f, 76},
                                     {0.4f, 102},
                                     {0.5f, 127},
                                     {0.6f, 153},
                                     {0.7f, 178},
                                     {0.8f, 204},
                                     {0.9f, 229},
                                     {1f, 255},
                             });
    }
}
