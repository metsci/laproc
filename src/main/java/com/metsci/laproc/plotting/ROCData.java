package com.metsci.laproc.plotting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents data related to a ROC curve
 * Created by robinsat on 10/25/2016.
 */
public class ROCData implements GraphableData{

    private String name;
    private Metric xAxisMetric;
    private Metric yAxisMetric;
    private List<Metric> pointMetrics;

    private List<ClassifierSetPoint> points;

    public ROCData() {
        this("");
    }

    public ROCData(String name) {
        this.name = name;
        this.pointMetrics = new ArrayList<Metric>();
        this.points = new ArrayList<ClassifierSetPoint>();

        this.xAxisMetric = new FalsePositiveRate();
        this.yAxisMetric = new TruePositiveRate();
        this.pointMetrics.add(this.xAxisMetric);
        this.pointMetrics.add(this.yAxisMetric);
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

    public void addClassifierSetPoint(ClassifierSetPoint pt) {
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
        return null;
    }

    public List<Metric> getAnalytics() {
        return Collections.unmodifiableList(this.pointMetrics);
    }

    public void useMetrics(Metric xAxis, Metric yAxis) {
        this.xAxisMetric = xAxis;
        this.yAxisMetric = yAxis;
    }

}
