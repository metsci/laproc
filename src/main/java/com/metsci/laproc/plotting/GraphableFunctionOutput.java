package com.metsci.laproc.plotting;

import java.util.Collection;
import java.util.Set;

/**
 * Interface represents the output of a GraphableFunction. It collects GraphPoints, and outputs instances implementing
 * the GraphableData interface
 * Created by robinsat on 9/30/2016.
 */
public interface GraphableFunctionOutput extends Set<GraphPoint> {

    /**
     * Returns an instance implementing the GraphableData interface
     * @param xAxis The x axis on which to plot the output data
     * @param yAxis The y axis on which to plot the output data
     * @return The GraphableData using the given x and y axes
     */
    public GraphableData getGraphableData(String xAxis, String yAxis);

    /**
     * Adds an attribute to the set of attributes for this data set
     * @param key The attribute to add, this must be a unique key
     */
    public void addAttribute(String key);

    /**
     * Gets the set of attributes for this data set
     * @return The set of attributes for this data set
     */
    public Set<String> getAttributes();

}
