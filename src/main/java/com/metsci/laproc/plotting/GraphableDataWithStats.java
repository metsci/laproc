package com.metsci.laproc.plotting;

import java.util.ArrayList;
import java.util.List;

/**
 * A more complex GraphableData object than SimpleGraphableData
 * Internally stores data points with additional stats
 * Created by robinsat on 10/18/2016.
 */
public class GraphableDataWithStats implements GraphableData {

    /** The name of this Graphable Data set */
    private String name;
    /** A list of all the data points in this set */
    private List<DataPoint> data;

    public GraphableDataWithStats() {
        this.data = new ArrayList<DataPoint>();
    }

    /**
     * Getter for the name of this data set
     * @return the name of this data set
     */
    public String getName() {
        return this.name;
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
        double[] xVals = new double[this.data.size()];
        for(int i = 0; i < data.size(); i++) {
            xVals[i] = this.data.get(i).getX();
        }
        return xVals;
    }

    /**
     * Gets an array representing the y values as doubles
     * @return The set of y values
     */
    public double[] getYValues() {
        double[] yVals = new double[this.data.size()];
        for(int i = 0; i < data.size(); i++) {
            yVals[i] = this.data.get(i).getY();
        }
        return yVals;
    }

    /**
     * Adds a point to the set of values
     *
     * @param x The x value of the added point
     * @param y The y value of the added point
     */
    public void addPoint(double x, double y) {
        this.addPoint(new DataPoint(x, y));
    }

    /**
     * Adds a point to the set of values
     * @param dataPoint The point to add
     */
    public void addPoint(DataPoint dataPoint) {
        this.data.add(dataPoint);
    }

    /**
     * Gets the number of points in this data set
     * @return The number of points in this data set
     */
    public int getSize() {
        return this.data.size();
    }

    /**
     * Gets the graph point closest to the given x/y value
     *
     * @param x The given x value
     * @param y The given y value
     * @return The closest graph point to the given values
     */
    public DataPoint getDataPoint(double x, double y) {
        return null;
    }
}
