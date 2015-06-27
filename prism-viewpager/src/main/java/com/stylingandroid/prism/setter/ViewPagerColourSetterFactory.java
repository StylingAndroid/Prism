package com.stylingandroid.prism.setter;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.stylingandroid.prism.ColourSetter;
import com.stylingandroid.prism.filter.ColourFilter;

public class ViewPagerColourSetterFactory implements SetterFactory {

    public static void initialise() {
        ColourSetterFactory.registerFactory(new ViewPagerColourSetterFactory());
    }

    @Override
    public ColourSetter getColourSetter(View view, ColourFilter filter) {
        if (view instanceof ViewPager) {
            return ViewPagerGlowSetter.newInstance((ViewPager) view, filter);
        }
        return null;
    }
}
