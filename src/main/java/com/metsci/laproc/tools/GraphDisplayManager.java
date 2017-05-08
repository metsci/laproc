package com.metsci.laproc.tools;

import com.metsci.laproc.uicomponents.graphfeatures.GraphFeature;

/**
 * Applies composite functions to the Graph
 * Created by robinsat on 1/30/2017.
 */
public interface GraphDisplayManager {

    /**
     * Adds an item to the set of features applied to the graph
     * @param feature The feature to enable
     */
    void enableGraphFeature(GraphFeature feature);

    /**
     * Removes an item from the set of features applied to the graph
     * @param feature The feature to disable
     */
    void disableGraphFeature(GraphFeature feature);

}
