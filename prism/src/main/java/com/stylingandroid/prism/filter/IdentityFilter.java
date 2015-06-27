package com.stylingandroid.prism.filter;

public class IdentityFilter extends RepeatableColourFilter {
    @Override
    public Integer onFilter(Integer colour) {
        return colour;
    }
}
