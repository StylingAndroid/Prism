package com.stylingandroid.prism.setter;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.EdgeEffectCompat;
import android.widget.EdgeEffect;

import com.stylingandroid.prism.filter.Filter;
import com.stylingandroid.prism.filter.IdentityFilter;

public abstract class ViewPagerGlowSetter extends BaseSetter {
    private static final IdentityFilter IDENTITY_FILTER = new IdentityFilter();

    private ViewPagerGlowSetter(Filter filter) {
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
        FieldAccessor<EdgeEffectCompat> leftField = new FieldAccessor<>(view, "mLeftEdge", EdgeEffectCompat.class);
        FieldAccessor<EdgeEffectCompat> rightField = new FieldAccessor<>(view, "mRightEdge", EdgeEffectCompat.class);
        FieldAccessor<EdgeEffect> leftEdgeEffectAccessor = new FieldAccessor<>(leftField.get(), "mEdgeEffect", EdgeEffect.class);
        FieldAccessor<EdgeEffect> rightEdgeEffectAccessor = new FieldAccessor<>(rightField.get(), "mEdgeEffect", EdgeEffect.class);
        EdgeEffect leftEdgeEffect = leftEdgeEffectAccessor.get();
        EdgeEffect rightEdgeEffect = rightEdgeEffectAccessor.get();
        return new GlowSetterLollipop(filter, leftEdgeEffect, rightEdgeEffect);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private static ViewPagerGlowSetter newInstanceIcs(ViewPager view, Filter filter) {
        Resources resources = view.getResources();
        int glowId = resources.getIdentifier("overscroll_glow", "drawable", "android");
        int edgeId = resources.getIdentifier("overscroll_edge", "drawable", "android");
        FieldAccessor<EdgeEffectCompat> leftField = new FieldAccessor<>(view, "mLeftEdge", EdgeEffectCompat.class);
        FieldAccessor<EdgeEffectCompat> rightField = new FieldAccessor<>(view, "mRightEdge", EdgeEffectCompat.class);
        FieldAccessor<EdgeEffect> leftEdgeEffectAccessor = new FieldAccessor<>(leftField.get(), "mEdgeEffect", EdgeEffect.class);
        FieldAccessor<EdgeEffect> rightEdgeEffectAccessor = new FieldAccessor<>(rightField.get(), "mEdgeEffect", EdgeEffect.class);
        FieldAccessor<Drawable> leftGlowAccessor = new FieldAccessor<>(leftEdgeEffectAccessor.get(), "mGlow", Drawable.class);
        FieldAccessor<Drawable> leftEdgeAccessor = new FieldAccessor<>(leftEdgeEffectAccessor.get(), "mEdge", Drawable.class);
        FieldAccessor<Drawable> rightGlowAccessor = new FieldAccessor<>(rightEdgeEffectAccessor.get(), "mGlow", Drawable.class);
        FieldAccessor<Drawable> rightEdgeAccessor = new FieldAccessor<>(rightEdgeEffectAccessor.get(), "mEdge", Drawable.class);
        GlowSetterIcs.Setter leftSetter = new GlowSetterIcs.Setter(resources, glowId, edgeId, leftGlowAccessor, leftEdgeAccessor);
        GlowSetterIcs.Setter rightSetter = new GlowSetterIcs.Setter(resources, glowId, edgeId, rightGlowAccessor, rightEdgeAccessor);
        return new GlowSetterIcs(filter, leftSetter, rightSetter);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private static final class GlowSetterLollipop extends ViewPagerGlowSetter {
        private final EdgeEffect leftEdgeEffect;
        private final EdgeEffect rightEdgeEffect;

        public GlowSetterLollipop(Filter filter, EdgeEffect leftEdgeEffect, EdgeEffect rightEdgeEffect) {
            super(filter);
            this.leftEdgeEffect = leftEdgeEffect;
            this.rightEdgeEffect = rightEdgeEffect;
        }

        @Override
        public void onSetColour(@ColorInt int colour) {
            leftEdgeEffect.setColor(colour);
            rightEdgeEffect.setColor(colour);
        }
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private static final class GlowSetterIcs extends ViewPagerGlowSetter {
        private final Setter left;
        private final Setter right;

        private GlowSetterIcs(Filter filter, Setter left, Setter right) {
            super(filter);
            this.left = left;
            this.right = right;
        }

        @Override
        public void onSetColour(@ColorInt int colour) {
            left.setColour(colour);
            right.setColour(colour);
        }

        private static final class Setter {
            private final Resources resources;
            private final int glowDrawableId;
            private final int edgeDrawableId;
            private final FieldAccessor<Drawable> glowAccessor;
            private final FieldAccessor<Drawable> edgeAccessor;

            private Setter(Resources resources, int glowId, int edgeId, FieldAccessor<Drawable> glowAccessor, FieldAccessor<Drawable> edgeAccessor) {
                this.resources = resources;
                this.glowDrawableId = glowId;
                this.edgeDrawableId = edgeId;
                this.glowAccessor = glowAccessor;
                this.edgeAccessor = edgeAccessor;
            }

            public void setColour(@ColorInt int colour) {
                Drawable glow = getDrawable(glowDrawableId);
                if (glow != null && glow instanceof BitmapDrawable) {
                    Bitmap bitmap = ((BitmapDrawable) glow).getBitmap();
                    Bitmap coloured = colourise(bitmap, colour);
                    glow = new BitmapDrawable(resources, coloured);
                    glowAccessor.set(glow);
                }
                Drawable edge = getDrawable(edgeDrawableId);
                if (edge != null && edge instanceof BitmapDrawable) {
                    Bitmap bitmap = ((BitmapDrawable) edge).getBitmap();
                    Bitmap coloured = colourise(bitmap, colour);
                    edge = new BitmapDrawable(resources, coloured);
                    edgeAccessor.set(edge);
                }
            }

            @SuppressWarnings("deprecation")
            private Drawable getDrawable(int id) {
                return resources.getDrawable(id);
            }

            private Bitmap colourise(Bitmap bitmap, @ColorInt int colour) {
                Bitmap coloured = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
                ColorFilter cf = new PorterDuffColorFilter(colour, PorterDuff.Mode.SRC_IN);
                Canvas canvas = new Canvas(coloured);
                Paint paint = new Paint();
                paint.setColorFilter(cf);
                canvas.drawBitmap(bitmap, 0, 0, paint);
                return coloured;
            }
        }

    }

    private static final class ViewPagerGlowSetterLegacy extends ViewPagerGlowSetter {

        private ViewPagerGlowSetterLegacy(Filter filter) {
            super(filter);
        }

        @Override
        public void onSetColour(@ColorInt int colour) {

        }

    }
}
