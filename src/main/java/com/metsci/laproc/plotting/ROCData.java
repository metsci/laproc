package com.metsci.laproc.plotting;

import java.util.List;

/**
 * Represents data related to a ROC curve
 * Created by robinsat on 10/25/2016.
 */
public class ROCData implements GraphableData{

    private String name;
    private Metric xAxisMetric;
    private Metric yAxisMetric;

    private int numPositives;
    private int numNegatives;
    private int truePositives;
    private int trueNegatives;

    public ROCData() {
        this("");
    }

    public ROCData(String name) {
        this.name = name;

    }

    /**
     * Getter for the name of this data set
     * @return the name of this data set
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the name of this data set
     * @param name the name to set for this data set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets an array representing the x values as doubles
     * @return The set of x values
     */
    public double[] getXValues() {
        return new double[0];
    }

    /**
     * Gets an array representing the y values as doubles
     *
     * @return The set of y values
     */
    public double[] getYValues() {
        return new double[0];
    }

    /**
     * Adds a point to the set of values
     *
     * @param x The x value of the added point
     * @param y The y value of the added point
     */
    public void addPoint(double x, double y) {

    }

    /**
     * Adds a point to the set of values
     *
     * @param dp The point to add
     */
    public void addPoint(GraphPoint dp) {

    }

    /**
     * Gets the number of points in this data set
     *
     * @return The number of points in this data set
     */
    public int getSize() {
        return 0;
    }

    /**
     * Gets the graph point closest to the given x/y value
     *
     * @param x The given x value
     * @param y The given y value
     * @return The closest graph point to the given values
     */
    public GraphPoint getDataPoint(double x, double y) {
        return null;
    }

    public List<Metric> getAnalytics() {
        return null;
    }

    public void useMetrics(Metric xAxis, Metric yAxis) {

    }

    public int getNumPositives() {

    }
}
