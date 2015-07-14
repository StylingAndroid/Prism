package com.stylingandroid.prism.viewpager;

import android.support.v4.view.ViewPager;

import com.stylingandroid.prism.BaseTrigger;
import com.stylingandroid.prism.Setter;
import com.stylingandroid.prism.setter.ViewPagerSetterFactory;

public class ViewPagerTrigger extends BaseTrigger implements ViewPager.OnPageChangeListener {
    private final ColourProvider colourProvider;
    private ColourAnimator colourAnimator;
    private int currentPosition;

    public static ViewPagerTrigger newInstance(ViewPager viewPager, ColorProvider colorProvider) {
        ColourProvider colourProvider = new ColorProviderAdapter(colorProvider);
        return newInstance(viewPager, colourProvider);
    }

    public static ViewPagerTrigger newInstance(ViewPager viewPager, ColourProvider colourProvider) {
        ViewPagerTrigger viewPagerTrigger = new ViewPagerTrigger(colourProvider);
        viewPager.addOnPageChangeListener(viewPagerTrigger);
        viewPagerTrigger.onPageSelected(0);
        ViewPagerSetterFactory.register();
        return viewPagerTrigger;
    }

    ViewPagerTrigger(ColourProvider colourProvider) {
        this.colourProvider = colourProvider;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        boolean transientChange = true;
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
            transientChange = false;
        }
        setColour(newColour, transientChange);
    }

    private boolean isScrollingRight(int position) {
        return position == currentPosition;
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == ViewPager.SCROLL_STATE_IDLE && colourAnimator != null) {
            int newColour = colourProvider.getColour(currentPosition);
            setColour(newColour, false);
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

    @Override
    public void addSetter(Setter setter) {
        boolean shouldInitialise = hasNoColourSetters();
        super.addSetter(setter);
        if (shouldInitialise) {
            ViewPagerSetterFactory.register();
            int newColour = colourProvider.getColour(currentPosition);
            setColour(newColour, false);
        }
    }

    @Override
    public void removeSetter(Setter setter) {
        super.removeSetter(setter);
        if (hasNoColourSetters()) {
            ViewPagerSetterFactory.unregister();
        }
    }
}
