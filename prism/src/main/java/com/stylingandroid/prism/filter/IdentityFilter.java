package com.stylingandroid.prism.filter;

public class IdentityFilter extends BaseColourFilter {
    @Override
    public int onFilter(int colour) {
        return colour;
    }
}
