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
import android.support.annotation.DrawableRes;
import android.widget.EdgeEffect;

import com.stylingandroid.prism.filter.Filter;

@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
final class GlowSetterIcs extends ViewPagerGlowSetter {
    private static final String GLOW = "mGlow";
    private static final String EDGE = "mEdge";

    private final Resources resources;
    private final int glowDrawableId;
    private final int edgeDrawableId;
    private final Setter left;
    private final Setter right;

    GlowSetterIcs(Filter filter, Resources resources, int glowId, int edgeId, EdgeEffect leftEdgeEffect, EdgeEffect rightEdgeEffect) {
        super(filter);
        this.resources = resources;
        glowDrawableId = glowId;
        edgeDrawableId = edgeId;
        this.left = new Setter(leftEdgeEffect);
        this.right = new Setter(rightEdgeEffect);
    }

    @Override
    public void onSetColour(@ColorInt int colour) {
        left.setColour(colour);
        right.setColour(colour);
    }

    private void setDrawableColour(FieldAccessor<Drawable> drawableFieldAccessor, @DrawableRes int drawableId, @ColorInt int colour) {
        Drawable drawable = getDrawable(drawableId);
        if (drawable != null && drawable instanceof BitmapDrawable) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            Bitmap coloured = colourise(bitmap, colour);
            drawable = new BitmapDrawable(resources, coloured);
            drawableFieldAccessor.set(drawable);
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

    private final class Setter {
        private final FieldAccessor<Drawable> glowAccessor;
        private final FieldAccessor<Drawable> edgeAccessor;

        private Setter(EdgeEffect edgeEffect) {
            this.glowAccessor = getDrawableAccessor(edgeEffect, GLOW);
            this.edgeAccessor = getDrawableAccessor(edgeEffect, EDGE);
        }

        public void setColour(@ColorInt int colour) {
            setDrawableColour(glowAccessor, glowDrawableId, colour);
            setDrawableColour(edgeAccessor, edgeDrawableId, colour);
        }
    }

}
