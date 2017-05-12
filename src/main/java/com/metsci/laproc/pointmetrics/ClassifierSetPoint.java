package com.metsci.laproc.pointmetrics;

/**
 * A data class that stores the classifier data for a single point on a curve
 * Created by robinsat on 10/25/2016.
 */
public class ClassifierSetPoint {

    /** The threshold used to calculate this data */
    private double threshold;
    /** The number of true positives classified at this threshold */
    private int truePositives;
    /** The number of true negatives classified at this threshold */
    private int trueNegatives;
    /** The number of false positives classified at this threshold */
    private int falsePositives;
    /** The number of false negatives classified at this threshold */
    private int falseNegatives;

    /** Constructor
     * @param threshold The threshold for this point
     * @param truePositives The number of true positives calculated at this threshold
     * @param trueNegatives The number of true negatives calculated at this threshold
     * @param falsePositives The number of false positives calculated at this threshold
     * @param falseNegatives The number of false negatives calculated at this threshold
     */
    public ClassifierSetPoint(double threshold, int truePositives, int trueNegatives,
                              int falsePositives, int falseNegatives) {
        this.threshold = threshold;
        this.truePositives = truePositives;
        this.trueNegatives = trueNegatives;
        this.falsePositives = falsePositives;
        this.falseNegatives = falseNegatives;
    }

    /**
     * Getter for the threshold
     * @return The threshold
     */
    public double getThreshold() {
        return this.threshold;
    }

    /**
     * Getter for the true positives
     * @return The true positives
     */
    public int getTruePositives() {
        return this.truePositives;
    }

    /**
     * Getter for the true negatives
     * @return The true negatives
     */
    public int getTrueNegatives() {
        return this.trueNegatives;
    }

    /**
     * Gets the total number of actual positives
     * @return The total number of actual positives
     */
    public int getNumPositives() {
        return this.truePositives + this.falseNegatives;
    }

    /**
     * Gets the total number of actual negatives
     * @return The total number of actual negatives
     */
    public int getNumNegatives() {
        return this.trueNegatives + this.falsePositives;
    }

    /**
     * Getter for the false positives
     * @return The false positives
     */
    public int getFalsePositives() {
        return this.falsePositives;
    }

    /**
     * Getter for the false negatives
     * @return The false negatives
     */
    public int getFalseNegatives() {
        return this.falseNegatives;
    }
}
