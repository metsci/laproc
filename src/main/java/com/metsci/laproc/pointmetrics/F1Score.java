package com.metsci.laproc.pointmetrics;

/**
 * A metric to calculate the F1 score for a given point
 * Created by robinsat on 10/25/2016.
 */
public class F1Score implements Metric {

    /**
     * Computes the F1 score for a given point
     * @param point The point for which to calculate the F1 score
     * @return The F1 score
     */
    public double compute(ClassifierSetPoint point) {
        double denominator = (2 * point.getTruePositives()) + point.getFalsePositives() + point.getFalseNegatives();
        return (2.0 * point.getTruePositives()) / denominator;
    }

    /**
     * Gets a string descriptor of what this metric represents
     * The F1 score, which is the harmonic mean of precision and sensitivity
     * @return A String descriptor of what this metric represents
     */
    public String getDescriptor() {
        return "F1 Score";
    }
}
