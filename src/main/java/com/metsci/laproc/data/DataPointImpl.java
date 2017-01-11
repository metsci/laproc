package com.metsci.laproc.data;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The implementation of a data point from the input
 * Created by robinsat on 9/20/2016.
 */
public class DataPointImpl implements DataPoint {

    /** The truth value of this data point */
    private boolean truth;
    /** The value(s) of this data point */
    private double[] values;

    /**
     * Constructor
     * @param truth The truth value for this data point
     * @param vals The values associated with this data point
     */
    public DataPointImpl(boolean truth, double... vals) {
        this.truth = truth;
        this.values = vals;
    }

    /**
     * Getter for the truth value for this data point
     * @return The truth value
     */
    public boolean getTruth() {
        return this.truth;
    }

    /**
     * Getter for the set of values associated with this datum
     * @return The set of values associated with this datum
     */
    public double[] getValues() {
        return this.values;
    }
}
