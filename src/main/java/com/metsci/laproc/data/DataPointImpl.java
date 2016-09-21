package com.metsci.laproc.data;

import java.util.Collection;

/**
 * The implementation of a data point from the input
 * Created by robinsat on 9/20/2016.
 */
public class DataPointImpl implements DataPoint {

    private boolean truth;
    private double[] values;
    private Collection<String> tags;

    public boolean getTruth() {
        return this.truth;
    }

    public double[] getValues() {
        return this.values;
    }

    public Collection<String> getTags() {
        return this.tags;
    }
}
