package com.metsci.laproc.plotting;

import java.util.Collection;

/**
 * Represents a set of data points to be plotted on a graph
 * Created by robinsat on 9/20/2016.
 */
public interface GraphableData {

    /**
     * Getter for the name of this data set
     * @return the name of this data set
     */
    public String getName();

    /**
     * Setter for the name of this data set
     * @param name the name to set for this data set
     */
    public void setName(String name);

    /**
     * Returns a set of points with double precision
     * @return The set of the points
     */
    Collection<GraphPoint> getPoints();
}
