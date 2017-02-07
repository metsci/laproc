package com.metsci.laproc.uicomponents.graphfeatures;

import com.metsci.glimpse.plot.Plot2D;
import com.metsci.laproc.plotting.CompositeFunction;
import com.metsci.laproc.plotting.Graph;
import com.metsci.laproc.plotting.GraphableData;

/**
 * Executes a composite function and draws the result to the graph
 * Created by robinsat on 2/7/2017.
 */
public class CompositeFunctionDrawer implements GraphFeature {

    /** The composite function that will output the data to add to the graph */
    private CompositeFunction function;

    /**
     * Constructor
     * @param function The composite function to draw
     */
    public CompositeFunctionDrawer(CompositeFunction function) {
        this.function = function;
    }

    /**
     * Applies this feature to the graph
     * @param graph
     * @param plot  The graph on which to draw this feature
     */
    public void applyToPlot(Graph graph, Plot2D plot) {
        GraphableData toAdd = function.compute(graph.getData());
    }
}
