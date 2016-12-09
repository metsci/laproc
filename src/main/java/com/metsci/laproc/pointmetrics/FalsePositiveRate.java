package com.metsci.laproc.pointmetrics;

/**
 * Computes the false positive rate for the given point
 * Created by robinsat on 10/25/2016.
 */
public class FalsePositiveRate implements Metric {

    /**
     * Computes the false positive rate for a given point
     * @param point The point for which to calculate the false positive rate
     * @return The false positive rate
     */
    public double compute(ClassifierSetPoint point) {
        return ((double) point.getFalsePositives()) / point.getNumNegatives();
    }

    /**
     * Gets a string descriptor of what this metric represents
     * False Positive Rate, aka fall-out
     * @return A String descriptor of what this metric represents
     */
    public String getDescriptor() {
        return "False Positive Rate";
    }
}
