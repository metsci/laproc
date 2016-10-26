package com.metsci.laproc.pointmetrics;

/**
 * Created by robinsat on 10/25/2016.
 */
public class FalseNegativeRate implements Metric {
    public double getMetric(ClassifierSetPoint point) {
        return ((double) point.getFalseNegatives() / point.getNumPositives());
    }

    public String getDescriptor() {
        return "False Negative Rate";
    }
}
