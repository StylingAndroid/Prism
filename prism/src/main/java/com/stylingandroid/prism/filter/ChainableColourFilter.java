package com.stylingandroid.prism.filter;

import android.support.annotation.NonNull;

public abstract class ChainableColourFilter<INPUT, OUTPUT> implements GenericColourFilter<INPUT, Integer> {
    private ChainableColourFilter<OUTPUT, Integer> nextFilter = null;

    @Override
    public Integer filter(INPUT colour) {
        OUTPUT output = onFilter(colour);
        if (nextFilter != null) {
            return nextFilter.filter(output);
        } else if (output instanceof Integer) {
            return (Integer) output;
        }
        return 0;
    }

    protected abstract OUTPUT onFilter(INPUT colour);

    public void setNextFilter(@NonNull ChainableColourFilter<OUTPUT, Integer> nextFilter) {
        this.nextFilter = nextFilter;
    }
}
