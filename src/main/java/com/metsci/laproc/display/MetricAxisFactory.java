package com.metsci.laproc.display;

import com.metsci.laproc.plotting.Axis;
import com.metsci.laproc.pointmetrics.*;
import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.HashMap;

/**
 * Created by malinocr on 12/8/2016.
 */
public class MetricAxisFactory {
    public static Axis createAxis(Metric metric){
        if(metric instanceof TruePositiveRate){
            return new Axis(0,1, metric.getDescriptor());
        } else if (metric instanceof FalsePositiveRate){
            return new Axis(0,1, metric.getDescriptor());
        } else if (metric instanceof Precision){
            return new Axis(0,1, metric.getDescriptor());
        }
        throw new IllegalArgumentException("Metric " + metric.getDescriptor() + " not supported");
    }
}
