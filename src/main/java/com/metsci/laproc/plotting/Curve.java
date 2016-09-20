package com.metsci.laproc.plotting;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

/**
 * Represents a continuous line that can be represented on a graph.
 * Created by robinsat on 9/20/2016.
 */
public class Curve implements GraphableData {

    /** The name of this set of data */
    private String name;
    /** All of the points in this set of data */
    private Set<GraphPoint> data;

    /**
     * Constructor
     * @param name The name to give to this graph data
     */
    public Curve(String name) {
        this.name = name;
        this.data = new TreeSet<GraphPoint>();
    }

    /**
     * Getter for the name of this graph data
     * @return The name of this data
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the name of this data set
     * @param name The name to set for this data set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns a set of points with double precision
     * @return This set of data points
     */
    public Collection<GraphPoint> getPoints() {
        return Collections.unmodifiableSet(data);
    }
}
