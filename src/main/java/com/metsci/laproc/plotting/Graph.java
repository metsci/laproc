package com.metsci.laproc.plotting;

import com.metsci.laproc.pointmetrics.ParametricFunction;
import javafx.util.Pair;

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
     * Getter for all of the graphable data associated with the graph
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
     * Adds a Graphable Data to the graph
     * @param graphSet The data to add
     */
    void addData(GraphableData<?> graphSet);

    /**
     * Removes a Graphable Data
     * @param graphSet The data to remove
     */
	void removeData(GraphableData<?> graphSet);

    /**
     * Replace a Graphable Data set with another Graphable Data set
     * @param graphSet old graphable data set to replace
     * @param newGraphSet graphable data to replace the old graphable data set
     */
    void replaceData(GraphableData<?> graphSet, GraphableData<?> newGraphSet);
}
