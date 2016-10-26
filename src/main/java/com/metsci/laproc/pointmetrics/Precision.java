package com.metsci.laproc.pointmetrics;

/**
 * Computes the precision for a given point
 * Created by robinsat on 10/25/2016.
 */
public class Precision implements Metric {

    /**
     * Computes the precision for a given point
     * @param point The point for which to calculate the precisioin
     * @return The precision
     */
    public double getMetric(ClassifierSetPoint point) {
        return ((double) point.getTruePositives()) / (point.getTruePositives() + point.getFalsePositives());
    }

    /**
     * Gets a string descriptor of what this metric represents
     * Precision, aka Positive Predictive Value
     * @return A String descriptor of what this metric represents
     */
    public String getDescriptor() {
        return "Precision";
    }
}
