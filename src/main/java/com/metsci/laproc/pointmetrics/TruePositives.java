package com.metsci.laproc.pointmetrics;

import com.metsci.laproc.plotting.ROCCurve;

/**
 * Created by robinsat on 10/25/2016.
 */
public class TruePositives implements Metric{
    public double getMetric(ClassifierSetPoint point) {
        return point.getTruePositives();
    }

    public String getDescriptor() {
        return ROCCurve.tpString;
    }
}
