package com.metsci.laproc.pointmetrics;

import com.metsci.laproc.plotting.ROCCurve;

/**
 * Represents the number of true positives for the given point
 * Created by robinsat on 10/25/2016.
 */
public class TruePositives implements Metric{

    /**
     * Represents the number of true positives for the given point
     * @param point The point for which to find the true positives
     * @return The number of true positives
     */
    public double compute(ClassifierSetPoint point) {
        return point.getTruePositives();
    }

    /**
     * Gets a string descriptor of what this metric represents
     * @return A String descriptor of what this metric represents
     */
    public String getDescriptor() {
        return ROCCurve.tpString;
    }
}
