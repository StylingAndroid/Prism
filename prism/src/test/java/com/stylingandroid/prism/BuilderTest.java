package com.stylingandroid.prism;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.stylingandroid.prism.setter.StatusBarSetterAccessor;

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
public class BuilderTest {
    @Mock
    private Trigger trigger;

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
    public void givenNoTriggerThenPrismCreationDoesNotNPE() {
        try {
            Prism.Builder.newInstance().build();
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void givenANonNullTriggerThenPrismCreationSetsTheTriggerCorrectly() {
        Prism.Builder.newInstance().add(trigger).build();
        verify(trigger, atLeastOnce()).addColourSetter(any(Setter.class));
    }

    @Test
    public void givenAVanillaViewThenBackgroundSetsTheBackgroundColour() {
        Setter setter = Prism.Builder.newInstance().add(trigger).background(view).build();
        setter.setColour(ColourUtils.TEST_COLOUR);
        verify(view, atLeastOnce()).setBackgroundColor(ColourUtils.TEST_COLOUR);
    }

    @Test
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void givenAWindowOnLollipopThenSetColourSetsTheStatusBarColour() {
        StatusBarSetterAccessor setter = StatusBarSetterAccessor.newInstance(window, Build.VERSION_CODES.LOLLIPOP);
        Setter colourSetter = Prism.Builder.newInstance().add(trigger).add(setter).build();
        colourSetter.setColour(ColourUtils.TEST_COLOUR);
        verify(window, atLeastOnce()).setStatusBarColor(ColourUtils.TEST_COLOUR);
    }

    @Test
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void givenAWindowOnPreLollipopThenSetColourDoesNotSetTheStatusBarColour() {
        Setter setter = Prism.Builder.newInstance().add(trigger).background(window).build();
        setter.setColour(ColourUtils.TEST_COLOUR);
        verify(window, never()).setStatusBarColor(ColourUtils.TEST_COLOUR);
    }

    @Test
    public void givenATextViewThenSetColourSetsTheTextColour() {
        Setter setter = Prism.Builder.newInstance().add(trigger).text(textView).build();
        setter.setColour(ColourUtils.TEST_COLOUR);
        verify(textView, atLeastOnce()).setTextColor(ColourUtils.TEST_COLOUR);
    }

    @Test
    public void givenAFABThenSetColourSetsTheTintColour() {
        Setter setter = Prism.Builder.newInstance().add(trigger).colour(floatingActionButton).build();
        setter.setColour(ColourUtils.TEST_COLOUR);
        verify(floatingActionButton, atLeastOnce()).setBackgroundTintList(any(ColorStateList.class));
    }

    @Test
    public void givenANullViewThenCreatingABuilderDoesNotThrowAnNpe() {
        try {
            Prism.Builder.newInstance().background((View) null).build();
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void givenANullSetterThenCreatingABuilderDoesNotThrowAnNpe() {
        try {
            Prism.Builder.newInstance().add((Setter) null).build();
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void givenANullTriggerThenCreatingABuilderDoesNotThrowAnNpe() {
        try {
            Prism.Builder.newInstance().add((Trigger) null).build();
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void givenANullFilterThenCreatingABuilderDoesNotThrowAnNpe() {
        try {
            Prism.Builder.newInstance().background(view, null).build();
        } catch (Exception e) {
            Assert.fail();
        }
    }

}
