package com.metsci.laproc.data;

import java.util.Collection;

/**
 * Interface representing a single point of data input to this application
 * Created by robinsat on 9/20/2016.
 */
public interface DataPoint {

    /**
     * Getter for the truth value for this data point
     * @return The truth value
     */
    public boolean getTruth();

    /**
     * Getter for the set of values associated with this datum
     * @return The set of values associated with this datum
     */
    public double[] getValues();

    /**
     * Getter for the set of tags associated with this datum
     * @return The set of tags associated with this datum
     */
    public Collection<String> getTags();

    /**
     * Adds a tag to the set of tags associated with this datum
     * @param tag The tag to associate with this datum
     */
    public void addTag(String tag);
}
