package com.metsci.laproc.pointmetrics;

/**
 * Represents the threshold for a point of data
 * Created by robinsat on 10/25/2016.
 */
public class Threshold implements Metric {

    /**
     * Represents the threshold for a point of data
     * @param point The point for which to find the threshold
     * @return The threshold
     */
    public double compute(ClassifierSetPoint point) {
        return point.getThreshold();
    }

    /**
     * Gets a string descriptor of what this metric represents
     * Threshold, aka cutpoint
     * @return A String descriptor of what this metric represents
     */
    public String getDescriptor() {
        return "Threshold";
    }
}
