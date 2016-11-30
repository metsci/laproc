package com.metsci.laproc.pointmetrics;

/**
 * Computes the false negative rate for a given point
 * Created by robinsat on 10/25/2016.
 */
public class FalseNegativeRate implements Metric {

    /**
     * Computes the false negative rate for a given point
     * @param point The point for which to calculate the false negative rate
     * @return The false negative rate
     */
    public double compute(ClassifierSetPoint point) {
        return ((double) point.getFalseNegatives() / point.getNumPositives());
    }

    /**
     * Gets a string descriptor of what this metric represents
     * False negative rate, aka miss rate, aka FNR
     * @return A String descriptor of what this metric represents
     */
    public String getDescriptor() {
        return "False Negative Rate";
    }
}
