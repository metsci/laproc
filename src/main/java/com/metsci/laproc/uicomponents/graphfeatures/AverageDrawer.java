package com.metsci.laproc.uicomponents.graphfeatures;

import com.metsci.glimpse.painter.plot.XYLinePainter;
import com.metsci.glimpse.plot.Plot2D;
import com.metsci.glimpse.support.color.GlimpseColor;
import com.metsci.laproc.plotting.*;
import com.metsci.laproc.pointmetrics.ParametricFunction;
import com.metsci.laproc.uicomponents.GraphVisualProperties;
import com.metsci.laproc.uicomponents.PainterFactory;

import java.text.ParseException;

/**
 * Executes a composite function and draws the result to the graph
 * Created by robinsat on 2/7/2017.
 */
public class AverageDrawer implements GraphFeature {

    /**
     * Applies this feature to the graph
     * @param graph The Graph object containing the data that will be drawn
     * @param plot  The graph on which to draw this feature
     * @param properties The properties to use when drawing the graph
     */
    public void applyToPlot(Graph graph, Plot2D plot, GraphVisualProperties properties) {
        PainterFactory factory = new PainterFactory(properties);
        AverageMetric averageMetric = new AverageMetric();
        CompositePointXMetric xMetric = new CompositePointXMetric();
        CompositeFunction compositeFunction = new CompositeFunction();

        //TODO explicitly throw a class cast exception here if needed
        GraphableData<CompositePoint> data = compositeFunction.compute(graph.getData());
        data.useAxes(xMetric, averageMetric);
        try {
            float[] color = GlimpseColor.fromColorHex(properties.getProperty(GraphVisualProperties.AVERAGE_COLOR));
            XYLinePainter painter = factory.getLinePainter(color);
            painter.setData(data.getXValues(), data.getYValues());
            plot.addPainter(painter);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
