package com.stylingandroid.prism.filter;

interface GenericColourFilter<INPUT, OUTPUT> {
    OUTPUT filter(INPUT colour);
}
