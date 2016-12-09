package com.metsci.laproc.display;

import com.metsci.glimpse.layout.GlimpseLayoutProvider;
import com.metsci.glimpse.painter.decoration.LegendPainter.*;
import com.metsci.glimpse.painter.info.CursorTextPainter;
import com.metsci.glimpse.painter.plot.XYLinePainter;
import com.metsci.glimpse.painter.shape.PolygonPainter;
import com.metsci.glimpse.plot.SimplePlot2D;
import com.metsci.glimpse.support.color.GlimpseColor;
import com.metsci.laproc.plotting.Axis;
import com.metsci.laproc.plotting.Graph;
import com.metsci.laproc.plotting.GraphableData;

/**
 * Creates a Glimpse plot for a Graph
 * Created by malinocr on 9/20/2016.
 */
public class GraphDisplayer implements GlimpseLayoutProvider
{
    Graph graph;
    private Window window;

    /**
     * Constructor for a given graph and window
     * @params graph, displayer
     */
    public GraphDisplayer(Graph graph, Window window) {
        this.graph = graph;
        this.window = window;
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

        // Add mouse listener
        PolygonPainter selectedAreaPainter = new PolygonPainter();
        plot.addPainter(selectedAreaPainter);

        plot.addGlimpseMouseListener(new GraphDisplayerMouseListener(graph, window, selectedAreaPainter));

        // Only show the x and y crosshairs
        plot.getCrosshairPainter().showSelectionBox(false);

        //Set up Legend
        LineLegendPainter legend = new LineLegendPainter(LegendPlacement.SE);
        legend.setOffsetY(10);
        legend.setOffsetX(100);
        legend.setLegendItemWidth(60);

        //Sets up all possible line colors for graphable data
        float[][] possibleColors = new float[8][4];
        possibleColors[0] = GlimpseColor.fromColorRgb(1f,0f,0f);
        possibleColors[1] = GlimpseColor.fromColorRgb(0f,1f,0f);
        possibleColors[2] = GlimpseColor.fromColorRgb(0f,0f,1f);
        possibleColors[3] = GlimpseColor.fromColorRgb(0.2f,0.5f,0.5f);
        possibleColors[4] = GlimpseColor.fromColorRgb(0.4f,0.4f,0f);
        possibleColors[5] = GlimpseColor.fromColorRgb(1f,0f,1f);
        possibleColors[6] = GlimpseColor.fromColorRgb(0.4f,0f,0.4f);

        //Draws each graphable data
        int currentColor = 0;
        for(GraphableData lineData : graph.getData()){
            if(!lineData.equals(graph.getSelectedData())) {
                float[] color = possibleColors[currentColor];
                if (currentColor != possibleColors.length) {
                    currentColor++;
                }
                XYLinePainter linePainter = createXYLinePainter(lineData, color, 1.5f);
                plot.addPainter(linePainter);
                legend.addItem(lineData.getName(), color);
            }
        }

        //Draw selected line
        float[] selectedLineColor = GlimpseColor.fromColorRgb(0f,0f,0f);
        XYLinePainter linePainter = createXYLinePainter(graph.getSelectedData(), selectedLineColor, 2.5f);
        plot.addPainter(linePainter);
        legend.addItem(graph.getSelectedData().getName(), selectedLineColor);

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
     * @param lineThickness thinkness of the line drawn
     * @return the XYLinePainter for the GraphableData
     */
    public static XYLinePainter createXYLinePainter(GraphableData data, float[] lineColor, float lineThickness)
    {
        XYLinePainter linePainter = new XYLinePainter();
        linePainter.setData(data.getXValues(),data.getYValues());
        linePainter.setLineColor(lineColor);
        linePainter.setLineThickness(lineThickness);

        //Don't show the actual points, just show the line connecting them
        linePainter.showPoints(false);

        return linePainter;
    }

    /**
     * Sets selected data set
     * @param data data set to set
     */
    public void setSelectedDataSet(GraphableData data){
        this.graph.setSelectedData(data);
    }
}