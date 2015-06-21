package com.stylingandroid.prism.setter;

import android.view.View;

import com.stylingandroid.prism.ColourSetter;
import com.stylingandroid.prism.filter.ColourFilter;

public interface SetterFactory {
    ColourSetter getBackgroundSetter(View view, ColourFilter filter);
}
