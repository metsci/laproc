package com.metsci.laproc.pointmetrics;

/**
 * A metric to calculate the true positive rate
 * Created by robinsat on 10/25/2016.
 */
public class TruePositiveRate implements Metric {

    /**
     * Calculates the true positive rate based on the given point
     * @param point A point for which to find the true positive rate
     * @return The true positive rate
     */
    public double getMetric(ClassifierSetPoint point) {
        return ((double) point.getTruePositives()) / point.getNumPositives();
    }

    /**
     * Gets a string descriptor of what this metric represents
     * True Positive Rate, aka Sensitivity
     * @return A String descriptor of what this metric represents
     */
    public String getDescriptor() {
        return "True Positive Rate";
    }
}
