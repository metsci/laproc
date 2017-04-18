package com.metsci.laproc.uicomponents;

import com.metsci.glimpse.painter.shape.PolygonPainter;
import com.metsci.glimpse.support.color.GlimpseColor;
import com.metsci.laproc.plotting.Axis;
import com.metsci.laproc.plotting.GraphableData;

/**
 * This extension of PolygonPainter allows one to use GraphableData as the bounds for shading
 * Created by robinsat on 2/7/2017.
 */
public class AreaShader extends PolygonPainter {

    /**
     * Constructor
     */
    public AreaShader() {
        super();
        this.setFill(0,true);
        float[] fillColor = GlimpseColor.fromColorRgb(0.6f,0.6f,0.6f);
        //Make polygon transparent
        fillColor[3] = 0.5f;
        this.setFillColor(0,fillColor);
        this.setShowLines(0,true);
        float[] lineColor = GlimpseColor.fromColorRgb(0f,0f,0f);
        this.setLineColor(0,lineColor);
        this.setLineWidth(0,2);
    }

    /**
     * Shades an area given a GraphableData object as the upper bound and a float as the lower bound
     * @param lowerBound The lower bound of the shaded area
     * @param upperBound The upper bound of the shaded area
     */
    public void drawArea(float lowerBound, GraphableData upperBound) {
        int size = upperBound.getSize() + 2;
        float[] areaX = new float[size];
        float[] areaY = new float[size];

        double[] xValues = upperBound.getXValues();
        double[] yValues = upperBound.getYValues();
        int i = 0;
        for(; i < xValues.length; i++) {
            areaX[i] = (float) xValues[i];
            areaY[i] = (float) yValues[i];
        }
        Axis bounds = upperBound.getXBounds();
        areaX[i] = (float) bounds.getMax();
        areaY[i] = lowerBound;
        i++;
        areaX[i] = (float) bounds.getMin();
        areaY[i] = lowerBound;

        this.addPolygon(0, 0, areaX, areaY, 0);
    }

    /**
     * Shades an area given a GraphableData object as the upper bound and a GraphableData object as the lower bound
     * @param lowerBound The lower bound of the shaded area
     * @param upperBound The upper bound of the shaded area
     */
    public void drawArea(GraphableData lowerBound, GraphableData upperBound) {
        int size = lowerBound.getSize() + upperBound.getSize();
        float[] areaX = new float[size];
        float[] areaY = new float[size];

        double[] xValues = upperBound.getXValues();
        double[] yValues = upperBound.getYValues();
        int i = 0;
        for(; i < xValues.length; i++) {
            areaX[i] = (float) xValues[i];
            areaY[i] = (float) yValues[i];
        }

        xValues = lowerBound.getXValues();
        yValues = lowerBound.getYValues();
        int lowerBoundSize = xValues.length - 1;

        for(int j = 0; j <= lowerBoundSize; j++) {
            areaX[i + j] = (float) xValues[lowerBoundSize - j];
            areaY[i + j] = (float) yValues[lowerBoundSize - j];
        }

        this.addPolygon(0, 0, areaX, areaY, 0);
    }

}
