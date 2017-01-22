package com.metsci.laproc.uicomponents;

import com.metsci.glimpse.layout.GlimpseLayoutProvider;
import com.metsci.glimpse.painter.decoration.LegendPainter;
import com.metsci.glimpse.painter.info.CursorTextPainter;
import com.metsci.glimpse.painter.plot.XYLinePainter;
import com.metsci.glimpse.painter.shape.PolygonPainter;
import com.metsci.glimpse.plot.SimplePlot2D;
import com.metsci.glimpse.support.color.GlimpseColor;
import com.metsci.laproc.plotting.*;
import com.metsci.laproc.utils.IAction;

/**
 * Provides a layout
 * Created by malinocr on 1/21/2017.
 */
public class GraphExporter implements GlimpseLayoutProvider {
    private Graph graph;

    /**
     * Constructor for a graph exporter
     */
    public GraphExporter(Graph graph) {
        this.graph = graph;
    }
    /**
     * Gets the layout for the graph
     * @return GlimpseLayout layout for the graph
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
        plot.getCrosshairPainter().setVisible(false);

        //Set up Legend
        LegendPainter.LineLegendPainter legend = new LegendPainter.LineLegendPainter(LegendPainter.LegendPlacement.SE);
        legend.setOffsetY(10);
        legend.setOffsetX(100);
        legend.setLegendItemWidth(60);

        //Sets up all possible line colors for graphable data
        float[][] possibleColors = new float[8][4];
        possibleColors[0] = GlimpseColor.fromColorRgb(0f,0f,0f);
        possibleColors[1] = GlimpseColor.fromColorRgb(1f,0f,0f);
        possibleColors[2] = GlimpseColor.fromColorRgb(0f,1f,0f);
        possibleColors[3] = GlimpseColor.fromColorRgb(0f,0f,1f);
        possibleColors[4] = GlimpseColor.fromColorRgb(0.2f,0.5f,0.5f);
        possibleColors[5] = GlimpseColor.fromColorRgb(0.4f,0.4f,0f);
        possibleColors[6] = GlimpseColor.fromColorRgb(1f,0f,1f);
        possibleColors[7] = GlimpseColor.fromColorRgb(0.4f,0f,0.4f);

        //Draws each graphable data
        int currentColor = 0;
        for(GraphableData lineData : graph.getDisplayedData()){
            float[] color = possibleColors[currentColor];
            if (currentColor != possibleColors.length - 1) {
                currentColor++;
            }
            XYLinePainter linePainter = GraphDisplayer.createXYLinePainter(lineData, color, 1.5f);
            plot.addPainter(linePainter);
            legend.addItem(lineData.getName(), color);
        }

        // Add the legend painter to the top of the center GlimpseLayout
        plot.getLayoutCenter().addPainter(legend);

        return plot;
    }
}
