package com.stylingandroid.prism.filter;

public interface ColourFilter<INPUT, OUTPUT> {
    OUTPUT filter(INPUT colour);
}
