package com.stylingandroid.prism.filter;

public class IdentityFilter extends RepeatableFilter {
    @Override
    public Integer onFilter(Integer colour) {
        return colour;
    }
}
