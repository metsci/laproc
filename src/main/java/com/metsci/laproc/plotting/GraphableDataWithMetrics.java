package com.metsci.laproc.plotting;

import com.metsci.laproc.pointmetrics.ClassifierSetPoint;
import com.metsci.laproc.pointmetrics.FalsePositiveRate;
import com.metsci.laproc.pointmetrics.Metric;
import com.metsci.laproc.pointmetrics.TruePositiveRate;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a set of graphable data that may be plotted on different axes
 * Created by robinsat on 10/25/2016.
 */
public class GraphableDataWithMetrics implements GraphableData{

    /** The displayable name of this set of data */
    private String name;
    /** The metric to use for the x axis */
    private Metric xAxisMetric;
    /** The metric to use for the y axis */
    private Metric yAxisMetric;
    /** All possible metrics that may be used for this data set */
    private List<Metric> axes;
    /** All metrics that will be used to generate displayable statistics for each GraphPoint */
    private List<Metric> pointStatistics;

    /** Tha actual data, stored as ClassifierSetPoints. The x and y values can all be derived from this data */
    private List<ClassifierSetPoint> points;

    /**
     * Default Constructor
     */
    public GraphableDataWithMetrics() {
        this("");
    }

    /**
     * Constructor
     * @param name The name to give to this set of data
     */
    public GraphableDataWithMetrics(String name) {
        this.name = name;
        this.axes = new ArrayList<Metric>();
        this.pointStatistics = new ArrayList<Metric>();
        this.points = new ArrayList<ClassifierSetPoint>();

        //TODO This is the default for a ROC curve. Eventually, the default behavior should be established in settings
        this.xAxisMetric = new FalsePositiveRate();
        this.yAxisMetric = new TruePositiveRate();
        this.axes.add(this.xAxisMetric);
        this.axes.add(this.yAxisMetric);
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
     * @return A set of doubles based on the metrivc's calculations
     */
    private double[] getDataForMetric(Metric m) {
        double[] values = new double[this.getSize()];
        for(int i = 0; i < values.length; i++) {
            values[i] = m.getMetric(points.get(i));
        }
        return values;
    }

    /**
     * Add a point to this data set based on the classifier data
     * @param pt The point to add
     */
    protected void addClassifierSetPoint(ClassifierSetPoint pt) {
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
        ClassifierSetPoint closest = points.get(0);
        double closestDistance = findDistance(closest, x, y);
        double currentDistance;
        for(int i = 0; i < this.getSize(); i++) {
            currentDistance = findDistance(points.get(i), x, y);
            if(currentDistance < closestDistance) { // This point is closer than the last closest point
                closestDistance = currentDistance;
                closest = points.get(i);
            }
        }

        // Now that the closest point has been found, construct a point object to pass back
        SimpleGraphPoint graphPoint = new SimpleGraphPoint(xAxisMetric.getMetric(closest),
                yAxisMetric.getMetric(closest));
        // Add all additional statistics/analytics
        for(Metric m : this.pointStatistics) {
            graphPoint.addStatistic(m.getDescriptor(), m.getMetric(closest));
        }

        // Return the graph point
        return graphPoint;
    }

    /**
     * Helper method to find the distance between two points
     * @param point The classifiersetpoint used to calculate the first point
     * @param x The x value of the second point
     * @param y The y value of the second point
     * @return The distance between the two points
     */
    private double findDistance(ClassifierSetPoint point, double x, double y) {
        double pointX = this.xAxisMetric.getMetric(point);
        double pointY = this.yAxisMetric.getMetric(point);
        return Point2D.distance(pointX, pointY, x, y);
    }

    /**
     * Returns a list of axes on which this data can be plotted
     * @return A list of axes on which this data can be plotted
     */
    public List<Metric> getAxes() {
        return Collections.unmodifiableList(this.axes);
    }

    /**
     * Adds a metric to this list of axis possibilities. Accessible within the package.
     * @param m The axis to add
     */
    protected void addAxisMetric(Metric m) {
        this.axes.add(m);
    }

    /**
     * Adds a metric to the list of statistics derived for each point in the set.
     * This metrics are for display purposes only
     * @param m The metric to add
     */
    protected void addStatisticMetric(Metric m) {
        this.pointStatistics.add(m);
    }

    /**
     * The next time getXValues or getYValues is called, the values will be calculated using these metrics
     * @param xAxis The metric to use for the x axis
     * @param yAxis The metric to use for the y axis
     */
    public void useAxes(Metric xAxis, Metric yAxis) {
        this.xAxisMetric = xAxis;
        this.yAxisMetric = yAxis;
    }

}
