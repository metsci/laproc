package com.metsci.laproc.plotting;

/**
 * A point representing some x value and the set of y values that correspond to each x.
 * Created by robinsat on 2/8/2017.
 */
public class CompositePoint {

    /** The x value */
    private double xValue;
    /** The y value */
    private double[] yValues;

    /**
     * Constructor
     * @param xValue The x value
     * @param yValues The y value
     */
    public CompositePoint(double xValue, double[] yValues) {
        this.xValue = xValue;
        this.yValues = yValues;
    }

    /**
     * Getter for the X value
     * @return The x value
     */
    public double getXValue() {
        return this.xValue;
    }

    /**
     * Getter for the Y values
     * @return The y values
     */
    public double[] getYValues() {
        return this.yValues;
    }

}
