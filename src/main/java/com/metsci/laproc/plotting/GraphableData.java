package com.metsci.laproc.plotting;

import com.metsci.laproc.pointmetrics.ParametricFunction;

import java.util.List;

/**
 * Represents a set of data points to be plotted on a graph
 * Created by robinsat on 9/20/2016.
 */
public interface GraphableData<T> {

    /**
     * Getter for the name of this data set
     * @return the name of this data set
     */
    String getName();

    /**
     * Setter for the name of this data set
     * @param name the name to set for this data set
     */
    void setName(String name);

    /**
     * Gets an array representing the x values as doubles
     * @return The set of x values
     */
    double[] getXValues();

    /**
     * Gets an array representing the y values as doubles
     * @return The set of y values
     */
    double[] getYValues();

    /**
     * Gets an axis that represents the maximum and minimum x values
     * @return An axis that represents the maximum and minimum x values
     */
    Axis getXBounds();

    /**
     * Gets an axis that represents the maximum and minimum x values
     * @return An axis that represents the maximum and minimum x values
     */
    Axis getYBounds();

    /**
     * Gets the number of points in this data set
     * @return The number of points in this data set
     */
    int getSize();

    /**
     * Gets the graph point closest to the given x/y value
     * @param x The given x value
     * @param y The given y value
     * @return The closest graph point to the given values
     */
    GraphPoint getDataPoint(double x, double y);

    /**
     * Gets the list of metrics that can be used as axes for this data
     * @return The list of metrics that can be used as axes for this data
     */
    List<ParametricFunction<T>> getAxes();

    /**
     * Sets the x axis and y axis to the specified metrics
     * @param xAxis The metric to use for the x axis
     * @param yAxis The metric to use for the y axis
     */
    void useAxes(ParametricFunction<T> xAxis, ParametricFunction<T> yAxis);
}
