package com.metsci.laproc.uicomponents.graphfeatures;

import com.metsci.glimpse.plot.Plot2D;
import com.metsci.laproc.plotting.Graph;
import com.metsci.laproc.uicomponents.GraphVisualProperties;

/**
 * An interface representing some feature that can be added on to a graph in addition to the raw data
 * Created by robinsat on 2/7/2017.
 */
public interface GraphFeature {

    /**
     * Applies this feature to the graph
     * @param graph The Graph object containing the data that will be drawn
     * @param plot  The graph on which to draw this feature
     * @param legend The graph's legend, which the feature may add to
     * @param properties The properties to use when drawing the graph
     */
    void applyToPlot(Graph graph, Plot2D plot, Legend legend, GraphVisualProperties properties);

}
