package com.stylingandroid.prism.setter;

import android.content.res.ColorStateList;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.stylingandroid.prism.ColourUtils;
import com.stylingandroid.prism.filter.IdentityFilter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ColorStateList.class)
public class FabSetterTest {
    @Mock
    private FloatingActionButton floatingActionButton;
    @Mock
    private View vanillaView;
    @Mock
    private ColorStateList colorStateList;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockStatic(ColorStateList.class);
        when(ColorStateList.valueOf(anyInt())).thenReturn(colorStateList);
        when(colorStateList.withAlpha(anyInt())).thenReturn(colorStateList);
    }

    @Test
    public void givenAFabThenIsFabReturnsTrue() {
        assertThat(FabSetter.isFab(floatingActionButton)).isTrue();
    }

    @Test
    public void givenAVanillaViewThenIsFabReturnsTrue() {
        assertThat(FabSetter.isFab(vanillaView)).isFalse();
    }

    @Test
    public void givenAFabThenTheSetterMethodIsCalledCorrectly() {
        FabSetter fabColourSetter = new FabSetter(floatingActionButton, new IdentityFilter());
        fabColourSetter.setColour(ColourUtils.TEST_COLOUR);
        verify(floatingActionButton, times(1)).setBackgroundTintList(any(ColorStateList.class));
    }
}
