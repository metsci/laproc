package com.metsci.laproc.plotting;

import com.metsci.laproc.pointmetrics.ParametricFunction;

/**
 * Returns the X value of a composite point
 * Created by robinsat on 3/12/2017.
 */
public class CompositePointXMetric implements ParametricFunction<CompositePoint> {
    /**
     * Calculates the output based on input t
     *
     * @param compositePoint The input for the function
     * @return The output for the function
     */
    public double compute(CompositePoint compositePoint) {
        return compositePoint.getXValue();
    }

    /**
     * Gets a string descriptor of what this function represents
     * @return A String descriptor of what this function represents
     */
    public String getDescriptor() {
        return "X Value";
    }
}
