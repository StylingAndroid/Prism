package com.stylingandroid.prism.setter;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.fest.assertions.api.Assertions.assertThat;

public class FieldAccessorTest {
    private static final int VALUE = 654;

    @Mock
    private Context context;

    private FloatingActionButton floatingActionButton;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        floatingActionButton = new FloatingActionButton(context);
    }

    @Test
    public void givenAnObjectThenFileAccessorReturnsCorrectValue() {
        FieldAccessor<Integer> fieldAccessor = new FieldAccessor<>(floatingActionButton, "privateField", Integer.class);
        int value = fieldAccessor.get();
        assertThat(value).isEqualTo(FloatingActionButton.DEFAULT);
    }

    @Test
    public void givenAnObjectThenFileAccessorCorrectSetsTheValue() {
        FieldAccessor<Integer> fieldAccessor = new FieldAccessor<>(floatingActionButton, "privateField", Integer.class);
        fieldAccessor.set(VALUE);
        int value = fieldAccessor.get();
        assertThat(value).isEqualTo(VALUE);
    }

}
