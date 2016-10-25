package com.metsci.laproc.plotting;

/**
 * Computes a metric
 * Created by robinsat on 10/25/2016.
 */
public interface Metric {
    //TODO acutally describe this better
    public double getMetric();

    public String getDescriptor();
}
