package com.stylingandroid.prism.viewpager;

import android.support.v4.view.ViewPager;

import com.stylingandroid.prism.BaseColourChangeTrigger;
import com.stylingandroid.prism.setter.ViewPagerColourSetterFactory;

public class ViewPagerProvider extends BaseColourChangeTrigger implements ViewPager.OnPageChangeListener {
    private final ColourProvider colourProvider;
    private ColourAnimator colourAnimator;
    private int currentPosition;

    public static ViewPagerProvider newInstance(ViewPager viewPager, ColourProvider colourProvider) {
        ViewPagerColourSetterFactory.initialise();
        ViewPagerProvider viewPagerProvider = new ViewPagerProvider(colourProvider);
        viewPager.addOnPageChangeListener(viewPagerProvider);
        viewPagerProvider.onPageSelected(0);
        return viewPagerProvider;
    }

    public ViewPagerProvider(ColourProvider colourProvider) {
        this.colourProvider = colourProvider;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (colourAnimator != null && colourAnimator.isOutsideRange(position)) {
            colourAnimator = null;
        }
        if (colourAnimator == null) {
            int endPosition;
            if (isScrollingRight(position)) {
                currentPosition = position;
                endPosition = Math.min(colourProvider.getCount() - 1, position + 1);
            } else {
                currentPosition = Math.min(colourProvider.getCount() - 1, position + 1);
                endPosition = position;
            }
            colourAnimator = createColourAnimator(currentPosition, endPosition);
        }
        int newColour = colourAnimator.getColourFor(position, positionOffset);
        if (positionOffset == 0 && currentPosition != position) {
            colourAnimator = null;
            newColour = colourProvider.getColour(position);
        }
        setColour(newColour);
    }

    private boolean isScrollingRight(int position) {
        return position == currentPosition;
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == ViewPager.SCROLL_STATE_IDLE && colourAnimator != null) {
            colourAnimator = null;
        }
    }

    @Override
    public void onPageSelected(int position) {
        colourAnimator = createColourAnimator(currentPosition, position);
        currentPosition = position;
    }

    private ColourAnimator createColourAnimator(int startPosition, int endPosition) {
        return ColourAnimator.newInstance(startPosition, endPosition, colourProvider);
    }
}
