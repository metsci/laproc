package com.metsci.laproc.plotting;

/**
 * Calculates the variance of a set of points
 * Created by robinsat on 2/8/2017.
 */
public class VarianceMetric extends AverageMetric {

    /**
     * Calculates the output based on the input
     * @param point The input for the function
     * @return The output for the function
     */
    public double compute(CompositePoint point) {
        double average = super.compute(point);
        //Compute the summation needed to find the variance
        double[] values = point.getYValues();
        double sum = 0;
        for(int i = 0; i < values.length; i++) {
            sum += Math.pow(values[i] - average, 2);
        }
        return sum / values.length;
    }

    /**
     * Gets a string descriptor of what this metric represents
     * @return A String descriptor of what this metric represents
     */
    public String getDescriptor() {
        return "Variance";
    }
}
