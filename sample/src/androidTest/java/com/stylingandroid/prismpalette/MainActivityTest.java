package com.stylingandroid.prismpalette;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.TextView;

import com.stylingandroid.prism.sample.MainActivity;
import com.stylingandroid.prism.sample.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void givenTheUserClicksTheFabThenTheColourOfTheTextIsNotRed() {
        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.text_view)).check(matches(not(withTextColour(Color.RED))));
    }

    @Test
    public void givenTheUserClicksTheFabThenTheColourOfTheTextIsGreen() {
        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.text_view)).check(matches(withTextColour(Color.GREEN)));
    }

    @Test
    public void givenTheUserClicksTheFabTwiceThenTheColourOfTheTextIsNotRed() {
        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.text_view)).check(matches(not(withTextColour(Color.RED))));
    }

    @Test
    public void givenTheUserClicksTheFabTwiceThenTheColourOfTheTextIsBlue() {
        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.text_view)).check(matches(withTextColour(Color.BLUE)));
    }

    @Test
    public void givenTheUserClicksTheFabThreeTimesThenTheColourOfTheTextIsRed() {
        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.text_view)).check(matches(withTextColour(Color.RED)));
    }

    @Test
    public void givenADefaultLayoutThenTheColourOfTheTextIsRed() {
        onView(withId(R.id.text_view)).check(matches(withTextColour(Color.RED)));
    }

    private static Matcher<View> withTextColour(@ColorInt final int colour) {
        return new BoundedMatcher<View, TextView>(TextView.class) {
            @Override
            protected boolean matchesSafely(TextView view) {
                return colour == view.getCurrentTextColor();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with background colour: ");
            }
        };
    }
}
