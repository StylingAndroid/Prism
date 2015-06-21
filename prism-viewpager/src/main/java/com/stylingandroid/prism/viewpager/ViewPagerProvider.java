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
        if (colourAnimator == null) {
            int endPosition = position == currentPosition ? position + 1 : position;
            currentPosition = position == currentPosition ? position : position + 1;
            colourAnimator = createColourAnimator(currentPosition, endPosition);
        }
        if (positionOffset == 0 && currentPosition != position) {
            currentPosition = position;
        }
        int newColour = colourAnimator.getColourFor(position, positionOffset);
        setColour(newColour);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == ViewPager.SCROLL_STATE_IDLE && colourAnimator != null && colourAnimator.isAtEndPosition(currentPosition)) {
            setColour(colourAnimator.getEndColour());
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
