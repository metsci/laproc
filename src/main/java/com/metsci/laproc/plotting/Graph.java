package com.metsci.laproc.plotting;

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
     * @param title the title axis to set
     */
     void setTitle(String title);

    /**
     * Getter for all of the graphable data associated with the graph
     * @return The graphable data associated with this graph
     */
    List<GraphableData> getData();

    /**
     * Gets the closest value on the plot to the value provided. This is probably a point that was clicked
     * @param x The x value to compare against
     * @param y The y value to compare against
     * @return The closest value on the plot to the value provided.
     */
    GraphPoint[] getClosestPoints(double x, double y);

    /**
     * Adds a Graphable Data to the graph
     * @param graphSet The data to add
     */
    void addData(GraphableData<?> graphSet);

    /**
     * Removes a Graphable Data
     * @param graphSet The data to remove
     */
	void removeData(GraphableData<?> graphSet);
}
