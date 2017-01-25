package com.metsci.laproc.datareference;

import com.metsci.laproc.plotting.Graph;
import com.metsci.laproc.plotting.GraphableData;
import com.metsci.laproc.pointmetrics.ParametricFunction;
import com.metsci.laproc.utils.IObservable;

/**
 * This reference allows tools to access the graph and associated data
 * Created by robinsat on 1/20/2017.
 */
public interface OutputDataReference extends IObservable {
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
    //Iterable<GraphableData> getHiddenData();

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
     * Returns a list of all possible axes to use for this graph
     * @return The list of axes that can be used for this graph
     */
    Iterable<ParametricFunction> getAxisFunctions();

    /**
     * Sets the current X and Y axis functions
     * @param xAxisFunction The X axis function
     * @param yAxisFunction The Y axis function
     */
    void setAxisFunctions(ParametricFunction xAxisFunction, ParametricFunction yAxisFunction);

    /**
     * Creates a Graph using the given functions as axes
     * @return A new Graph object using the given axes, displaying the appropriate data.
     */
    Graph createGraph();
}
