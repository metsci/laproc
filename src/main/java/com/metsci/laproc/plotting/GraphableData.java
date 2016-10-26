package com.metsci.laproc.plotting;

/**
 * Represents a set of data points to be plotted on a graph
 * Created by robinsat on 9/20/2016.
 */
public interface GraphableData {

    /**
     * Getter for the name of this data set
     * @return the name of this data set
     */
    public String getName();

    /**
     * Setter for the name of this data set
     * @param name the name to set for this data set
     */
    public void setName(String name);

    /**
     * Gets an array representing the x values as doubles
     * @return The set of x values
     */
    public double[] getXValues();

    /**
     * Gets an array representing the y values as doubles
     * @return The set of y values
     */
    public double[] getYValues();

    /**
     * Adds a point to the set of values
     * @param x The x value of the added point
     * @param y The y value of the added point
     */
    public void addPoint(double x, double y);

    /**
     * Adds a point to the set of values
     * @param dp The point to add
     */
    public void addPoint(GraphPoint dp);

    /**
     * Gets the number of points in this data set
     * @return The number of points in this data set
     */
    public int getSize();

    /**
     * Gets the graph point closest to the given x/y value
     * @param x The given x value
     * @param y The given y value
     * @return The closest graph point to the given values
     */
    public GraphPoint getDataPoint(double x, double y);

}
