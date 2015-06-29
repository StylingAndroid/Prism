package com.stylingandroid.prism.setter;

import android.support.annotation.ColorInt;

import com.stylingandroid.prism.ColourUtils;
import com.stylingandroid.prism.filter.ColourFilter;
import com.stylingandroid.prism.filter.IdentityFilter;

import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class BaseColourSetterTest {
    private IdentityFilter identityFilter;

    @Before
    public void setup() {
        identityFilter = new IdentityFilter();
    }

    @Test
    public void givenATransientColourSetterTransientEventsAreHandled() {
        TransientColourSetter colourSetter = new TransientColourSetter(identityFilter);
        colourSetter.setColour(ColourUtils.TEST_COLOUR, true);
        assertThat(colourSetter.onSetColourCalled).isTrue();
    }

    @Test
    public void givenATransientColourSetterNonTransientEventsAreHandled() {
        TransientColourSetter colourSetter = new TransientColourSetter(identityFilter);
        colourSetter.setColour(ColourUtils.TEST_COLOUR, false);
        assertThat(colourSetter.onSetColourCalled).isTrue();
    }

    @Test
    public void givenANonTransientColourSetterTransientEventsAreNotHandled() {
        NonTransientColourSetter colourSetter = new NonTransientColourSetter(identityFilter);
        colourSetter.setColour(ColourUtils.TEST_COLOUR, true);
        assertThat(colourSetter.onSetColourCalled).isFalse();
    }

    @Test
    public void givenANonTransientColourSetterNonTransientEventsAreHandled() {
        NonTransientColourSetter colourSetter = new NonTransientColourSetter(identityFilter);
        colourSetter.setColour(ColourUtils.TEST_COLOUR, false);
        assertThat(colourSetter.onSetColourCalled).isTrue();
    }

    private class TransientColourSetter extends BaseColourSetter {
        private boolean onSetColourCalled = false;

        protected TransientColourSetter(ColourFilter filter) {
            super(filter, true);
        }

        @Override
        public void onSetColour(@ColorInt int colour) {
            onSetColourCalled = true;
        }
    }

    private class NonTransientColourSetter extends BaseColourSetter {
        private boolean onSetColourCalled = false;

        protected NonTransientColourSetter(ColourFilter filter) {
            super(filter, false);
        }

        @Override
        public void onSetColour(@ColorInt int colour) {
            onSetColourCalled = true;
        }
    }
}
