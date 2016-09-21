package com.metsci.laproc.plotting;

/**
 * An interface representing any computable function
 * Created by robinsat on 9/20/2016.
 */
public interface GraphableFunction {

    /** Executes the function and returns the resulting data set */
    public GraphableData compute();

}