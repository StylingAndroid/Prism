package com.stylingandroid.prism.setter;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.Window;

import com.stylingandroid.prism.ColourSetter;
import com.stylingandroid.prism.ColourUtils;
import com.stylingandroid.prism.filter.IdentityFilter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class StatusBarColourSetterTest {
    @Mock
    private Window window;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenAWindowOnLollipopOrLaterThenTheSetterMethodIsCalledCorrectly() {
        ColourSetter colourSetter = new StatusBarColourSetter(window, new IdentityFilter(), Build.VERSION_CODES.LOLLIPOP);
        colourSetter.setColour(ColourUtils.TEST_COLOUR);
        verify(window, atLeast(1)).setStatusBarColor(anyInt());
    }

    @Test
    public void givenAWindowPreLollipopThenTheSetterMethodIsNotCalled() {
        ColourSetter colourSetter = new StatusBarColourSetter(window, new IdentityFilter());
        colourSetter.setColour(ColourUtils.TEST_COLOUR);
        verify(window, never()).setStatusBarColor(anyInt());
    }
}
