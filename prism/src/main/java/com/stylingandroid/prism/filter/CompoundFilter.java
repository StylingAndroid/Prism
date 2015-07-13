package com.stylingandroid.prism.filter;

public class CompoundFilter<INTERMEDIATE> implements Filter {
    private final GenericFilter<Integer, INTERMEDIATE> firstFilter;
    private final GenericFilter<INTERMEDIATE, Integer> secondFilter;

    public CompoundFilter(GenericFilter<Integer, INTERMEDIATE> firstFilter, GenericFilter<INTERMEDIATE, Integer> secondFilter) {
        this.firstFilter = firstFilter;
        this.secondFilter = secondFilter;
    }

    @Override
    public Integer filter(Integer colour) {
        INTERMEDIATE intermediate = firstFilter.filter(colour);
        return secondFilter.filter(intermediate);
    }
}
