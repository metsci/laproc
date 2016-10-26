package com.metsci.laproc.pointmetrics;

/**
 * Computes some metric for this point based on the number of true positives, false positives, true negatives,
 * and false negatives.
 * Created by robinsat on 10/25/2016.
 */
public interface Metric {

    /**
     * Computes some metric for this point based on the number of true positives, false positives, true negatives,
     * and false negatives.
     * @param point The point for which to calculate a metric
     * @return The calculated value
     */
    double getMetric(ClassifierSetPoint point);

    /**
     * Gets a string descriptor of what this metric represents
     * @return A String descriptor of what this metric represents
     */
    String getDescriptor();


}
