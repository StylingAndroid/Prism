package com.stylingandroid.prism.setter;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.EdgeEffectCompat;
import android.widget.EdgeEffect;

import com.stylingandroid.prism.filter.Filter;
import com.stylingandroid.prism.filter.IdentityFilter;

public abstract class ViewPagerGlowSetter extends BaseSetter {
    private static final IdentityFilter IDENTITY_FILTER = new IdentityFilter();
    private static final String LEFT_EDGE = "mLeftEdge";
    private static final String RIGHT_EDGE = "mRightEdge";
    private static final String EDGE_EFFECT = "mEdgeEffect";
    private static final String OVERSCROLL_GLOW = "overscroll_glow";
    private static final String OVERSCROLL_EDGE = "overscroll_edge";
    private static final String DRAWABLE = "drawable";
    private static final String ANDROID = "android";

    ViewPagerGlowSetter(Filter filter) {
        super(filter, false);
    }

    public static ViewPagerGlowSetter newInstance(@NonNull ViewPager view) {
        return newInstance(view, IDENTITY_FILTER);
    }

    public static ViewPagerGlowSetter newInstance(@NonNull ViewPager view, @Nullable Filter filter) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return newInstanceLollipop(view, filter);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            return newInstanceIcs(view, filter);
        } else {
            return new ViewPagerGlowSetterLegacy(filter);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private static ViewPagerGlowSetter newInstanceLollipop(ViewPager view, Filter filter) {
        EdgeEffect leftEdgeEffect = getEdgeEffect(view, LEFT_EDGE);
        EdgeEffect rightEdgeEffect = getEdgeEffect(view, RIGHT_EDGE);
        return new GlowSetterLollipop(filter, leftEdgeEffect, rightEdgeEffect);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private static EdgeEffect getEdgeEffect(ViewPager viewPager, String edgeFieldName) {
        FieldAccessor<EdgeEffectCompat> edgeField = new FieldAccessor<>(viewPager, edgeFieldName, EdgeEffectCompat.class);
        FieldAccessor<EdgeEffect> edgeEffectAccessor = new FieldAccessor<>(edgeField.get(), EDGE_EFFECT, EdgeEffect.class);
        return edgeEffectAccessor.get();
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private static ViewPagerGlowSetter newInstanceIcs(ViewPager viewPager, Filter filter) {
        Resources resources = viewPager.getResources();
        int glowId = getAndroidDrawableIdentifier(resources, OVERSCROLL_GLOW);
        int edgeId = getAndroidDrawableIdentifier(resources, OVERSCROLL_EDGE);
        EdgeEffect leftEdgeEffect = getEdgeEffect(viewPager, LEFT_EDGE);
        EdgeEffect rightEdgeEffect = getEdgeEffect(viewPager, RIGHT_EDGE);
        return new GlowSetterIcs(filter, resources, glowId, edgeId, leftEdgeEffect, rightEdgeEffect);
    }

    private static int getAndroidDrawableIdentifier(Resources resources, String overscrollGlow) {
        return resources.getIdentifier(overscrollGlow, DRAWABLE, ANDROID);
    }

    static FieldAccessor<Drawable> getDrawableAccessor(EdgeEffect edgeEffect, String fieldName) {
        return new FieldAccessor<>(edgeEffect, fieldName, Drawable.class);
    }

}
