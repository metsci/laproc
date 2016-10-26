package com.metsci.laproc.pointmetrics;

/**
 *
 * Created by robinsat on 10/25/2016.
 */
public class F1Score implements Metric {
    public double getMetric(ClassifierSetPoint point) {
        double denominator = (2 * point.getTruePositives()) + point.getFalsePositives() + point.getFalseNegatives();
        return (2.0 * point.getTruePositives()) / denominator;
    }

    public String getDescriptor() {
        return "F1 Score";
    }
}
