package com.metsci.laproc.plotting;

/**
 * Computes the standard deviation of a set of points
 * Created by robinsat on 2/8/2017.
 */
public class StandardDeviationMetric extends VarianceMetric {

    /**
     * Calculates the output based on the input
     * @param point The input for the function
     * @return The output for the function
     */
    public double compute(CompositePoint point) {
        double variance = super.compute(point);
        return Math.sqrt(variance);
    }

    /**
     * Gets a string descriptor of what this metric represents
     * @return A String descriptor of what this metric represents
     */
    public String getDescriptor() {
        return "Standard Deviation";
    }
}
