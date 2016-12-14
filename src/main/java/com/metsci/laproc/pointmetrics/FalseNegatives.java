package com.metsci.laproc.pointmetrics;

/**
 * Calculates the number of false negatives for a given point of data
 * Created by robinsat on 10/25/2016.
 */
public class FalseNegatives implements Metric {

    /**
     * Finds the number of false negatives for a given point
     * @param point The point for which to find the false negatives
     * @return The number of false negatives
     */
    public double compute(ClassifierSetPoint point) {
        return point.getFalseNegatives();
    }

    /**
     * Gets a string descriptor of what this metric represents
     * @return A String descriptor of what this metric represents
     */
    public String getDescriptor() {
        return MetricDescriptionConstants.falseNegatives;
    }
}
