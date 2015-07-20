package com.stylingandroid.prism.setter;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.Window;

import com.stylingandroid.prism.Setter;
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
public class SystemChromeSetterTest {
    @Mock
    private Window window;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenAWindowOnLollipopOrLaterThenTheStatusBarSetterMethodIsCalledCorrectly() {
        Setter setter = new SystemChromeSetter(window, new IdentityFilter(), Build.VERSION_CODES.LOLLIPOP);
        setter.setColour(ColourUtils.TEST_COLOUR);
        verify(window, atLeast(1)).setStatusBarColor(anyInt());
        verify(window, atLeast(1)).setNavigationBarColor(anyInt());
    }

    @Test
    public void givenAWindowOnLollipopOrLaterThenTheNavigationBarSetterMethodIsCalledCorrectly() {
        Setter setter = new SystemChromeSetter(window, new IdentityFilter(), Build.VERSION_CODES.LOLLIPOP);
        setter.setColour(ColourUtils.TEST_COLOUR);
        verify(window, atLeast(1)).setNavigationBarColor(anyInt());
    }

    @Test
    public void givenAWindowPreLollipopThenTheStatusBarSetterMethodIsNotCalled() {
        Setter setter = new SystemChromeSetter(window, new IdentityFilter());
        setter.setColour(ColourUtils.TEST_COLOUR);
        verify(window, never()).setStatusBarColor(anyInt());
    }

    @Test
    public void givenAWindowPreLollipopThenTheNavigationBarSetterMethodIsNotCalled() {
        Setter setter = new SystemChromeSetter(window, new IdentityFilter());
        setter.setColour(ColourUtils.TEST_COLOUR);
        verify(window, never()).setNavigationBarColor(anyInt());
    }
}
