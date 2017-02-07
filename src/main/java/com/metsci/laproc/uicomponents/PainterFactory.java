package com.metsci.laproc.uicomponents;

import com.metsci.glimpse.painter.plot.XYLinePainter;
import com.metsci.glimpse.painter.shape.PolygonPainter;
import com.metsci.glimpse.support.color.GlimpseColor;

/**
 * A factory class to initialize painters with the provided defaults
 * Created by robinsat on 2/7/2017.
 */
public class PainterFactory {

    /** The properties to use when creating the painters */
    private GraphVisualProperties properties = new GraphVisualProperties();

    /**
     * Default Constructor
     */
    public PainterFactory() {
        this(new GraphVisualProperties());
    }

    /**
     * Constructor
     * @param properties The properties to use when constructing the painters
     */
    public PainterFactory(GraphVisualProperties properties) {
        this.properties = properties;
    }

    /**
     * Constructs an XY line painter
     * @return A new XYLinePainter instance
     */
    public XYLinePainter getLinePainter() {
        return getLinePainter(properties.getRandomColor());
    }

    /**
     * Constructs an XY line painter
     * @param lineColor The color to use for this line painter
     * @return A new XYLinePainter instance
     */
    public XYLinePainter getLinePainter(float[] lineColor) {
        XYLinePainter linePainter = new XYLinePainter();
        linePainter.setLineColor(lineColor);
        linePainter.setLineThickness(properties.getLineThickness());
        linePainter.showPoints(properties.shouldShowPoints());
        return linePainter;
    }

    /**
     * Constructs an Area Shader
     * @return A new AreaShader instance
     */
    public AreaShader getAreaShader () {
        return getAreaShader(properties.getShadeColor());
    }

    /**
     * Constructs an Area Shader
     * @param color The color to use for the shader
     * @return A new AreaShader instance
     */
    public AreaShader getAreaShader (float[] color) {
        AreaShader shader = new AreaShader();
        shader.setFill(0, true);
        //Make polygon transparent
        color[3] = GraphVisualProperties.SHADE_ALPHA;
        shader.setFillColor(0,color);
        shader.setShowLines(0,false);
        return shader;
    }
}
