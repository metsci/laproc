package com.metsci.laproc.plotting;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A basic implementation of a graph point
 * Created by robinsat on 10/25/2016.
 */
public class BasicGraphPoint implements GraphPoint {

    /** The x value */
    private double xVal;
    /** The y value */
    private double yVal;
    /** A map representing additional statistics associated with this point */
    private Map<String, Double> analytics;

    /**
     * Constructor
     * @param x The x value
     * @param y The y value
     */
    public BasicGraphPoint(double x, double y) {
        this.xVal = x;
        this.yVal = y;
        this.analytics = new LinkedHashMap<String, Double>();
    }

    /**
     * Getter for the x value
     * @return The x value
     */
    public double getX() {
        return this.xVal;
    }

    /**
     * Getter for the y value
     * @return The y value
     */
    public double getY() {
        return this.yVal;
    }

    /**
     * Gets a map representing any additional data associated with this point
     * @return A map representing any additional data associated with this point
     */
    public Map<String, Double> getAnalytics() {
        return Collections.unmodifiableMap(this.analytics);
    }

    /**
     * A package-local method to add statistics to associate with this graph point
     * @param key The key, which is a string descriptor
     * @param value The value of the statistic
     */
    public void addStatistic(String key, Double value) {
        this.analytics.put(key, value);
    }
}
