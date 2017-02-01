package com.metsci.laproc.plotting;

import com.metsci.laproc.pointmetrics.ParametricFunction;

import java.util.*;

/**
 * Represents a continuous line that can be represented on a graph.
 * Created by robinsat on 9/20/2016.
 */
public class BasicGraphableData implements GraphableData<Double> {

    //Fields
    /** The name of this set of data */
    private String name;

    /** The points in this data set */
    private SortedSet<GraphPoint> points;

    /**
     * Constructor
     * @param name The name to give to this graph data
     */
    public BasicGraphableData(String name) {
        this.name = name;
        this.points = new TreeSet<GraphPoint>(new XValueComparator());
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
        int count = 0;
        for(GraphPoint p : points) {
            xVals[count] = p.getX();
            count++;
        }
        return xVals;
    }

    /**
     * Gets an array representing the y values as doubles
     * @return The set of y values
     */
    public double[] getYValues() {
        double[] yVals = new double[points.size()];
        int count = 0;
        for(GraphPoint p : points) {
            yVals[count] = p.getY();
            count++;
        }
        return yVals;
    }

    /**
     * Gets an axis that represents the maximum and minimum x values
     * @return An axis that represents the maximum and minimum x values
     */
    public Axis getXBounds() {
        return new BasicAxis(points.first().getX(), points.last().getX());
    }

    /**
     * Gets an axis that represents the maximum and minimum x values
     * @return An axis that represents the maximum and minimum x values
     */
    public Axis getYBounds() {
        double yMin = 0; // Default lower bound
        double yMax = 1; // Default upper bound
        for(GraphPoint p : points) {
            double y = p.getY();
            if(y < yMin)
                yMin = y;
            if(y > yMax)
                yMax = y;
        }
        return new BasicAxis(yMin, yMax);
    }

    /**
     * Adds a point to the set of values
     * @param x The x value of the added point
     * @param y The y value of the added point
     */
    public void addPoint(double x, double y) {
        this.points.add(new BasicGraphPoint(x, y));
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
        GraphPoint closest = points.first();
        double closestDistance = Math.abs(x - closest.getX());

        for(GraphPoint p : points) {
            if(Math.abs(x - p.getX()) < closestDistance) {
                closestDistance = Math.abs(x - p.getX());
                closest = p;
            }
        }

        return closest;
    }

    /**
     * Gets the point with the largest x less than the given x value
     * @param x The given x value
     * @return
     */
    public GraphPoint getPointLessOrEqual(double x) {
        GraphPoint lastLessThan = null;

        for(GraphPoint p : points) {
            if(p.getX() > x )
                break;
            lastLessThan = p;
        }

        return lastLessThan;
    }

    /**
     * Gets the point with the smallest x greater than the given x value
     * @param x The given x value
     * @return
     */
    public GraphPoint getPointGreaterOrEqual(double x) {
        GraphPoint firstGreaterThan = null;
        for(GraphPoint p : points) {
            if(p.getX() >= x) {
                firstGreaterThan = p;
                break;
            }
        }
        return firstGreaterThan;
    }

    /**
     * Returns a list of axes on which this data may be plotted
     * @return A list of axes on which this data may be plotted
     */
    public List<ParametricFunction<Double>> getAxes() {
        return new ArrayList<ParametricFunction<Double>>();
    }

    /**
     * The next time getXValues and getYValues are called, the provided metrics are used to calculate these values
     * @param xAxis The metric to use for the x axis
     * @param yAxis The metric to use for the y axis
     */
    public void useAxes(ParametricFunction xAxis, ParametricFunction yAxis) {
       //For now, do nothing.
    }

    /**
     * Compares two GraphPoints based on their x value
     */
    private class XValueComparator implements Comparator<GraphPoint> {

        /**
         * Compares its two arguments for order.  Returns a negative integer,
         * zero, or a positive integer as the first argument is less than, equal
         * to, or greater than the second.
         */
        public int compare(GraphPoint p1, GraphPoint p2) {
            if (p1.getX() > p2.getX())
                return 1;
            else if (p1.getX() < p2.getX())
                return -1;
            //They are equal, compare them now by Y value
            if(p1.getY() > p2.getY())
                return 1;
            else if (p1.getY() < p2.getY())
                return -1;
            //They are identical
            return 0;
        }
    }

}
