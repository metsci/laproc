package com.metsci.laproc.pointmetrics;

/**
 * Computes some metric for this point based on the number of true positives, false positives, true negatives,
 * and false negatives.
 * Created by robinsat on 10/25/2016.
 */
public interface Metric extends ParametricFunction<ClassifierSetPoint> {

    /**
     * Computes some metric for this point based on the number of true positives, false positives, true negatives,
     * and false negatives.
     * @param point The point for which to calculate a metric
     * @return The calculated value
     */
    double compute(ClassifierSetPoint point);

}
