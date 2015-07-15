package com.stylingandroid.prism.setter;

import android.support.annotation.ColorInt;

import com.stylingandroid.prism.ColourUtils;
import com.stylingandroid.prism.filter.Filter;
import com.stylingandroid.prism.filter.IdentityFilter;

import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class BaseSetterTest {
    private IdentityFilter identityFilter;

    @Before
    public void setup() {
        identityFilter = new IdentityFilter();
    }

    @Test
    public void givenATransientColourSetterTransientEventsAreHandled() {
        TransientSetter colourSetter = new TransientSetter(identityFilter);
        colourSetter.setTransientColour(ColourUtils.TEST_COLOUR);
        assertThat(colourSetter.onSetColourCalled).isTrue();
    }

    @Test
    public void givenATransientColourSetterNonTransientEventsAreHandled() {
        TransientSetter colourSetter = new TransientSetter(identityFilter);
        colourSetter.setColour(ColourUtils.TEST_COLOUR);
        assertThat(colourSetter.onSetColourCalled).isTrue();
    }

    @Test
    public void givenANonTransientColourSetterTransientEventsAreNotHandled() {
        NonTransientSetter colourSetter = new NonTransientSetter(identityFilter);
        colourSetter.setTransientColour(ColourUtils.TEST_COLOUR);
        assertThat(colourSetter.onSetColourCalled).isFalse();
    }

    @Test
    public void givenANonTransientColourSetterNonTransientEventsAreHandled() {
        NonTransientSetter colourSetter = new NonTransientSetter(identityFilter);
        colourSetter.setColour(ColourUtils.TEST_COLOUR);
        assertThat(colourSetter.onSetColourCalled).isTrue();
    }

    private class TransientSetter extends BaseSetter {
        private boolean onSetColourCalled = false;

        protected TransientSetter(Filter filter) {
            super(filter, true);
        }

        @Override
        public void onSetColour(@ColorInt int colour) {
            onSetColourCalled = true;
        }
    }

    private class NonTransientSetter extends BaseSetter {
        private boolean onSetColourCalled = false;

        protected NonTransientSetter(Filter filter) {
            super(filter, false);
        }

        @Override
        public void onSetColour(@ColorInt int colour) {
            onSetColourCalled = true;
        }
    }
}
