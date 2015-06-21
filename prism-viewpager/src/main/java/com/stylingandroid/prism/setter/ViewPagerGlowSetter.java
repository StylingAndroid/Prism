package com.stylingandroid.prism.setter;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.EdgeEffectCompat;
import android.widget.EdgeEffect;

import com.stylingandroid.prism.filter.ColourFilter;

public abstract class ViewPagerGlowSetter extends BaseColourSetter {

    private ViewPagerGlowSetter(ColourFilter filter) {
        super(filter);
    }

    public static ViewPagerGlowSetter newInstance(ViewPager view, ColourFilter filter) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return newInstanceLollipop(view, filter);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            return newInstanceIcs(view, filter);
        } else {
            return new ViewPagerGlowSetterLegacy(filter);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private static ViewPagerGlowSetter newInstanceLollipop(ViewPager view, ColourFilter filter) {
        FieldAccessor<EdgeEffectCompat> leftField = new FieldAccessor<>(view, "mLeftEdge", EdgeEffectCompat.class);
        FieldAccessor<EdgeEffectCompat> rightField = new FieldAccessor<>(view, "mRightEdge", EdgeEffectCompat.class);
        FieldAccessor<EdgeEffect> leftEdgeEffectAccessor = new FieldAccessor<>(leftField.get(), "mEdgeEffect", EdgeEffect.class);
        FieldAccessor<EdgeEffect> rightEdgeEffectAccessor = new FieldAccessor<>(rightField.get(), "mEdgeEffect", EdgeEffect.class);
        EdgeEffect leftEdgeEffect = leftEdgeEffectAccessor.get();
        EdgeEffect rightEdgeEffect = rightEdgeEffectAccessor.get();
        return new ViewPagerGlowSetterLollipop(filter, leftEdgeEffect, rightEdgeEffect);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private static ViewPagerGlowSetter newInstanceIcs(ViewPager view, ColourFilter filter) {
        Resources resources = view.getResources();
        int glowDrawableId = resources.getIdentifier("overscroll_glow", "drawable", "android");
        int edgeDrawableId = resources.getIdentifier("overscroll_edge", "drawable", "android");
        return new ViewPagerGlowSetterIcs(filter, resources, glowDrawableId, edgeDrawableId);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private static final class ViewPagerGlowSetterLollipop extends ViewPagerGlowSetter {
        private final EdgeEffect leftEdgeEffect;
        private final EdgeEffect rightEdgeEffect;

        public ViewPagerGlowSetterLollipop(ColourFilter filter, EdgeEffect leftEdgeEffect, EdgeEffect rightEdgeEffect) {
            super(filter);
            this.leftEdgeEffect = leftEdgeEffect;
            this.rightEdgeEffect = rightEdgeEffect;
        }

        @Override
        public void onSetColour(int colour) {
            leftEdgeEffect.setColor(colour);
            rightEdgeEffect.setColor(colour);
        }
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private static final class ViewPagerGlowSetterIcs extends ViewPagerGlowSetter {
        private final Resources resources;
        private final int glowDrawableId;
        private final int edgeDrawableId;

        private ViewPagerGlowSetterIcs(ColourFilter filter, Resources resources, int glowDrawableId, int edgeDrawableId) {
            super(filter);
            this.resources = resources;
            this.glowDrawableId = glowDrawableId;
            this.edgeDrawableId = edgeDrawableId;
        }

        @Override
        public void onSetColour(int colour) {
            Drawable glow = getDrawable(glowDrawableId);
            if (glow != null) {
                glow.setColorFilter(colour, PorterDuff.Mode.MULTIPLY);
            }
            Drawable edge = getDrawable(edgeDrawableId);
            if (edge != null) {
                edge.setColorFilter(colour, PorterDuff.Mode.MULTIPLY);
            }
        }

        private Drawable getDrawable(int id) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                return getDrawableLollipop(id);
            }
            return getDrawableLegacy(id);
        }

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        private Drawable getDrawableLollipop(int id) {
            return resources.getDrawable(id, null);
        }

        @SuppressWarnings("deprecation")
        private Drawable getDrawableLegacy(int id) {
            return resources.getDrawable(id);
        }
    }

    private static final class ViewPagerGlowSetterLegacy extends ViewPagerGlowSetter {
        private ViewPagerGlowSetterLegacy(ColourFilter filter) {
            super(filter);
        }

        @Override
        public void onSetColour(int colour) {

        }
    }

}
