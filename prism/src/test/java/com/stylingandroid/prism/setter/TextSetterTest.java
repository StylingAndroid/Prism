package com.stylingandroid.prism.setter;

import android.widget.TextView;

import com.stylingandroid.prism.ColourUtils;
import com.stylingandroid.prism.filter.IdentityFilter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

public class TextSetterTest {
    @Mock
    private TextView textView;

    private TextSetter textColourSetter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        textColourSetter = new TextSetter(textView, new IdentityFilter());
    }

    @Test
    public void givenAViewThenSetBackgroundColorIsCalled() {
        textColourSetter.setColour(ColourUtils.TEST_COLOUR);
        verify(textView, atLeast(1)).setTextColor(anyInt());
    }

}
