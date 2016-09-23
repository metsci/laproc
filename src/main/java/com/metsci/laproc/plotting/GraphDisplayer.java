package com.metsci.laproc.plotting;

import com.metsci.glimpse.layout.GlimpseLayoutProvider;
import com.metsci.glimpse.painter.decoration.LegendPainter.*;
import com.metsci.glimpse.painter.info.CursorTextPainter;
import com.metsci.glimpse.painter.plot.XYLinePainter;
import com.metsci.glimpse.plot.SimplePlot2D;
import com.metsci.glimpse.support.color.GlimpseColor;
import com.metsci.laproc.plotting.Axis;
import com.metsci.laproc.plotting.Graph;
import com.metsci.laproc.plotting.GraphableData;

import java.util.Collection;
import java.util.HashSet;

/**
 * Creates a Glimpse plot for a Graph
 * Created by malinocr on 9/20/2016.
 */
public class GraphDisplayer implements GlimpseLayoutProvider
{
    Graph graph;

    /**
     * Constructor for a given Graph
     * @param graph graph to display
     */
    public GraphDisplayer(Graph graph)
    {
        this.graph = graph;
    }

    /**
     * Gets the plot for the graph
     * @return GlimpseLayout plot of the graph
     */
    public SimplePlot2D getLayout()
    {
        // Create a plot frame
        SimplePlot2D plot = new SimplePlot2D( );

        Axis xAxis = graph.getXAxis();
        Axis yAxis = graph.getYAxis();

        // Set axis labels and chart title
        plot.setTitle(graph.getTitle());
        plot.setAxisLabelX(xAxis.getName());
        plot.setAxisLabelY(yAxis.getName());

        // Set the x, y initial axis bounds
        plot.setMinX(xAxis.getMin());
        plot.setMaxX(xAxis.getMax());

        plot.setMinY(yAxis.getMin());
        plot.setMaxY(yAxis.getMax());

        // Only show the x and y crosshairs
        plot.getCrosshairPainter().showSelectionBox(false);

        //Set up Legend
        LineLegendPainter legend = new LineLegendPainter(LegendPlacement.SE);
        legend.setOffsetY(10);
        legend.setOffsetX(100);
        legend.setLegendItemWidth(60);

        // Draws a line for each set of graphable data
        HashSet<float[]> usedColors = new HashSet<float[]>();
        for(GraphableData lineData : graph.getData()){
            float[] color = null;
            do {
                color = GlimpseColor.fromColorRgb((float)Math.random(),(float)Math.random(),(float)Math.random());
            } while (usedColors.contains(color));
            XYLinePainter linePainter = createXYLinePainter(lineData,color);
            plot.addPainter(linePainter);
            legend.addItem(lineData.getName(),color);
        }

        // Add a painter to display the x and y position of the cursor
        CursorTextPainter cursorPainter = new CursorTextPainter();
        plot.addPainter(cursorPainter);

        // Don't offset the text by the size of the selection box
        cursorPainter.setOffsetBySelectionSize(false);

        // Add the legend painter to the top of the center GlimpseLayout
        plot.getLayoutCenter().addPainter(legend);

        return plot;
    }

    /**
     * Creates a XYLinePainter for a set of GraphableData that connects them with a colored line
     * @param data data that the line painter should draw
     * @param lineColor color of the line drawn
     * @return the XYLinePainter for the GraphableData
     */
    public static XYLinePainter createXYLinePainter(GraphableData data,float[] lineColor)
    {
        XYLinePainter linePainter = new XYLinePainter();
        linePainter.setData(data.getXValues(),data.getYValues());
        linePainter.setLineColor(lineColor);
        linePainter.setLineThickness(1.5f);

        //Don't show the actual points, just show the line connecting them
        linePainter.showPoints(false);

        return linePainter;
    }
}