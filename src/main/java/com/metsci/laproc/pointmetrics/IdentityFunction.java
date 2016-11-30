package com.metsci.laproc.pointmetrics;

/**
 * A function for which the output is equal to the input
 * Created by robinsat on 11/30/2016.
 */
public class IdentityFunction implements ParametricFunction<Double> {

    /** A string descriptor of what this function represents */
    private String descriptor;

    /**
     * Constructor
     * @param descriptor A String descriptor of what this function represents
     */
    public IdentityFunction(String descriptor) {
        this.descriptor = descriptor;
    }

    /**
     *
     * @param t The input for the function
     * @return The output for the function
     */
    public double compute(Double t) {
        return t;
    }

    /**
     * Gets a string descriptor of what this function represents
     * @return A String descriptor of what this function represents
     */
    public String getDescriptor() {
        return this.descriptor;
    }
}
