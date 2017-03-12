package com.metsci.laproc.tools;

import com.metsci.glimpse.painter.plot.XYLinePainter;
import com.metsci.glimpse.plot.Plot2D;
import com.metsci.laproc.plotting.Graph;
import com.metsci.laproc.plotting.GraphableData;
import com.metsci.laproc.uicomponents.GraphVisualProperties;
import com.metsci.laproc.uicomponents.PainterFactory;
import com.metsci.laproc.uicomponents.graphfeatures.GraphFeature;

import java.util.*;

/**
 * Applies composite functions to the Graph
 * Created by robinsat on 1/30/2017.
 */
public class GraphDisplayManagerImpl implements GraphDisplayManager {

    /** The set of features that will be applied to the graph */
    private Collection<GraphFeature> graphFeatures;

    /**
     * Constructor
     */
    public GraphDisplayManagerImpl() {
        graphFeatures = new HashSet<GraphFeature>();
    }

    /**
     * Adds an item to the set of features applied to the graph
     * @param feature The feature to enable
     */
    public void enableGraphFeature(GraphFeature feature) {
        this.graphFeatures.add(feature);
    }

    /**
     * Removes an item from the set of features applied to the graph
     * @param feature The feature to disable
     */
    public void disableGraphFeature(GraphFeature feature) {
        this.graphFeatures.remove(feature);
    }


    /**
     * Called whenever the Graph needs to update
     * @param graph The graph to use for the features
     * @param plot The plot on which to draw the features
     * @param properties The properties to use for drawing the features
     */
    public void updateGraph(Graph graph, Plot2D plot, GraphVisualProperties properties) {
        for(GraphFeature feature : this.graphFeatures) {
            feature.applyToPlot(graph, plot, properties);
            //System.out.println(feature.getClass().toString());
        }
        /*GraphableData d = graph.getData().iterator().next();
        if(d != null) {
            PainterFactory pf = new PainterFactory();
            XYLinePainter painter = pf.getLinePainter();
            painter.setData(d.getXValues(), d.getYValues());
            plot.addPainter(painter);
        }*/
    }
}
