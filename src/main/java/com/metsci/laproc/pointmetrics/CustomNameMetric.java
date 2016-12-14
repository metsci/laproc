package com.metsci.laproc.pointmetrics;

/**
 * This allows a user to give a custom name to an existing parametric function
 * Created by robinsat on 12/9/2016.
 */
public class CustomNameMetric<T> implements ParametricFunction<T> {

    /** The name of this metric */
    private String name;
    /** The metric to use for the actual computation */
    private ParametricFunction<T> metric;

    /**
     * Constructor
     * @param name The custom name to use for this metric
     * @param metric The metric to use for the computation
     */
    public CustomNameMetric(String name, ParametricFunction<T> metric) {
        this.name = name;
        this.metric = metric;
    }

    /**
     * Calculates the output based on input t
     * @param t The input for the function
     * @return The output for the function
     */
    public double compute(T t) {
        return metric.compute(t);
    }

    /**
     * Gets a string descriptor of what this function represents
     * @return A String descriptor of what this function represents
     */
    public String getDescriptor() {
        return name;
    }
}
