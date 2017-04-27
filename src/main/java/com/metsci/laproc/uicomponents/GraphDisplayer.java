package com.metsci.laproc.uicomponents;

import com.metsci.glimpse.layout.GlimpseLayoutProvider;
import com.metsci.glimpse.painter.info.CursorTextPainter;
import com.metsci.glimpse.painter.shape.PolygonPainter;
import com.metsci.glimpse.plot.Plot2D;
import com.metsci.glimpse.plot.SimplePlot2D;
import com.metsci.glimpse.support.color.GlimpseColor;
import com.metsci.laproc.plotting.*;
import com.metsci.laproc.tools.GraphDisplayManager;
import com.metsci.laproc.uicomponents.graphfeatures.GraphFeature;
import com.metsci.laproc.utils.IAction;

import java.util.ArrayList;
import java.util.List;

import java.util.Map;

/**
 * Creates a Glimpse plot for a Graph
 * Created by malinocr on 9/20/2016.
 */
public class GraphDisplayer implements GlimpseLayoutProvider, GraphDisplayManager
{
    /**
     * Adds an item to the set of features applied to the graph
     * @param feature The feature to enable
     */
    public void enableGraphFeature(GraphFeature feature) {
        this.graphRenderer.enableGraphFeature(feature);
    }

    /**
     * Removes an item from the set of features applied to the graph
     * @param feature The feature to disable
     */
    public void disableGraphFeature(GraphFeature feature) {
        this.graphRenderer.disableGraphFeature(feature);
    }

    private GraphRenderer graphRenderer;
    private IAction<Map<String, GraphPoint>>[] pointClickActions;
    private Graph graph;

    /**
     * Default constructor for the graph displayer
     * @param pointClickActions point click actions for the graph displayer
     */
    public GraphDisplayer(IAction<Map<String, GraphPoint>>... pointClickActions) {
        this.pointClickActions = pointClickActions;
        //By default, display an empty graph
        this.graph = new BasicGraph();
        this.graphRenderer = new GraphRenderer();
    }

    /**
     * Sets the graph displayer to use the properties provided
     * @param properties The new set of properties to use
     */
    public void setGraphProperties(GraphVisualProperties properties) {
        this.graphRenderer.setGraphProperties(properties);
    }

    /**
     * This should be called before getLayout to update the graph that the canvas should display.
     * @param graph The graph to display on this GraphDisplayer
     */
    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    /**
     * Gets the Glimpse plot for the graph
     * @return GlimpseLayout plot of the graph
     */
    public SimplePlot2D getLayout()
    {
        // Create a plot frame
        SimplePlot2D plot = graphRenderer.getLayout(graph);

        // Only show the x and y crosshairs
        plot.getCrosshairPainter().showSelectionBox(false);

        // Add mouse listener
        PolygonPainter selectedAreaPainter = new PolygonPainter();
        plot.addPainter(selectedAreaPainter);
        plot.addGlimpseMouseListener(new GraphDisplayerMouseListener(graph, selectedAreaPainter, this.pointClickActions));

        // Add a painter to uicomponents the x and y position of the cursor
        CursorTextPainter cursorPainter = new CursorTextPainter();
        plot.addPainter(cursorPainter);

        // Don't offset the text by the size of the selection box
        cursorPainter.setOffsetBySelectionSize(false);

        return plot;
    }

}
