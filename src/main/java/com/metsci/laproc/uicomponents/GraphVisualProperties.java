package com.metsci.laproc.uicomponents;

import com.metsci.glimpse.support.color.GlimpseColor;

import java.util.Properties;
import java.util.Random;

/**
 * A class storing variables that will determine the graph's appearance
 * Created by robinsat on 2/7/2017.
 */
public class GraphVisualProperties extends Properties {

    public static final String AVERAGE_COLOR = "Average Color";
    public static final String SHADE_COLOR = "Shade Color";
    public static final String LINE_THICKNESS = "Line Thickness";
    public static final String SHOW_POINTS = "Show Points";
    public static final String LEGEND_OFFSET_X = "Legend Horizontal Offset";
    public static final String LEGEND_OFFSET_Y = "Legend Vertical Offset";
    public static final String LEGEND_WIDTH = "Legend Width";

    static final float[] defaultAverageColor = GlimpseColor.fromColorRgb(0f, 1f, 0f);
    static final float[] defaultShadeColor = GlimpseColor.fromColorRgb(0.6f,0.6f,0.6f);
    static final float[][] possibleColors = new float[8][4];
    static final float defaultLineThickness = 1.5f;
    static final int defaultLegendXOffset = 100;
    static final int defaultLegendYOffset = 10;
    static final int defaultLegendWidth = 60;


    public static final float SHADE_ALPHA = 0.25f;

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
     * Constructor, initialized with defaults
     */
    public GraphVisualProperties() {
        this.setProperty(AVERAGE_COLOR, GlimpseColor.toColorHex(defaultAverageColor));
        this.setProperty(SHADE_COLOR, GlimpseColor.toColorHex(defaultShadeColor));
        this.setProperty(LINE_THICKNESS, defaultLineThickness + "");
        this.setProperty(SHOW_POINTS, "false");
        this.setProperty(LEGEND_OFFSET_X, defaultLegendXOffset + "");
        this.setProperty(LEGEND_OFFSET_Y, defaultLegendYOffset + "");
        this.setProperty(LEGEND_WIDTH, defaultLegendWidth + "");
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