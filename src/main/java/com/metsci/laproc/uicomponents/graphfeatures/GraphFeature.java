package com.metsci.laproc.uicomponents.graphfeatures;

import com.metsci.glimpse.plot.Plot2D;
import com.metsci.laproc.plotting.Graph;

/**
 * An interface representing some feature that can be added on to a graph in addition to the raw data
 * Created by robinsat on 2/7/2017.
 */
public interface GraphFeature {

    /**
     * Applies this feature to the graph
     * @param plot The graph on which to draw this feature
     */
    void applyToPlot(Graph graph, Plot2D plot);
}
