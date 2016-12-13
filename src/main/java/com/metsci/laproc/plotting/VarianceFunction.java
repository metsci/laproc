package com.metsci.laproc.plotting;

/**
 * Computes the variance of a set of curves
 * Created by robinsat on 12/13/2016.
 */
public class VarianceFunction extends CompositeFunction {

    /**
     * Uses the y values for several data sets at a given x to compute output
     * @param yValues The y values for several data sets at a given x
     * @return The function output
     */
    protected double computeFromYValues(double[] yValues) {
        double mean = calculateAverage(yValues);
        //Compute the summation needed to find the variance
        double sum = 0;
        for(int i = 0; i < yValues.length; i++) {
            sum += Math.pow(yValues[i] - mean, 2);
        }
        return sum / yValues.length;
    }

}
