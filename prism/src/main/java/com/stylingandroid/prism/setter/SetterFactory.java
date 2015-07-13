package com.stylingandroid.prism.setter;

import android.view.View;

import com.stylingandroid.prism.Setter;
import com.stylingandroid.prism.filter.Filter;

public interface SetterFactory {
    Setter getColourSetter(View view, Filter filter);
}
