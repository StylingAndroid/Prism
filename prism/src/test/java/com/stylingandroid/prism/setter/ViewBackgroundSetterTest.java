package com.stylingandroid.prism.setter;

import android.view.View;

import com.stylingandroid.prism.ColourUtils;
import com.stylingandroid.prism.filter.IdentityFilter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

public class ViewBackgroundSetterTest {
    @Mock
    private View view;

    private ViewBackgroundSetter backgroundColourSetter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        backgroundColourSetter = new ViewBackgroundSetter(view, new IdentityFilter());
    }

    @Test
    public void givenAViewThenSetBackgroundColorIsCalled() {
        backgroundColourSetter.setColour(ColourUtils.TEST_COLOUR);
        verify(view, atLeast(1)).setBackgroundColor(anyInt());
    }
}
