package com.metsci.laproc.plotting;

import com.metsci.laproc.pointmetrics.ParametricFunction;

/**
 * This interface represents a graph with customizable axes that can be rendered with Glimpse.
 * Created by robinsat on 9/19/2016.
 */
public interface Graph {

    /**
     * Getter for the graph's title
     * @return the title
     */
    String getTitle();

    /**
     * Getter for the graph's X axis
     * @return the X axis
     */
    Axis getXAxis();

    /**
     * Getter for the graph's Y axis
     * @return the Y axis
     */
    Axis getYAxis();

    /**
     * Sets the X axis descriptor to the given string
     * @param descriptor The string to set as the descriptor
     */
    void setXAxisDescriptor(String descriptor);

    /**
     * Sets the Y axis descriptor to the given string
     * @param descriptor The string to set as the descriptor
     */
    void setYAxisDescriptor(String descriptor);

    /**
     * Setter for the graph's title
     * @param title the title to set
     */
    void setTitle(String title);

    /**
     * Setter for selected graph data
     * @param data the data to set
     */
    void setSelectedData(GraphableData data);

    /**
     * Getter for selected graph data
     * @return selected graph data
     */
    GraphableData getSelectedData();

    /**
     * Getter for all of the graphable data associated with this graph
     * @return The graphable data associated with this graph
     */
    Iterable<GraphableData> getData();

    /**
     * Gets the closest value on the plot to the value provided. This is probably a point that was clicked
     * @param x The x value to compare against
     * @param y The y value to compare against
     * @return The closest value on the plot to the value provided.
     */
    GraphPoint[] getClosestPoints(double x, double y);

    /**
     * Returns a list of all possible axes to use for this graph
     * @return The list of axes that can be used for this graph
     */
    Iterable<ParametricFunction> getAxisFunctions();

    /**
     * Sets all GraphableData sets on this graph to use the same set of axes
     * @param xAxis The function to use for the X Axis
     * @param yAxis The function to use for the Y Axis
     */
    void useAxisFunctions(ParametricFunction xAxis, ParametricFunction yAxis);

    /**
     * Adds a graphable data item
     * @param dat The data to add
     */
    void addData(GraphableData dat);

}
