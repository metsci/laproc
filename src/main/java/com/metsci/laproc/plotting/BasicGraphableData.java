package com.metsci.laproc.plotting;

import com.metsci.laproc.pointmetrics.Metric;
import com.metsci.laproc.pointmetrics.ParametricFunction;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a continuous line that can be represented on a graph.
 * Created by robinsat on 9/20/2016.
 */
public class BasicGraphableData implements GraphableData {

    //Fields
    /** The name of this set of data */
    private String name;
    /** The x values in this data set */
    //private double[] xValues;
    /** The y values in this data set */
    //private double[] yValues;

    private List<GraphPoint> points;

    //Internal use
    /** Keeps track of the current size of the data set */
    //private int size;
    /** The default size to initialize the array */
    //private static final int DEFAULT_SIZE = 100;

    //Constructors

    /**
     * Default constructor
     */
   /*  public BasicGraphableData() {
         this("", DEFAULT_SIZE);
     }

    /**
     * Constructor
     * @param name The name to give to this graph data
     */
    /**public BasicGraphableData(String name) {
        this(name, DEFAULT_SIZE);
    }

    /**
     * Constructor
     * @param name The number of points to initialize this data set with
     */
   /* public BasicGraphableData(int numPoints) {
        this("", numPoints);
    }

    /**
     * Constructor
     * @param name The name to give to this graph data
     * @param numPoints The number of points to initialize this data set with
     */
    public BasicGraphableData(String name) {
        this.name = name;
        /*this.size = 0;
        this.xValues = new double[numPoints];
        this.yValues = new double[numPoints];*/
        this.points = new ArrayList<GraphPoint>();

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
        double[] xVals = new double[points.size()];
        for(int i = 0; i < points.size(); i++) {
            xVals[i] = points.get(i).getX();
        }
        return xVals;
    }

    /**
     * Gets an array representing the y values as doubles
     * @return The set of y values
     */
    public double[] getYValues() {
        double[] yVals = new double[points.size()];
        for(int i = 0; i < points.size(); i++) {
            yVals[i] = points.get(i).getY();
        }
        return yVals;
    }

    /**
     * Adds a point to the set of values
     * @param x The x value of the added point
     * @param y The y value of the added point
     */
    public void addPoint(double x, double y) {
        this.points.add(new BasicGraphPoint(x, y));
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
        return this.points.size();
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
        double[] xValues = getXValues();
        double[] yValues = getYValues();
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
        BasicGraphPoint graphPoint = new BasicGraphPoint(xValues[closestIndex], yValues[closestIndex]);
        return graphPoint;
    }

    /**
     * Returns a list of axes on which this data may be plotted
     * @return A list of axes on which this data may be plotted
     */
    public List<ParametricFunction> getAxes() {
        return new ArrayList<ParametricFunction>();
    }

    /**
     * The next time getXValues and getYValues are called, the provided metrics are used to calculate these values
     * @param xAxis The metric to use for the x axis
     * @param yAxis The metric to use for the y axis
     */
    public void useAxes(ParametricFunction xAxis, ParametricFunction yAxis) {
       //For now, do nothing.
    }

}
