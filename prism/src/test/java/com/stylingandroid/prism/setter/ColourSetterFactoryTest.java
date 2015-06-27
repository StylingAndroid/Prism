package com.stylingandroid.prism.setter;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.stylingandroid.prism.ColourSetter;
import com.stylingandroid.prism.filter.ColourFilter;
import com.stylingandroid.prism.filter.IdentityFilter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;

public class ColourSetterFactoryTest {
    @Mock
    private SetterFactory setterFactory;

    @Mock
    private ColourSetter colourSetter;

    @Mock
    private ColourSetter windowColourSetter;

    @Mock
    private CustomView customView;

    @Mock
    private FloatingActionButton floatingActionButton;

    @Mock
    private TextView textView;

    @Mock
    private View vanillaView;

    @Mock
    private Window window;

    private ColourFilter identity;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(setterFactory.getColourSetter(isA(CustomView.class), any(ColourFilter.class))).thenReturn(colourSetter);
        ColourSetterFactory.registerFactory(setterFactory);
        identity = new IdentityFilter();
    }

    @After
    public void teardown() {
        ColourSetterFactory.unregisterFactory(setterFactory);
    }

    @Test
    public void givenAStandardViewWhenRequestingABackgroundSetterThenAViewBackgroundColourSetterIsReturned() {
        ColourSetter setter = ColourSetterFactory.getBackgroundSetter(vanillaView, identity);
        assertThat(setter).isInstanceOf(ViewBackgroundColourSetter.class);
    }

    @Test
    public void givenAStandardViewWhenRequestingAColourSetterThenAViewBackgroundColourSetterIsReturned() {
        ColourSetter setter = ColourSetterFactory.getColourSetter(vanillaView, identity);
        assertThat(setter).isInstanceOf(ViewBackgroundColourSetter.class);
    }

    @Test
    public void givenATextViewWhenRequestingABackgroundSetterThenAViewBackgroundColourSetterIsReturned() {
        ColourSetter setter = ColourSetterFactory.getBackgroundSetter(textView, identity);
        assertThat(setter).isInstanceOf(ViewBackgroundColourSetter.class);
    }

    @Test
    public void givenATextViewWhenRequestingAColourSetterThenABackgroundColourSetterIsReturned() {
        ColourSetter setter = ColourSetterFactory.getColourSetter(textView, identity);
        assertThat(setter).isInstanceOf(ViewBackgroundColourSetter.class);
    }

    @Test
    public void givenATextViewWhenRequestingATextSetterThenATextColourSetterIsReturned() {
        ColourSetter setter = ColourSetterFactory.getTextSetter(textView, identity);
        assertThat(setter).isInstanceOf(TextColourSetter.class);
    }

    @Test
    public void givenAFabViewWhenRequestingAColourSetterThenAFabColourSetterIsReturned() {
        ColourSetter setter = ColourSetterFactory.getColourSetter(floatingActionButton, identity);
        assertThat(setter).isInstanceOf(FabColourSetter.class);
    }

    @Test
    public void givenACustomViewWhenRequestingAColourSetterThenACustomColourSetterIsReturned() {
        ColourSetter setter = ColourSetterFactory.getColourSetter(customView, identity);
        assertThat(setter).isEqualTo(colourSetter);
    }

    @Test
    public void givenAWindowRequestingABackgroundSetterThenAStatusBarSetterSetterIsReturned() {
        ColourSetter setter = ColourSetterFactory.getBackgroundSetter(window, identity);
        assertThat(setter).isInstanceOf(StatusBarColourSetter.class);
    }

    private class CustomView extends View {
        public CustomView(Context context) {
            super(context);
        }
    }
}
