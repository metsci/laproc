package com.metsci.laproc.tools;

import com.metsci.laproc.datareference.GraphReference;
import com.metsci.laproc.plotting.CompositeFunction;
import com.metsci.laproc.utils.IObservable;
import com.metsci.laproc.utils.IObserver;

/**
 * Applies composite functions to the Graph
 * Created by robinsat on 1/30/2017.
 */
public interface GraphDisplayManager extends IObservable, IObserver<GraphReference>, GraphReference {

    /**
     * Adds a composite function to the set of functions applied to the graph
     * @param func The function to apply
     */
    void enableCompositeFunction(CompositeFunction func);

    /**
     * Removes a composite function to the set of functions applied to the graph
     * @param func The function to disable
     */
    void disableCompositeFunction(CompositeFunction func);
}
