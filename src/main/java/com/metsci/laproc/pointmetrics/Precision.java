package com.metsci.laproc.pointmetrics;

/**
 * Created by robinsat on 10/25/2016.
 */
public class Precision implements Metric {
    public double getMetric(ClassifierSetPoint point) {
        return ((double) point.getTruePositives()) / (point.getTruePositives() + point.getFalsePositives());
    }

    public String getDescriptor() {
        return "Precision";
    }
}
