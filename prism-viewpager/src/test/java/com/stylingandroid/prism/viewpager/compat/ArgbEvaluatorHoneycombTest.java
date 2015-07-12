package com.stylingandroid.prism.viewpager.compat;

import android.animation.ArgbEvaluator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyFloat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

public class ArgbEvaluatorHoneycombTest {
    @Mock
    private ArgbEvaluator inbuiltArgbEvaluator;

    private ArgbEvaluatorCompat argbEvaluator;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        argbEvaluator = new ArgbEvaluatorCompat.ArgbEvaluatorHoneycomb(inbuiltArgbEvaluator);
    }

    @Test
    public void givenAArgbEvaluatorHoneycombThenTheInbuiltArgbEvaluatorIsUsed() {
        argbEvaluator.evaluate(1f, 0x000000, 0x0000FF);
        verify(inbuiltArgbEvaluator, atLeastOnce()).evaluate(anyFloat(), any(), any());
    }

}
