package com.stylingandroid.prism.filter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RepeatableFilterTest {
    @Mock
    private RepeatableFilter repeatableColourFilter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        when(repeatableColourFilter.onFilter(anyInt())).thenReturn(0);
    }

    @Test
    public void givenSpecificColourValueCalculationIsOnlyPerformedOnce() throws Exception {
        repeatableColourFilter.filter(1);
        repeatableColourFilter.filter(1);
        repeatableColourFilter.filter(1);
        repeatableColourFilter.filter(1);
        repeatableColourFilter.filter(1);
        repeatableColourFilter.filter(1);
        verify(repeatableColourFilter, times(1)).onFilter(1);
    }

    @Test
    public void givenDifferentColourValuesCalculationIsPerformedEachTime() throws Exception {
        for (int i = 1; i < 6; i++) {
            repeatableColourFilter.filter(i);
        }
        verify(repeatableColourFilter, times(5)).onFilter(anyInt());
    }
}
