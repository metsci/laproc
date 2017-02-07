package com.metsci.laproc.uicomponents;

import com.metsci.glimpse.support.color.GlimpseColor;

import java.util.Random;

/**
 * A class storing variables that will determine the graph's appearance
 * Created by robinsat on 2/7/2017.
 */
public class GraphVisualProperties {

    static final float[] defaultAverageColor = GlimpseColor.fromColorRgb(0f, 1f, 0f);
    static final float[] defaultShadeColor = GlimpseColor.fromColorRgb(0.6f,0.6f,0.6f);
    static final float[][] possibleColors = new float[8][4];
    static final float defaultLineThickness = 1.5f;

    public static final float SHADE_ALPHA = 0.5f;

    static {
        possibleColors[0] = GlimpseColor.fromColorRgb(0f, 0f, 0f);
        possibleColors[1] = GlimpseColor.fromColorRgb(1f, 0f, 0f);
        possibleColors[2] = GlimpseColor.fromColorRgb(0f, 0f, 1f);
        possibleColors[3] = GlimpseColor.fromColorRgb(0.2f, 0.5f, 0.5f);
        possibleColors[4] = GlimpseColor.fromColorRgb(0.4f, 0.4f, 0f);
        possibleColors[5] = GlimpseColor.fromColorRgb(1f, 0f, 1f);
        possibleColors[6] = GlimpseColor.fromColorRgb(0.4f, 0f, 0.4f);
    }

    /** The color used to display the vertical average */
    private float[] averageColor;
    /** The color used to shade a portion of the graph */
    private float[] shadeColor;
    /** The thickness of the line used to draw each data set */
    private float lineThickness;
    /** Whether or not to show the individual points on the graph */
    private boolean showPoints;

    /**
     * Constructor, initialized with defaults
     */
    public GraphVisualProperties() {
        averageColor = defaultAverageColor;
        shadeColor = defaultShadeColor;
        lineThickness = defaultLineThickness;
        showPoints = false;
    }

    /**
     * Getter for the line thickness value
     * @return The line thickness value
     */
    public float getLineThickness() {
        return lineThickness;
    }

    /**
     * Getter for the show points boolean value
     * @return The show points boolean value
     */
    public boolean shouldShowPoints() {
        return showPoints;
    }

    /**
     * Getter for the average color value
     * @return The average color value
     */
    public float[] getAverageColor() {
        return averageColor;
    }

    /**
     * Getter for the shade color value
     * @return The shade color value
     */
    public float[] getShadeColor() {
        return shadeColor;
    }

    /**
     * Setter for the line thickness value
     * @param newValue The line thickness value
     */
    public void setLineThickness(float newValue) {
        lineThickness = newValue;
    }

    /**
     * Setter for the show points boolean value
     * @param newValue The show points boolean value
     */
    public void setShowPoints(boolean newValue) {
        showPoints = newValue;
    }

    /**
     * Setter for the average color value
     * @param newColor The average color value
     */
    public void setAverageColor(float[] newColor) {
        averageColor = newColor;
    }

    /**
     * Setter for the shade color value
     * @param newColor The shade color value
     */
    public void setShadeColor(float[] newColor) {
        shadeColor = newColor;
    }

    /**
     * Generates a random color
     * @return A random color
     */
    public float[] getRandomColor() {
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();

        return GlimpseColor.fromColorRgb(r, g, b);
    }
}