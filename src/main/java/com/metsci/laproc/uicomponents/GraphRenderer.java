package com.metsci.laproc.uicomponents;

import com.metsci.glimpse.painter.decoration.LegendPainter;
import com.metsci.glimpse.painter.plot.XYLinePainter;
import com.metsci.glimpse.plot.SimplePlot2D;
import com.metsci.laproc.plotting.Axis;
import com.metsci.laproc.plotting.Graph;
import com.metsci.laproc.plotting.GraphableData;

/**
 * Given a graph object, creates a Plot2D
 * Created by robinsat on 2/7/2017.
 */
public class GraphRenderer {

    /** The factory to use for constructing painters */
    private PainterFactory factory;

    public GraphRenderer() {
        this.factory = new PainterFactory();
    }

    /**
     * Sets the graph displayer to use the properties provided
     * @param properties The new set of properties to use
     */
    public void useGraphProperties(GraphVisualProperties properties) {
        this.factory = new PainterFactory(properties);
    }

    /**
     * Gets the glimpse layout that will be displayed for the graph
     * @param graph graph to display the layout
     * @return glimpse layout of the graph
     */
    public SimplePlot2D getLayout(Graph graph)
    {
        // Create a plot frame
        SimplePlot2D plot = new SimplePlot2D( );

        // Set axis labels and chart title
        plot.setTitle(graph.getTitle());
        setPlotAxis(graph.getXAxis(), graph.getYAxis(), plot);

        //Set up Legend
        LegendPainter.LineLegendPainter legend = createLineLegendPainter();

        //Draws each graphable data
        drawGraphableData(graph.getData(), plot, legend);

        // Add the legend painter to the top of the center GlimpseLayout
        plot.getLayoutCenter().addPainter(legend);

        return plot;
    }

    /**
     * Creates a XYLinePainter for a set of GraphableData that connects them with a colored line
     * @param data data that the line painter should draw
     * @return the XYLinePainter for the GraphableData
     */
    private static XYLinePainter createXYLinePainter(GraphableData data, float[] color)
    {
        XYLinePainter linePainter = new PainterFactory().getLinePainter(color);
        linePainter.setData(data.getXValues(),data.getYValues());
        return linePainter;
    }

    /**
     * Sets the values on the plot to the appropriate values according to the x and y axes
     * @param xAxis x axis of the plot
     * @param yAxis y axis of the plot
     * @param plot plot to set values
     */
    public static void setPlotAxis(Axis xAxis, Axis yAxis, SimplePlot2D plot){
        plot.setAxisLabelX(xAxis.getName());
        plot.setAxisLabelY(yAxis.getName());
        plot.setMinX(xAxis.getMin());
        plot.setMaxX(xAxis.getMax());
        plot.setMinY(yAxis.getMin());
        plot.setMaxY(yAxis.getMax());
    }

    /**
     * Creates a legend painter with a default layout
     * @return legend painter
     */
    public static LegendPainter.LineLegendPainter createLineLegendPainter(){
        LegendPainter.LineLegendPainter legend = new LegendPainter.LineLegendPainter(LegendPainter.LegendPlacement.SE);
        legend.setOffsetY(10);
        legend.setOffsetX(100);
        legend.setLegendItemWidth(60);
        return legend;
    }

    /**
     * Draws the graphable data on the plot and adds that data to the legend
     * @param data iterable of the data to draw
     * @param plot plot to draw the data on
     * @param legend legend to add the data to
     */
    public static void drawGraphableData(Iterable<GraphableData> data, SimplePlot2D plot, LegendPainter.LineLegendPainter legend){
        int currentColor = 0;
        for(GraphableData lineData : data){
            float[] color = GraphDisplayer.possibleColors[currentColor];
            if (currentColor != GraphDisplayer.possibleColors.length - 1) {
                currentColor++;
            }
            XYLinePainter linePainter = createXYLinePainter(lineData, color);
            plot.addPainter(linePainter);
            legend.addItem(lineData.getName(), color);
        }
    }
}
