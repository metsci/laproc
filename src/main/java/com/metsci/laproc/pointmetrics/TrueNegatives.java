package com.metsci.laproc.pointmetrics;

import com.metsci.laproc.plotting.ROCCurve;

/**
 * Represents the number of true negatives for a given point
 * Created by robinsat on 10/25/2016.
 */
public class TrueNegatives implements Metric{

    /**
     * Represents the number of true negatives for a given point
     * @param point The point for which to find the true negatives
     * @return The number of true negatives
     */
    public double getMetric(ClassifierSetPoint point) {
        return point.getTrueNegatives();
    }

    /**
     * Gets a string descriptor of what this metric represents
     * @return A String descriptor of what this metric represents
     */
    public String getDescriptor() {
        return ROCCurve.tnString;
    }
}
