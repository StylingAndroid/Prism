package com.stylingandroid.prism.setter;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.stylingandroid.prism.Setter;
import com.stylingandroid.prism.filter.Filter;

public class ViewPagerSetterFactory implements SetterFactory {
    private static final ViewPagerSetterFactory INSTANCE = new ViewPagerSetterFactory();

    public static void register() {
        ColourSetterFactory.registerFactory(INSTANCE);
    }

    public static void unregister() {
        ColourSetterFactory.unregisterFactory(INSTANCE);
    }

    @Override
    public Setter getColourSetter(View view, Filter filter) {
        if (view instanceof ViewPager) {
            return ViewPagerGlowSetter.newInstance((ViewPager) view, filter);
        }
        return null;
    }
}
