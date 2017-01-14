package com.metsci.laproc.plotting;

import com.metsci.laproc.pointmetrics.ParametricFunction;

/**
 * This is an interface representing a collection of all Graphable Data that has been calculated.
 *  GraphableDataSet is meant to be an intermediate step that is capable of creating graphs, and stores both active
 *  and hidden data.
 * Created by robinsat on 1/14/2017.
 */
public interface GraphableDataSet {

    /**
     * Adds a GraphableData object to this global set
     * @param data The GraphableData to add
     */
    void addGraphableData(GraphableData data);

    /**
     * Deletes a GraphableData object from this global set
     * @param data The GraphableData to remove
     */
    void deleteGraphableData(GraphableData data);

    /**
     * Gets all GraphableData stored in this set
     * @return All GraphableData stored in this set
     */
    Iterable<GraphableData> getAllData();

    /**
     * Gets only the displayed data sets
     * @return Only the displayed data sets
     */
    Iterable<GraphableData> getDisplayedData();

    /**
     * Gets only the hidden data sets
     * @return Only the hidden data sets
     */
    Iterable<GraphableData> getHiddenData();

    /**
     * Sets the given GraphableData object as displayed or active
     * @param data The data to set as displayed or active
     */
    void showData(GraphableData data);

    /**
     * Sets the given GraphableData object as hidden or inactive
     * @param data The data to set as hidden or inactive
     */
    void hideData(GraphableData data);

    /**
     * Returns a boolean indicating whether the given GraphableData is displayed
     * @param data The data to check
     * @return A boolean indicating whether the given GraphableData is displayed
     */
    boolean isDisplayed(GraphableData data);

    /**
     * Creates a Graph using the given functions as axes
     * @param xAxis The function to use for the xAxis
     * @param yAxis The function to use for the yAxis
     * @return A new Graph object using the given axes, displaying the appropriate data.
     */
    Graph createGraph(ParametricFunction xAxis, ParametricFunction yAxis);
}
