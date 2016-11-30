package com.metsci.laproc.pointmetrics;

import com.metsci.laproc.plotting.ROCCurve;

/**
 * Represents the number of false positives for a given point
 * Created by robinsat on 10/25/2016.
 */
public class FalsePositives implements Metric{

    /**
     * Finds the number of false positives for a given point
     * @param point The point for which to find the false positives
     * @return The number of false positives
     */
    public double compute(ClassifierSetPoint point) {
        return point.getFalsePositives();
    }

    /**
     * Gets a string descriptor of what this metric represents
     * @return A String descriptor of what this metric represents
     */
    public String getDescriptor() {
        return ROCCurve.fpString;
    }
}
