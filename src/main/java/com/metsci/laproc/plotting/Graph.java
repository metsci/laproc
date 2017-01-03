package com.metsci.laproc.plotting;

import com.metsci.laproc.pointmetrics.ParametricFunction;

import java.util.List;

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
     * Getter for all of the displayed graphable data associated with the graph
     * @return The graphable data associated with this graph
     */
    Iterable<GraphableData> getDisplayedData();

    /**
     * Getter for all of the hidden graphable data associated with the graph
     * @return The graphable data associated with this graph
     */
    Iterable<GraphableData> getHiddenData();

    /**
     * Getter for all of the graphable data associated with the graph
     * @return The graphable data associated with this graph
     */
    List<GraphableData> getData();

    /**
     * Getter for the associated displayed rows for graphable data in the graph
     * @return The graphable data associated with this graph
     */
    List<Boolean> getDisplayed();

    /**
     * Gets the closest value on the plot to the value provided. This is probably a point that was clicked
     * @param x The x value to compare against
     * @param y The y value to compare against
     * @return The closest value on the plot to the value provided.
     */
    GraphPoint[] getDisplayedClosestPoints(double x, double y);

    /**
     * Returns a list of all possible axes to use for this graph
     * @return The list of axes that can be used for this graph
     */
    Iterable<ParametricFunction> getDisplayedAxisFunctions();

    /**
     * Sets all GraphableData sets on this graph to use the same set of axes
     * @param xAxis The function to use for the X Axis
     * @param yAxis The function to use for the Y Axis
     */
    void useAxisFunctions(ParametricFunction xAxis, ParametricFunction yAxis);

    /**
     * Adds a Graphable Data to the graph
     * @param graphSet The data to add
     * @param display true if the data should be displayed
     */
    void addData(GraphableData<?> graphSet, boolean display);

    /**
     * Sets a Graphable Data to be displayed or hidden
     * @param graphSet The data to set
     * @param display true if the data should be displayed
     */
    void setDataDisplay(GraphableData<?> graphSet, boolean display);

    /**
     * Removes a Graphable Data
     * @param graphSet The data to remove
     */
	void removeData(GraphableData<?> graphSet);

}
