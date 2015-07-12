package com.stylingandroid.prism.viewpager.compat;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class ArgbEvaluatorCompatTest {

    @Test
    public void givenAPreHoneycombAndroidVersionThenALegacyArgbEvaluatorIsReturned() {
        ArgbEvaluatorCompat argbEvaluator = ArgbEvaluatorCompat.newInstance(10);
        assertThat(argbEvaluator).isInstanceOf(ArgbEvaluatorCompat.ArgbEvaluatorLegacy.class);
    }

    @Test
    public void givenNoAndroidVersionThenALegacyArgbEvaluatorIsReturned() {
        ArgbEvaluatorCompat argbEvaluator = ArgbEvaluatorCompat.newInstance();
        assertThat(argbEvaluator).isInstanceOf(ArgbEvaluatorCompat.ArgbEvaluatorLegacy.class);
    }

    @Test
    public void givenAPostHoneycombAndroidVersionThenAHoneycombArgbEvaluatorIsReturned() {
        ArgbEvaluatorCompat argbEvaluator = ArgbEvaluatorCompat.newInstance(22);
        assertThat(argbEvaluator).isInstanceOf(ArgbEvaluatorCompat.ArgbEvaluatorHoneycomb.class);
    }
}
