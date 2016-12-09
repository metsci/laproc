package com.metsci.laproc.plotting;

import com.metsci.laproc.pointmetrics.ParametricFunction;

import java.util.Collection;

/**
 * An interface representing any computable function
 * Created by robinsat on 9/20/2016.
 */
public interface GraphableFunction {

    /**
     * Executes the function and returns the resulting data set
     * @return GraphableData
     */
    GraphableData compute();

    Collection<ParametricFunction> getAllFunctions();
    void useFunctions(ParametricFunction xAxis, ParametricFunction yAxis);
}
