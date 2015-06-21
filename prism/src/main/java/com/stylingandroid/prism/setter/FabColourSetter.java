package com.stylingandroid.prism.setter;

import android.content.res.ColorStateList;
import android.view.View;

import com.stylingandroid.prism.filter.ColourFilter;

class FabColourSetter extends BaseColourSetter {
    private static final String CLASS_NAME = "android.support.design.widget.FloatingActionButton";
    private static final String METHOD_NAME = "setBackgroundTintList";
    private final MethodInvoker<ColorStateList> methodInvoker;

    public static boolean isFab(View view) {
        ReflectiveCache reflectiveCache = ReflectiveCache.getInstance();
        return view.getClass().getCanonicalName().equals(CLASS_NAME)
                && reflectiveCache.hasMethod(view.getClass(), METHOD_NAME, ColorStateList.class);
    }

    public FabColourSetter(View view, ColourFilter filter) {
        super(filter);
        methodInvoker = new MethodInvoker<>(view, METHOD_NAME, ColorStateList.class);
    }

    @Override
    public void onSetColour(int colour) {
        ColorStateList colorStateList = ColorStateList.valueOf(colour).withAlpha(0xFF);
        methodInvoker.invoke(colorStateList);
    }
}
