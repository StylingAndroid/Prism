package com.stylingandroid.prism;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.stylingandroid.prism.setter.StatusBarColourSetterAccessor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Color.class, ColorStateList.class})
public class PrismTest {
    @Mock
    private ColourChangeTrigger colourChangeTrigger;

    @Mock
    private View view;

    @Mock
    private TextView textView;

    @Mock
    private FloatingActionButton floatingActionButton;

    @Mock
    private Window window;

    @Mock
    private ColorStateList colorStateList;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        MockUtils.mockColor();
        mockStatic(ColorStateList.class);
        when(ColorStateList.valueOf(anyInt())).thenReturn(colorStateList);
        when(colorStateList.withAlpha(anyInt())).thenReturn(colorStateList);
    }

    @Test
    public void givenANullTriggerThenPrismCreationDoesNotNPE() {
        try {
            Prism.newInstance(null).build();
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void givenANonNullTriggerThenPrismCreationSetsTheTriggerCorrectly() {
        Prism.newInstance(colourChangeTrigger).build();
        verify(colourChangeTrigger, atLeastOnce()).addColourSetter(any(ColourSetter.class));
    }

    @Test
    public void givenAVanillaViewThenBackgroundSetsTheBackgroundColour() {
        ColourSetter colourSetter = Prism.newInstance(colourChangeTrigger).background(view).build();
        colourSetter.setColour(ColourUtils.TEST_COLOUR);
        verify(view, atLeastOnce()).setBackgroundColor(ColourUtils.TEST_COLOUR);
    }

    @Test
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void givenAWindowOnLollipopThenSetColourSetsTheStatusBarColour() {
        StatusBarColourSetterAccessor setter = StatusBarColourSetterAccessor.newInstance(window, Build.VERSION_CODES.LOLLIPOP);
        ColourSetter colourSetter = Prism.newInstance(colourChangeTrigger).add(setter).build();
        colourSetter.setColour(ColourUtils.TEST_COLOUR);
        verify(window, atLeastOnce()).setStatusBarColor(ColourUtils.TEST_COLOUR);
    }

    @Test
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void givenAWindowOnPreLollipopThenSetColourDoesNotSetTheStatusBarColour() {
        ColourSetter colourSetter = Prism.newInstance(colourChangeTrigger).background(window).build();
        colourSetter.setColour(ColourUtils.TEST_COLOUR);
        verify(window, never()).setStatusBarColor(ColourUtils.TEST_COLOUR);
    }

    @Test
    public void givenATextViewThenSetColourSetsTheTextColour() {
        ColourSetter colourSetter = Prism.newInstance(colourChangeTrigger).text(textView).build();
        colourSetter.setColour(ColourUtils.TEST_COLOUR);
        verify(textView, atLeastOnce()).setTextColor(ColourUtils.TEST_COLOUR);
    }

    @Test
    public void givenAFABThenSetColourSetsTheTintColour() {
        ColourSetter colourSetter = Prism.newInstance(colourChangeTrigger).colour(floatingActionButton).build();
        colourSetter.setColour(ColourUtils.TEST_COLOUR);
        verify(floatingActionButton, atLeastOnce()).setBackgroundTintList(any(ColorStateList.class));
    }

}
