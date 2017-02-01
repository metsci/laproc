package com.metsci.laproc.plotting;

/**
 * Finds the vertical average of a set of curves
 * Created by robinsat on 12/13/2016.
 */

public class VerticalAverageFunction extends CompositeFunction {

    /** Constructor */
    public VerticalAverageFunction() {
        super();
        this.setName("Vertical Average");
    }

    /**
     * Computes the vertical average based on a set of y values at some point x
     * @param yValues A set of y values representing several function outputs for one x
     * @return The average of the y values
     */
    protected double computeFromYValues(double[] yValues) {
        return this.calculateAverage(yValues);
    }
}
