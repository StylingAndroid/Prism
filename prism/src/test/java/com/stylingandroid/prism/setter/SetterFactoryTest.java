package com.stylingandroid.prism.setter;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.stylingandroid.prism.Setter;
import com.stylingandroid.prism.filter.Filter;
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

public class SetterFactoryTest {
    @Mock
    private SetterFactory setterFactory;

    @Mock
    private Setter setter;

    @Mock
    private Setter windowSetter;

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

    private Filter identity;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(setterFactory.getColourSetter(isA(CustomView.class), any(Filter.class))).thenReturn(setter);
        ColourSetterFactory.registerFactory(setterFactory);
        identity = new IdentityFilter();
    }

    @After
    public void teardown() {
        ColourSetterFactory.unregisterFactory(setterFactory);
    }

    @Test
    public void givenAStandardViewWhenRequestingABackgroundSetterThenAViewBackgroundColourSetterIsReturned() {
        Setter setter = ColourSetterFactory.getBackgroundSetter(vanillaView, identity);
        assertThat(setter).isInstanceOf(ViewBackgroundSetter.class);
    }

    @Test
    public void givenAStandardViewWhenRequestingAColourSetterThenAViewBackgroundColourSetterIsReturned() {
        Setter setter = ColourSetterFactory.getColourSetter(vanillaView, identity);
        assertThat(setter).isInstanceOf(ViewBackgroundSetter.class);
    }

    @Test
    public void givenATextViewWhenRequestingABackgroundSetterThenAViewBackgroundColourSetterIsReturned() {
        Setter setter = ColourSetterFactory.getBackgroundSetter(textView, identity);
        assertThat(setter).isInstanceOf(ViewBackgroundSetter.class);
    }

    @Test
    public void givenATextViewWhenRequestingAColourSetterThenABackgroundColourSetterIsReturned() {
        Setter setter = ColourSetterFactory.getColourSetter(textView, identity);
        assertThat(setter).isInstanceOf(ViewBackgroundSetter.class);
    }

    @Test
    public void givenATextViewWhenRequestingATextSetterThenATextColourSetterIsReturned() {
        Setter setter = ColourSetterFactory.getTextSetter(textView, identity);
        assertThat(setter).isInstanceOf(TextSetter.class);
    }

    @Test
    public void givenAFabViewWhenRequestingAColourSetterThenAFabColourSetterIsReturned() {
        Setter setter = ColourSetterFactory.getColourSetter(floatingActionButton, identity);
        assertThat(setter).isInstanceOf(FabSetter.class);
    }

    @Test
    public void givenACustomViewWhenRequestingAColourSetterThenACustomColourSetterIsReturned() {
        Setter setter = ColourSetterFactory.getColourSetter(customView, identity);
        assertThat(setter).isEqualTo(this.setter);
    }

    @Test
    public void givenAWindowRequestingABackgroundSetterThenAStatusBarSetterSetterIsReturned() {
        Setter setter = ColourSetterFactory.getBackgroundSetter(window, identity);
        assertThat(setter).isInstanceOf(SystemChromeSetter.class);
    }

    private class CustomView extends View {
        public CustomView(Context context) {
            super(context);
        }
    }
}
