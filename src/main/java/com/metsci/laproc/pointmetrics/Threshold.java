package com.metsci.laproc.pointmetrics;

/**
 * Created by robinsat on 10/25/2016.
 */
public class Threshold implements Metric {
    public double getMetric(ClassifierSetPoint point) {
        return point.getThreshold();
    }

    public String getDescriptor() {
        return "Threshold";
    }
}
