package com.stylingandroid.prism.setter;

import android.content.res.ColorStateList;
import android.support.annotation.ColorInt;
import android.view.View;

import com.stylingandroid.prism.filter.Filter;

class FabSetter extends BaseSetter {
    private static final String CLASS_NAME = "android.support.design.widget.FloatingActionButton";
    private static final String METHOD_NAME = "setBackgroundTintList";
    private static final int ALPHA_OPAQUE = 0xFF;
    private final MethodInvoker<ColorStateList> methodInvoker;

    public static boolean isFab(View view) {
        if (view == null) {
            return false;
        }
        ReflectiveCache reflectiveCache = ReflectiveCache.getInstance();
        return view.getClass().getCanonicalName().startsWith(CLASS_NAME)
                && reflectiveCache.hasMethod(view.getClass(), METHOD_NAME, ColorStateList.class);
    }

    public FabSetter(View view, Filter filter) {
        super(filter);
        methodInvoker = new MethodInvoker<>(view, METHOD_NAME, ColorStateList.class);
    }

    @Override
    public void onSetColour(@ColorInt int colour) {
        ColorStateList colorStateList = ColorStateList.valueOf(colour).withAlpha(ALPHA_OPAQUE);
        methodInvoker.invoke(colorStateList);
    }
}
