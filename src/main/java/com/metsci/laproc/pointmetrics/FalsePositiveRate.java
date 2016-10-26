package com.metsci.laproc.pointmetrics;

/**
 * Created by robinsat on 10/25/2016.
 */
public class FalsePositiveRate implements Metric {
    public double getMetric(ClassifierSetPoint point) {
        return ((double) point.getFalsePositives()) / point.getNumNegatives();
    }

    public String getDescriptor() {
        return "False Positive Rate";
    }
}
