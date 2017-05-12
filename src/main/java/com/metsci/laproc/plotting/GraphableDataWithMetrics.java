package com.metsci.laproc.plotting;

import com.metsci.laproc.pointmetrics.*;

import java.util.*;

/**
 * Represents a set of graphable data that may be plotted on different axes
 * Created by robinsat on 10/25/2016.
 */
public class GraphableDataWithMetrics<T> implements GraphableData<T>{

    /** The displayable name of this set of data */
    private String name;
    /** The metric to use for the x axis */
    private ParametricFunction<T> xAxisMetric;
    /** The metric to use for the y axis */
    private ParametricFunction<T> yAxisMetric;
    /** All possible metrics that may be used for this data set */
    private List<ParametricFunction<T>> axes;
    /** All metrics that will be used to generate displayable statistics for each GraphPoint */
    private List<ParametricFunction<T>> pointStatistics;

    /** Tha actual data, stored as some type T The x and y values can all be derived from this data */
    private SortedSet<T> points;

    /**
     * Constructor
     * @param name The name to give to this set of data
     * @param defaultXMetric The default X axis metric
     * @param defaultYMetric The default Y axis metric
     */
    public GraphableDataWithMetrics(String name, ParametricFunction<T> defaultXMetric,
                                    ParametricFunction<T> defaultYMetric) {
        this.name = name;
        this.axes = new ArrayList<ParametricFunction<T>>();
        this.pointStatistics = new ArrayList<ParametricFunction<T>>();
        this.xAxisMetric = defaultXMetric;
        this.yAxisMetric = defaultYMetric;

        this.axes.add(this.xAxisMetric);
        this.axes.add(this.yAxisMetric);
        this.points = new TreeSet<T>(new MetricComparator<T>(this.xAxisMetric));
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
        return getDataForMetric(this.xAxisMetric);
    }

    /**
     * Gets an array representing the y values as doubles
     * @return The set of y values
     */
    public double[] getYValues() {
        return getDataForMetric(this.yAxisMetric);
    }

    /**
     * A helper method to reuse code for getXValues and getYValues
     * @param m The metric to use for this data
     * @return A set of doubles based on the metric's calculations
     */
    private double[] getDataForMetric(ParametricFunction<T> m) {
        double[] values = new double[this.getSize()];
        int count = 0;
        for(T point : points) {
            values[count] = m.compute(point);
            count++;
        }
        return values;
    }

    /**
     * Gets an axis that represents the maximum and minimum x values
     * @return An axis that represents the maximum and minimum x values
     */
    public Axis getXBounds() {
        double[] x = getXValues();
        return new BasicAxis(x[0], x[x.length - 1], xAxisMetric.getDescriptor());
    }

    /**
     * Gets an axis that represents the maximum and minimum x values
     * @return An axis that represents the maximum and minimum x values
     */
    public Axis getYBounds() {
        double[] y = getYValues();
        Arrays.sort(y);
        return new BasicAxis(y[0], y[y.length - 1], yAxisMetric.getDescriptor());
    }

    /**
     * Add a point to this data set
     * @param pt The point to add
     */
    protected void addDataPoint(T pt) {
        this.points.add(pt);
    }

    /**
     * Gets the number of points in this data set
     * @return The number of points in this data set
     */
    public int getSize() {
        return this.points.size();
    }

    /**
     * Gets the graph point closest to the given x/y value
     * @param x The given x value
     * @param y The given y value
     * @return The closest graph point to the given values
     */
    public GraphPoint getDataPoint(double x, double y) {
        //Find the closest point in the set
        T closest = points.first();
        double closestDistance = findDistance(closest, x);
        double currentDistance;
        for(T p : points) {
            currentDistance = findDistance(p, x);
            if(currentDistance < closestDistance) { // This point is closer than the last closest point
                closestDistance = currentDistance;
                closest = p;
            }
        }

        // Now that the closest point has been found, construct a point object to pass back
        BasicGraphPoint graphPoint = new BasicGraphPoint(xAxisMetric.compute(closest),
                yAxisMetric.compute(closest));
        // Add all additional statistics/analytics
        for(ParametricFunction<T> m : this.pointStatistics) {
            graphPoint.addStatistic(m.getDescriptor(), m.compute(closest));
        }

        // Return the graph point
        return graphPoint;
    }

    /**
     * Gets the point with the largest x less than the given x value
     * @param x The given x value
     * @return data point with the largest x less than the given x value
     */
    public GraphPoint getPointLessOrEqual(double x) {
        T lastLessThan = null;

        for(T p : points) {
            if(xAxisMetric.compute(p) > x )
                break;
            lastLessThan = p;
        }
        if(lastLessThan != null)
            return new BasicGraphPoint(xAxisMetric.compute(lastLessThan), yAxisMetric.compute(lastLessThan));
        return null;
    }

    /**
     * Gets the point with the smallest x greater than the given x value
     * @param x The given x value
     * @return data point with the smallest x greater than the given x value
     */
    public GraphPoint getPointGreaterOrEqual(double x) {
        T firstGreaterThan = null;
        for(T p : points) {
            if(xAxisMetric.compute(p) > x) {
                firstGreaterThan = p;
                break;
            }
        }
        if(firstGreaterThan != null)
            return new BasicGraphPoint(xAxisMetric.compute(firstGreaterThan), yAxisMetric.compute(firstGreaterThan));
        return null;
    }

    /**
     * Helper method to find the distance between two points
     * @param point The point used to calculate the first point
     * @param x The x value of the second point
     * @return The distance between the two points
     */
    private double findDistance(T point, double x) {
        double pointX = this.xAxisMetric.compute(point);
        return Math.abs(pointX - x);
    }

    /**
     * Returns a list of axes on which this data can be plotted
     * @return A list of axes on which this data can be plotted
     */
    public List<ParametricFunction<T>> getAxes() {
        return Collections.unmodifiableList(this.axes);
    }

    /**
     * Adds a metric to this list of axis possibilities. Accessible within the package.
     * @param m The axis to add
     */
    protected void addAxisMetric(ParametricFunction<T> m) {
        this.axes.add(m);
    }

    /**
     * Adds a metric to the list of statistics derived for each point in the set.
     * These metrics are for display purposes only
     * @param m The metric to add
     */
    protected void addStatisticMetric(ParametricFunction<T> m) {
        this.pointStatistics.add(m);
    }

    /**
     * The next time getXValues or getYValues is called, the values will be calculated using these metrics
     * @param xAxis The metric to use for the x axis
     * @param yAxis The metric to use for the y axis
     */
    public void useAxes(ParametricFunction<T> xAxis, ParametricFunction<T> yAxis) {
        this.xAxisMetric = xAxis;
        this.yAxisMetric = yAxis;

        SortedSet<T> resort = new TreeSet<T>(new MetricComparator<T>(xAxis));
        resort.addAll(this.points);
        this.points = resort;
    }
}
