package com.metsci.laproc.pointmetrics;

/**
 * Computes the true negative rate at a given point
 * Created by robinsat on 10/25/2016.
 */
public class TrueNegativeRate implements Metric {

    /**
     * Computes the true negative rate for a given point
     * @param point The point for which to calculate the true negative rate
     * @return The true negative rate
     */
    public double compute(ClassifierSetPoint point) {
        return ((double) point.getTrueNegatives()) / point.getNumNegatives();
    }

    /**
     * Gets a string descriptor of what this metric represents
     * True Negative Rate, aka Specificity
     * @return A String descriptor of what this metric represents
     */
    public String getDescriptor() {
        return "True Negative Rate";
    }
}
