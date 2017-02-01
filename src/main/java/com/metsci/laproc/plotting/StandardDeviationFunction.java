package com.metsci.laproc.plotting;

/**
 * Computes the standard deviation of a set of points
 * Created by robinsat on 12/13/2016.
 */
public class StandardDeviationFunction extends VarianceFunction {

    /** Constructor */
    public StandardDeviationFunction() {
        super();
        this.setName("Standard Deviation");
    }

    /**
     * Uses the y values for several data sets at a given x to compute the standard deviation
     * @param yValues The y values for several data sets at a given x
     * @return The standard deviation
     */
    protected double computeFromYValues(double[] yValues) {
        double variance = super.computeFromYValues(yValues);
        return Math.sqrt(variance);
    }

}
