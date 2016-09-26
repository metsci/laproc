package com.metsci.laproc.plotting;

import java.util.Collections;
import java.util.Map;

/**
 * THIS CLASS WILL PROBABLY BE DELETED
 * A class representing a single point on the graph
 * Created by robinsat on 9/20/2016.
 */
public class GraphPoint {

    /** The x value of this point */
    private double x;
    /** The y value of this point */
    private double y;
    /** Contains all metadata to be displayed when the user hovers over this point */
    private Map<String, Double> metadata;

    /**
     * Constructor
     * @param x The x value of this point
     * @param y The y value of this point
     */
    public GraphPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Adds a value to the map of information for this point
     * @param key
     * @param value
     */
    public void putValue(String key, Double value) {
        this.metadata.put(key, value);
    }

    /**
     * Getter for the X value of this point
     * @return The X value
     */
    public double getX() {
        return this.x;
    }

    /**
     * Getter for the Y value of this point
     * @return The Y value
     */
    public double getY() {
        return this.y;
    }

    /**
     * Gets the data value from the map corresponding to the string key
     * @param key The key to find in the map
     * @return The corresponding value in the map
     */
    public Double getValue(String key) {
        return this.metadata.get(key);
    }

    /**
     * Gets all of the metadata associated with this point
     * @return All of the metadata associated with this point
     */
    public Map<String, Double> getPointData() {
        return Collections.unmodifiableMap(this.metadata);
    }
}
