package com.metsci.laproc.plotting;

/**
 * A data class that stores the classifer data for a single point on a curve
 * Created by robinsat on 10/25/2016.
 */
public class ClassifierSetPoint {

    /** The threshold used to calculate this data */
    private double threshold;
    /** The number of true positives classified at this threshold */
    private int truePositives;
    /** The number of true negatives classified at this threshold */
    private int trueNegatives;
    private int falsePositives;
    private int falseNegatives;

    public ClassifierSetPoint(double a, int b, int c, int d, int e) {
        this.threshold = a;
        this.truePositives = b;
        this.trueNegatives = c;
        this.falsePositives = d;
        this.falseNegatives = e;
    }

    public double getThreshold() {
        return this.threshold;
    }

    public int getTruePositives() {
        return this.truePositives;
    }

    public int getTrueNegatives() {
        return this.trueNegatives;
    }

    public int getNumPositives() {
        return this.truePositives + this.falseNegatives;
    }

    public int getNumNegatives() {
        return this.trueNegatives + this.falsePositives;
    }

    public int getFalsePositives() {
        return this.falsePositives;
    }

    public int getFalseNegatives() {
        return this.falseNegatives;
    }
}
