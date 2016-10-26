package com.metsci.laproc.pointmetrics;

/**
 * Calculates the accuracy of the classifier at the current threshold
 * Created by robinsat on 10/25/2016.
 */
public class Accuracy implements Metric {

    /**
     * Calculates the accuracy of the classifier at the threshold of the given point
     * @param point The point at which to calculate the accuracy
     * @return The accuracy
     */
    public double getMetric(ClassifierSetPoint point) {
        return ((double) point.getTruePositives() + point.getTrueNegatives()) /
                (point.getNumPositives() + point.getNumNegatives());
    }

    /**
     * Gets a string descriptor of what this metric represents
     * @return A String descriptor of what this metric represents
     */
    public String getDescriptor() {
        return "Accuracy";
    }
}
