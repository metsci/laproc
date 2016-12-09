package com.metsci.laproc.pointmetrics;

/**
 * An interface representing a parametric function
 * Created by robinsat on 11/30/2016.
 */
public interface ParametricFunction<T> {

    /**
     * Calculates the output based on input t
     * @param t The input for the function
     * @return The output for the function
     */
    double compute(T t);

    /**
     * Gets a string descriptor of what this function represents
     * @return A String descriptor of what this function represents
     */
    String getDescriptor();
}
