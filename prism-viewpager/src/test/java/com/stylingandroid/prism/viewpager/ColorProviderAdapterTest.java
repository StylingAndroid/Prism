package com.stylingandroid.prism.viewpager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

public class ColorProviderAdapterTest {
    @Mock
    private ColorProvider colorProvider;

    private ColourProvider colourProvider;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        colourProvider = new ColorProviderAdapter(colorProvider);
    }

    @Test
    public void givenAColorProviderWrappedInAColourProviderCompatThenGetColorMethodIsCalled() {
        colourProvider.getColour(0);
        verify(colorProvider, atLeastOnce()).getColor(anyInt());
    }
}
