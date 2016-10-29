package com.metsci.laproc.plotting;

import com.metsci.laproc.pointmetrics.Metric;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a continuous line that can be represented on a graph.
 * Created by robinsat on 9/20/2016.
 */
public class SimpleGraphableData implements GraphableData {

    //Fields
    /** The name of this set of data */
    private String name;
    /** The x values in this data set */
    private double[] xValues;
    /** The y values in this data set */
    private double[] yValues;

    //Internal use
    /** Keeps track of the current size of the data set */
    private int size;
    /** The default size to initialize the array */
    private static final int DEFAULT_SIZE = 100;

    //Constructors

    /**
     * Default constructor
     */
     public SimpleGraphableData() {
         this("", DEFAULT_SIZE);
     }

    /**
     * Constructor
     * @param name The name to give to this graph data
     */
    public SimpleGraphableData(String name) {
        this(name, DEFAULT_SIZE);
    }

    /**
     * Constructor
     * @param numPoints The number of points to initialize this data set with
     */
    public SimpleGraphableData(int numPoints) {
        this("", numPoints);
    }

    /**
     * Constructor
     * @param name The name to give to this graph data
     * @param numPoints The number of points to initialize this data set with
     */
    public SimpleGraphableData(String name, int numPoints) {
        this.name = name;
        this.size = 0;
        this.xValues = new double[numPoints];
        this.yValues = new double[numPoints];
    }

    /**
     * Getter for the name of this graph data
     * @return The name of this data
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the name of this data set
     * @param name The name to set for this data set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets an array representing the x values as doubles
     * @return The set of x values
     */
    public double[] getXValues() {
        if(this.xValues.length != size) {
            this.xValues = resize(this.xValues, size);
        }
        return this.xValues;
    }

    /**
     * Gets an array representing the y values as doubles
     * @return The set of y values
     */
    public double[] getYValues() {
        if(this.yValues.length != size) {
            this.yValues = resize(this.yValues, size);
        }
        return this.yValues;
    }

    /**
     * Adds a point to the set of values
     * @param x The x value of the added point
     * @param y The y value of the added point
     */
    public void addPoint(double x, double y) {
        if(size >= this.xValues.length || size >= this.yValues.length) {
            //Grow
            this.xValues = resize(this.xValues, size * 2);
            this.yValues = resize(this.yValues, size * 2);
        }

        this.xValues[size] = x;
        this.yValues[size] = y;
        size++;
    }

    public void addPoint(GraphPoint dataPoint) {
        addPoint(dataPoint.getX(), dataPoint.getY());
    }

    /**
     * Private helper method to resize an array
     * @param original The original array
     * @param newsize The capacity of the new array
     * @return A new array with the elements copied over
     */
    private double[] resize(double[] original, int newsize) {
        double[] newArray = new double[newsize];
        for(int i = 0; i < original.length && i < newsize; i++) {
            newArray[i] = original[i];
        }
        return newArray;
    }

    /**
     * Gets the number of points in this data set
     * @return The number of points in this data set
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Returns a data point
     * @param x The given x value
     * @param y The given y value
     * @return The closest graph point to the given values
     */
    public GraphPoint getDataPoint(double x, double y) {
        //Find the closest point in the set
        int closestIndex = 0;
        double closestDistance = Point2D.distance(xValues[0], yValues[0], x, y);
        double currentDistance;
        for(int i = 0; i < this.getSize(); i++) {
            currentDistance = Point2D.distance(xValues[i], yValues[i], x, y);
            if(currentDistance < closestDistance) { // This point is closer than the last closest point
                closestDistance = currentDistance;
                closestIndex = i;
            }
        }

        // Now that the closest point has been found, construct a point object to pass back
        SimpleGraphPoint graphPoint = new SimpleGraphPoint(xValues[closestIndex], yValues[closestIndex]);
        return graphPoint;
    }

    /**
     * Returns a list of axes on which this data may be plotted
     * @return A list of axes on which this data may be plotted
     */
    public List<Metric> getAxes() {
        return new ArrayList<Metric>();
    }

    /**
     * The next time getXValues and getYValues are called, the provided metrics are used to calculate these values
     * @param xAxis The metric to use for the x axis
     * @param yAxis The metric to use for the y axis
     */
    public void useAxes(Metric xAxis, Metric yAxis) {
       //For now, do nothing.
    }

}
