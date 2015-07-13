package com.stylingandroid.prism.setter;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.stylingandroid.prism.Setter;
import com.stylingandroid.prism.filter.Filter;

public class ViewPagerSetterFactory implements SetterFactory {

    public static void initialise() {
        ColourSetterFactory.registerFactory(new ViewPagerSetterFactory());
    }

    @Override
    public Setter getColourSetter(View view, Filter filter) {
        if (view instanceof ViewPager) {
            return ViewPagerGlowSetter.newInstance((ViewPager) view, filter);
        }
        return null;
    }
}
