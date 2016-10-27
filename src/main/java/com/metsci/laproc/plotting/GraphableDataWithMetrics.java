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
 * Represents data related to a ROC curve
 * Created by robinsat on 10/25/2016.
 */
public class GraphableDataWithMetrics implements GraphableData{

    private String name;
    private Metric xAxisMetric;
    private Metric yAxisMetric;
    private List<Metric> axes;
    private List<Metric> pointStatistics;

    private List<ClassifierSetPoint> points;

    public GraphableDataWithMetrics() {
        this("");
    }

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
     *
     * @return The set of y values
     */
    public double[] getYValues() {
        return getDataForMetric(this.yAxisMetric);
    }

    private double[] getDataForMetric(Metric m) {
        double[] values = new double[this.getSize()];
        for(int i = 0; i < values.length; i++) {
            values[i] = m.getMetric(points.get(i));
        }
        return values;
    }

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
     *
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

    private double findDistance(ClassifierSetPoint point, double x, double y) {
        double pointX = this.xAxisMetric.getMetric(point);
        double pointY = this.yAxisMetric.getMetric(point);
        return Point2D.distance(pointX, pointY, x, y);
    }

    public List<Metric> getAxes() {
        return Collections.unmodifiableList(this.axes);
    }

    protected void addAxisMetric(Metric m) {
        this.axes.add(m);
    }

    protected void addStatisticMetric(Metric m) {
        this.pointStatistics.add(m);
    }

    public void useAxes(Metric xAxis, Metric yAxis) {
        this.xAxisMetric = xAxis;
        this.yAxisMetric = yAxis;
    }

}
