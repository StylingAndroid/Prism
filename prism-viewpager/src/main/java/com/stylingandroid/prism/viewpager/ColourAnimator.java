package com.stylingandroid.prism.viewpager;

import com.stylingandroid.prism.viewpager.compat.ArgbEvaluatorCompat;

class ColourAnimator {
    private static final ArgbEvaluatorCompat ARGB_EVALUATOR = ArgbEvaluatorCompat.newInstance();
    private final int startColour;
    private final int startPosition;
    private final int endColour;
    private final int endPosition;
    private final int range;

    public static ColourAnimator newInstance(int startPosition, int endPosition, ColourProvider colourProvider) {
        int startPos = Math.min(startPosition, endPosition);
        int endPos = Math.max(startPosition, endPosition);
        int startColour = colourProvider.getColour(startPos);
        int endColour = colourProvider.getColour(endPos);
        int range = endPos - startPos;
        return new ColourAnimator(startPos, startColour, endPos, endColour, range);
    }

    ColourAnimator(int startPosition, int startColour, int endPosition, int endColour, int range) {
        this.startPosition = Math.min(startPosition, endPosition);
        this.endPosition = Math.max(startPosition, endPosition);
        this.startColour = startColour;
        this.endColour = endColour;
        this.range = range;
    }

    @SuppressWarnings("PMD.UselessParentheses")
    public int getColourFor(int position, float positionOffset) {
        float offset;
        if (range > 1) {
            offset = ((float) (position - startPosition) / range) + (positionOffset / (float) range);
        } else if (position == endPosition && positionOffset == 0f) {
            offset = 1f;
        } else {
            offset = positionOffset;
        }

        return (int) ARGB_EVALUATOR.evaluate(offset, startColour, endColour);
    }

    @SuppressWarnings("PMD.UselessParentheses")
    public boolean isOutsideRange(int position, float offset) {
        return position < startPosition || (position == endPosition && offset > 0f) || position > endPosition;
    }
}
