package com.metsci.laproc.uicomponents;

import com.metsci.glimpse.layout.GlimpseLayoutProvider;
import com.metsci.glimpse.painter.decoration.LegendPainter.*;
import com.metsci.glimpse.painter.info.CursorTextPainter;
import com.metsci.glimpse.painter.plot.XYLinePainter;
import com.metsci.glimpse.painter.shape.PolygonPainter;
import com.metsci.glimpse.plot.SimplePlot2D;
import com.metsci.glimpse.support.color.GlimpseColor;
import com.metsci.laproc.plotting.*;
import com.metsci.laproc.utils.IAction;
import com.metsci.laproc.utils.IActionReceiver;

import javax.sound.sampled.Line;
import java.util.Collection;

/**
 * Creates a Glimpse plot for a Graph
 * Created by malinocr on 9/20/2016.
 */
public class GraphDisplayer implements GlimpseLayoutProvider
{
    private IAction<GraphPoint[]>[] pointClickActions;
    private Graph graph;

    static final float[][] possibleColors = new float[8][4];

    static {
        possibleColors[0] = GlimpseColor.fromColorRgb(0f, 0f, 0f);
        possibleColors[1] = GlimpseColor.fromColorRgb(1f, 0f, 0f);
        possibleColors[2] = GlimpseColor.fromColorRgb(0f, 1f, 0f);
        possibleColors[3] = GlimpseColor.fromColorRgb(0f, 0f, 1f);
        possibleColors[4] = GlimpseColor.fromColorRgb(0.2f, 0.5f, 0.5f);
        possibleColors[5] = GlimpseColor.fromColorRgb(0.4f, 0.4f, 0f);
        possibleColors[6] = GlimpseColor.fromColorRgb(1f, 0f, 1f);
        possibleColors[7] = GlimpseColor.fromColorRgb(0.4f, 0f, 0.4f);
    }

    /**
     * Constructor for a given graph and window
     * @params graph, displayer
     */
    public GraphDisplayer(IAction<GraphPoint[]>... pointClickActions) {
        this.pointClickActions = pointClickActions;
        //By default, display an empty graph
        this.graph = new BasicGraph();
    }

    /**
     * This should be called before getLayout to update the graph that the canvas should display.
     * Because getLayout() is an interface method from Glimpse, we can't give it a Graph as a parameter
     * So use this method instead
     * @param graph The graph to display on this GraphDisplayer
     */
    public void setGraph(Graph graph) {
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

        // Set axis labels and chart title
        plot.setTitle(graph.getTitle());
        GraphDisplayer.setPlotAxis(graph.getXAxis(), graph.getYAxis(), plot);

        // Add mouse listener
        PolygonPainter selectedAreaPainter = new PolygonPainter();
        plot.addPainter(selectedAreaPainter);
        plot.addGlimpseMouseListener(new GraphDisplayerMouseListener(graph, selectedAreaPainter, this.pointClickActions));

        // Only show the x and y crosshairs
        plot.getCrosshairPainter().showSelectionBox(false);

        //Set up Legend
        LineLegendPainter legend = GraphDisplayer.createLineLegendPainter();

        //Draws each graphable data
        GraphDisplayer.drawGraphableData(graph.getDisplayedData(), plot, legend);

        // Add a painter to uicomponents the x and y position of the cursor
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
    private static XYLinePainter createXYLinePainter(GraphableData data, float[] lineColor, float lineThickness)
    {
        XYLinePainter linePainter = new XYLinePainter();
        linePainter.setData(data.getXValues(),data.getYValues());
        linePainter.setLineColor(lineColor);
        linePainter.setLineThickness(lineThickness);
        linePainter.showPoints(false);
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
    public static LineLegendPainter createLineLegendPainter(){
        LineLegendPainter legend = new LineLegendPainter(LegendPlacement.SE);
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
    public static void drawGraphableData(Iterable<GraphableData> data, SimplePlot2D plot, LineLegendPainter legend){
        int currentColor = 0;
        for(GraphableData lineData : data){
            float[] color = GraphDisplayer.possibleColors[currentColor];
            if (currentColor != GraphDisplayer.possibleColors.length - 1) {
                currentColor++;
            }
            XYLinePainter linePainter = createXYLinePainter(lineData, color, 1.5f);
            plot.addPainter(linePainter);
            legend.addItem(lineData.getName(), color);
        }
    }
}