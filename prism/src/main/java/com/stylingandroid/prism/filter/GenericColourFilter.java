package com.stylingandroid.prism.filter;

public interface GenericColourFilter<INPUT, OUTPUT> {
    OUTPUT filter(INPUT colour);
}
