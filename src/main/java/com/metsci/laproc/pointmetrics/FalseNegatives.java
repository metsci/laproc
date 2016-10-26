package com.metsci.laproc.pointmetrics;

import com.metsci.laproc.plotting.ROCCurve;

/**
 * Created by robinsat on 10/25/2016.
 */
public class FalseNegatives implements Metric {
    public double getMetric(ClassifierSetPoint point) {
        return point.getFalseNegatives();
    }

    public String getDescriptor() {
        return ROCCurve.fnString;
    }
}
