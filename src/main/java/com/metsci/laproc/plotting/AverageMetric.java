package com.metsci.laproc.plotting;

import com.metsci.laproc.pointmetrics.ParametricFunction;

/**
 * Calculates the vertical average of a set of points
 * Created by robinsat on 2/8/2017.
 */
public class AverageMetric implements ParametricFunction<CompositePoint> {

    /**
     * Calculates the output based on the input
     * @param point The input for the function
     * @return The output for the function
     */
    public double compute(CompositePoint point) {
        double[] values = point.getYValues();
        double sum = 0;
        for(int i = 0; i < values.length; i++) {
            sum += values[i];
        }
        return sum / values.length;
    }

    /**
     * Gets a string descriptor of what this metric represents
     * @return A String descriptor of what this metric represents
     */
    public String getDescriptor() {
        return "Vertical Average";
    }
}
