package com.metsci.laproc.pointmetrics;

/**
 * Created by robinsat on 10/25/2016.
 */
public class TruePositiveRate implements Metric {
    public double getMetric(ClassifierSetPoint point) {
        return ((double) point.getTruePositives()) / point.getNumPositives();
    }

    public String getDescriptor() {
        return "True Positive Rate";
    }
}
