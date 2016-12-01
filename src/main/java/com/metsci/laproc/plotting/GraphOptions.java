package com.metsci.laproc.plotting;

import com.metsci.laproc.pointmetrics.ParametricFunction;

import java.util.Collection;
import java.util.List;

/**
 * An interface representing a set of graph options
 * Created by robinsat on 11/30/2016.
 */
interface GraphOptions {

    ParametricFunction getXAxisFunction();
    ParametricFunction getYAxisFunction();

    Collection<ParametricFunction> getAllFunctions();
    void useFunctions(ParametricFunction xAxis, ParametricFunction yAxis);
}
