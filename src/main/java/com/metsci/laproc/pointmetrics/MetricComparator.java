package com.metsci.laproc.pointmetrics;

import java.util.Comparator;

/**
 * Compares two items using a Metric
 * Created by robinsat on 12/8/2016.
 */
public class MetricComparator<T> implements Comparator<T> {

    /** The metric to use for comparison */
    private ParametricFunction<T> function;

    /**
     * Constructor
     * @param metric The metric to use for comparison
     */
    public MetricComparator(ParametricFunction<T> metric) {
        this.function = metric;
    }

    /**
     * Compares two items using a Metric
     * @param o1 The first object to compare
     * @param o2 The second object to compare
     * @return A value indicating the relative value of the two objects
     */
    public int compare(T o1, T o2) {
        double val1 = function.compute(o1);
        double val2 = function.compute(o2);

        if(val1 >= val2) {
            return 1;
        } else {
            return -1;
        }
    }
}
