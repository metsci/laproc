package com.metsci.laproc.uicomponents;

import com.metsci.glimpse.painter.decoration.LegendPainter;
import com.metsci.glimpse.painter.plot.XYLinePainter;
import com.metsci.glimpse.plot.Plot2D;
import com.metsci.glimpse.plot.SimplePlot2D;
import com.metsci.laproc.plotting.Axis;
import com.metsci.laproc.plotting.Graph;
import com.metsci.laproc.plotting.GraphableData;
import com.metsci.laproc.tools.GraphDisplayManager;
import com.metsci.laproc.uicomponents.graphfeatures.GraphFeature;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a graph object, creates a Plot2D
 * Created by robinsat on 2/7/2017.
 */
public class GraphRenderer implements GraphDisplayManager {

    private GraphVisualProperties properties;
    private List<GraphFeature> graphFeatures;

    /**
     * Constructor
     */
    public GraphRenderer() {
        this.properties = new GraphVisualProperties();
        this.graphFeatures = new ArrayList<GraphFeature>();
    }

    /**
     * Sets the graph displayer to use the properties provided
     * @param properties The new set of properties to use
     */
    public void setGraphProperties(GraphVisualProperties properties) {
        this.properties = properties;
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
     * Gets the glimpse layout that will be displayed for the graph
     * @return glimpse layout of the graph
     */
    public SimplePlot2D getLayout(Graph graph)
    {
        // Create a plot frame
        SimplePlot2D plot = new SimplePlot2D( );

        PainterFactory factory = new PainterFactory(this.properties);

        // Set axis labels and chart title
        plot.setTitle(graph.getTitle());
        setPlotAxis(graph.getXAxis(), graph.getYAxis(), plot);

        //Set up Legend
        LegendPainter.LineLegendPainter legend = factory.getLineLegendPainter(LegendPainter.LegendPlacement.SE);

        //Draws each graphable data
        drawGraphableData(graph.getData(), plot, factory, legend);
        applyFeatures(graph, plot, properties);

        // Add the legend painter to the top of the center GlimpseLayout
        plot.getLayoutCenter().addPainter(legend);

        return plot;
    }

    /**
     * Sets the values on the plot to the appropriate values according to the x and y axes
     * @param xAxis x axis of the plot
     * @param yAxis y axis of the plot
     * @param plot plot to set values
     */
    public void setPlotAxis(Axis xAxis, Axis yAxis, SimplePlot2D plot){
        plot.setAxisLabelX(xAxis.getName());
        plot.setAxisLabelY(yAxis.getName());
        plot.setMinX(xAxis.getMin());
        plot.setMaxX(xAxis.getMax());
        plot.setMinY(yAxis.getMin());
        plot.setMaxY(yAxis.getMax());
    }

    /**
     * Draws the graphable data on the plot and adds that data to the legend
     * @param data iterable of the data to draw
     * @param plot plot to draw the data on
     * @param legend legend to add the data to
     */
    public void drawGraphableData(Iterable<GraphableData> data, SimplePlot2D plot, PainterFactory factory, LegendPainter.LineLegendPainter legend){
        int currentColor = 0;
        for(GraphableData lineData : data){
            float[] color = GraphDisplayer.possibleColors[currentColor];
            if (currentColor != GraphDisplayer.possibleColors.length - 1) {
                currentColor++;
            }
            XYLinePainter linePainter = factory.getLinePainter(color);
            linePainter.setData(lineData.getXValues(), lineData.getYValues());
            plot.addPainter(linePainter);
            legend.addItem(lineData.getName(), color);
        }
    }

    /**
     * Called whenever the Graph needs to update
     * @param graph The graph to use for the features
     * @param plot The plot on which to draw the features
     * @param properties The properties to use for drawing the features
     */
    private void applyFeatures(Graph graph, Plot2D plot, GraphVisualProperties properties) {
        for(GraphFeature feature : this.graphFeatures) {
            feature.applyToPlot(graph, plot, properties);
        }
    }

}
