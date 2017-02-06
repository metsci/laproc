package com.metsci.laproc.datareference;

import com.metsci.laproc.plotting.Graph;
import com.metsci.laproc.utils.IObservable;

/**
 * An interface allowing a tool to observe a Graph object and update itself accordingly.
 * Created by robinsat on 1/30/2017.
 */
public interface GraphReference extends IObservable {

    /**
     * Getter for the Graph object
     * @return The Graph object
     */
    Graph getGraph();
}
