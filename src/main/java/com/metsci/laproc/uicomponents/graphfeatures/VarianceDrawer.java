package com.metsci.laproc.uicomponents.graphfeatures;

import com.metsci.glimpse.plot.Plot2D;
import com.metsci.glimpse.support.color.GlimpseColor;
import com.metsci.laproc.plotting.*;
import com.metsci.laproc.uicomponents.AreaShader;
import com.metsci.laproc.uicomponents.GraphVisualProperties;
import com.metsci.laproc.uicomponents.PainterFactory;

import java.text.ParseException;

/**
 * Executes a composite function and draws the result to the graph as a shaded area around the mean
 * Created by robinsat on 2/7/2017.
 */
public class VarianceDrawer implements GraphFeature {

    /**
     * Constructor
     */
    public VarianceDrawer() {
    }

    /**
     * Applies this feature to the graph
     * @param graph The Graph object containing the data that will be drawn
     * @param plot  The graph on which to draw this feature
     * @param properties The properties to use when drawing the graph
     */
    public void applyToPlot(Graph graph, Plot2D plot, Legend legend, GraphVisualProperties properties) {
        PainterFactory factory = new PainterFactory(properties);
        CompositePointXMetric xMetric = new CompositePointXMetric();
        AverageMetric averageMetric = new AverageMetric();
        VarianceMetric varianceMetric = new VarianceMetric();
        CompositeFunction compositeFunction = new CompositeFunction();

        //TODO explicitly throw a class cast exception here if needed
        GraphableData<CompositePoint> data = compositeFunction.compute(graph.getData());
        data.useAxes(xMetric, averageMetric);
        double[] xValues = data.getXValues();
        double[] average = data.getYValues();
        data.useAxes(xMetric, varianceMetric);
        double[] variance = data.getYValues();

        BasicGraphableData upper = new BasicGraphableData("");
        BasicGraphableData lower = new BasicGraphableData("");
        for(int i = 0; i < data.getSize(); i++) {
            upper.addPoint(xValues[i], average[i] + variance[i]);
            lower.addPoint(xValues[i], average[i] - variance[i]);
        }
        try {
            float[] color = GlimpseColor.fromColorHex(properties.getProperty(GraphVisualProperties.AVERAGE_COLOR));
            AreaShader shader = factory.getAreaShader(color);
            plot.addPainter(shader);
            shader.drawArea(upper, lower);
            legend.addBlockEntry("Variance", color);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
