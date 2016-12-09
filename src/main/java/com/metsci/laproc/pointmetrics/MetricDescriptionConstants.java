package com.metsci.laproc.pointmetrics;

/**
 * Stores some static constants for metric descriptors
 * This is useful for displaying a confusion matrix, which search for metrics by very specific keys
 * Created by robinsat on 12/9/2016.
 */
public interface MetricDescriptionConstants {
    String truePositives = "True Positives";
    String falsePositives = "False Positives";
    String trueNegatives = "True Negatives";
    String falseNegatives = "False Negatives";
}
