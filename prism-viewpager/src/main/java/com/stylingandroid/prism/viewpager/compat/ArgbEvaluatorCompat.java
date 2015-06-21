package com.stylingandroid.prism.viewpager.compat;

import android.animation.ArgbEvaluator;
import android.annotation.TargetApi;
import android.os.Build;

public abstract class ArgbEvaluatorCompat {
    public static ArgbEvaluatorCompat newInstance() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return new ArgbEvaluatorHoneycomb();
        }
        return new ArgbEvaluatorLegacy();
    }
    public abstract Object evaluate(float fraction, Object startValue, Object endValue);

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private static class ArgbEvaluatorHoneycomb extends ArgbEvaluatorCompat {
        private final ArgbEvaluator argbEvaluator = new ArgbEvaluator();

        @Override
        public Object evaluate(float fraction, Object startValue, Object endValue) {
            return argbEvaluator.evaluate(fraction, startValue, endValue);
        }
    }

    private static class ArgbEvaluatorLegacy extends ArgbEvaluatorCompat {
        private static final int EIGHT_BITS = 8;
        private static final int SIXTEEN_BITS = 16;
        private static final int TWENTY_FOUR_BITS = 24;
        private static final int ALL_BITS = 0xff;

        @Override
        @SuppressWarnings("PMD.UselessParentheses")
        public Object evaluate(float fraction, Object startValue, Object endValue) {
            int startInt = (Integer) startValue;
            int startA = (startInt >> TWENTY_FOUR_BITS) & ALL_BITS;
            int startR = (startInt >> SIXTEEN_BITS) & ALL_BITS;
            int startG = (startInt >> EIGHT_BITS) & ALL_BITS;
            int startB = startInt & ALL_BITS;
            int endInt = (Integer) endValue;
            int endA = (endInt >> TWENTY_FOUR_BITS) & ALL_BITS;
            int endR = (endInt >> SIXTEEN_BITS) & ALL_BITS;
            int endG = (endInt >> EIGHT_BITS) & ALL_BITS;
            int endB = endInt & ALL_BITS;
            return ((startA + (int) (fraction * (endA - startA))) << TWENTY_FOUR_BITS)
                    | ((startR + (int) (fraction * (endR - startR))) << SIXTEEN_BITS)
                    | ((startG + (int) (fraction * (endG - startG))) << EIGHT_BITS)
                    | ((startB + (int) (fraction * (endB - startB))));
        }
    }
}
