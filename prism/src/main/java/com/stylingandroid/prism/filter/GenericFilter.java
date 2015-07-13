package com.stylingandroid.prism.filter;

public interface GenericFilter<INPUT, OUTPUT> {
    OUTPUT filter(INPUT colour);
}
