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
    /** All tags associated with this data point */
    private Collection<String> tags;

    /**
     * Constructor
     * @param truth The truth value for this data point
     * @param vals The values associated with this data point
     */
    public DataPointImpl(boolean truth, double... vals) {
        this.truth = truth;
        this.values = vals;
        this.tags = new ArrayList<String>();
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

    /**
     * Getter for the set of tags associated with this datum
     * @return The set of tags associated with this datum
     */
    public Collection<String> getTags() {
        return this.tags;
    }

    /**
     * Adds a tag to the set of tags associated with this datum
     * @param tag The tag to associate with this datum
     */
    public void addTag(String tag) {
        this.tags.add(tag);
    }
}
