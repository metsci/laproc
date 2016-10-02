package com.metsci.laproc.plotting;

import it.unimi.dsi.fastutil.objects.ObjectArraySet;

import java.util.*;

/**
 * An implementation of the GraphableFunctionOutput interface. Constructs GraphableData.
 * Created by robinsat on 9/30/2016.
 */
public class GraphableFunctionOutputImpl extends ObjectArraySet<GraphPoint> implements GraphableFunctionOutput {

    /** The set of attributes for this data set */
    private Set<String> attributes;

    /**
     * Constructor, currently just needs to initialize attribute set
     */
    public GraphableFunctionOutputImpl() {
        this.attributes = new HashSet<String>();
    }

    /**
     * Returns an instance implementing the GraphableData interface
     * @param xAxis The x axis on which to plot the output data
     * @param yAxis The y axis on which to plot the output data
     * @return The GraphableData using the given x and y axes
     */
    public GraphableData getGraphableData(String xAxis, String yAxis) {
        GraphableData data = new Curve();
        for(GraphPoint p : this) {
            double x = p.get(xAxis);
            double y = p.get(yAxis);
            data.addPoint(x, y);
        }
        return data;
    }

    /**
     * Adds an attribute to the set of attributes for this data set
     * @param key The attribute to add, this must be a unique key
     */
    public void addAttribute(String key) {
        this.attributes.add(key);
    }

    /**
     * Gets the set of attributes for this data set
     * @return The set of attributes for this data set
     */
    public Set<String> getAttributes() {
        return this.attributes;
    }
}
