package com.stylingandroid.prism.filter;

public class CompoundColourFilter<INTERMEDIATE> implements ColourFilter {
    private final GenericColourFilter<Integer, INTERMEDIATE> firstFilter;
    private final GenericColourFilter<INTERMEDIATE, Integer> secondFilter;

    public CompoundColourFilter(GenericColourFilter<Integer, INTERMEDIATE> firstFilter, GenericColourFilter<INTERMEDIATE, Integer> secondFilter) {
        this.firstFilter = firstFilter;
        this.secondFilter = secondFilter;
    }

    @Override
    public Integer filter(Integer colour) {
        INTERMEDIATE intermediate = firstFilter.filter(colour);
        return secondFilter.filter(intermediate);
    }
}
