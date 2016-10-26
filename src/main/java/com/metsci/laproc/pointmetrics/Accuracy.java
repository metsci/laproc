package com.metsci.laproc.pointmetrics;

/**
 * Created by robinsat on 10/25/2016.
 */
public class Accuracy implements Metric {
    public double getMetric(ClassifierSetPoint point) {
        return ((double) point.getTruePositives() + point.getTrueNegatives()) /
                (point.getNumPositives() + point.getNumNegatives());
    }

    public String getDescriptor() {
        return "Accuracy";
    }
}
