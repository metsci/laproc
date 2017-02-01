package com.metsci.laproc.plotting;

/**
 * An interface representing any computable function
 * Created by robinsat on 9/20/2016.
 */
public interface GraphableFunction<T> {

    /**
     * Executes the function and returns the resulting data set
     * @return GraphableData
     */
    GraphableData compute(T input);

}
