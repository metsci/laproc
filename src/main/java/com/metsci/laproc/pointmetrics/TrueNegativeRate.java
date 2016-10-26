package com.metsci.laproc.pointmetrics;

/**
 * Created by robinsat on 10/25/2016.
 */
public class TrueNegativeRate implements Metric {
    public double getMetric(ClassifierSetPoint point) {
        return ((double) point.getTrueNegatives()) / point.getNumNegatives();
    }

    public String getDescriptor() {
        return "True Negative Rate";
    }
}
