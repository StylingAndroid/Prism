package com.stylingandroid.prism;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

public class SetterAdapterTest {
    @Mock
    private ColorSetter colorSetter;

    private Setter setter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        setter = new SetterAdapter(colorSetter);
    }

    @Test
    public void givenAColourSetterWrappedInASetterAdapterThenSetColorIsCalled() {
        setter.setColour(1);
        verify(colorSetter, atLeastOnce()).setColor(anyInt());
    }

    @Test
    public void givenAColourSetterWrappedInASetterAdapterThenTransientSetColorIsCalled() {
        setter.setTransientColour(1);
        verify(colorSetter, atLeastOnce()).setTransientColor(anyInt());
    }
}
