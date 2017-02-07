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

/**
 * Creates a Glimpse plot for a Graph
 * Created by malinocr on 9/20/2016.
 */
public class GraphDisplayer implements GlimpseLayoutProvider
{
    private PainterFactory factory;
    private IAction<GraphPoint[]>[] pointClickActions;
    private Graph graph;

    static final float[][] possibleColors = new float[7][4];

    static {
        possibleColors[0] = GlimpseColor.fromColorRgb(0f, 0f, 0f);
        possibleColors[1] = GlimpseColor.fromColorRgb(1f, 0f, 0f);
        possibleColors[2] = GlimpseColor.fromColorRgb(0f, 0f, 1f);
        possibleColors[3] = GlimpseColor.fromColorRgb(0.2f, 0.5f, 0.5f);
        possibleColors[4] = GlimpseColor.fromColorRgb(0.4f, 0.4f, 0f);
        possibleColors[5] = GlimpseColor.fromColorRgb(1f, 0f, 1f);
        possibleColors[6] = GlimpseColor.fromColorRgb(0.4f, 0f, 0.4f);
    }

    /**
     * Default constructor for the graph displayer
     * @param pointClickActions point click actions for the graph displayer
     */
    public GraphDisplayer(IAction<GraphPoint[]>... pointClickActions) {
        this.pointClickActions = pointClickActions;
        //By default, display an empty graph
        this.graph = new BasicGraph();
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
        GraphRenderer renderer = new GraphRenderer();

        // Create a plot frame
        SimplePlot2D plot = renderer.getLayout(graph);
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
