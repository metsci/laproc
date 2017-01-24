package com.metsci.laproc.datareference;

import com.metsci.laproc.plotting.Graph;
import com.metsci.laproc.plotting.GraphableData;
import com.metsci.laproc.utils.IObservable;

/**
 * This interface allows tools to access the graphable data calculated as the result of executing a function
 * Created by robinsat on 1/23/2017.
 */
public interface OutputDataReference extends IObservable {

    /**
     * Getter for the Graph object
     * @return The Graph object
     */
    Graph getGraph();

    /**
     * Adds a GraphableData object to this global set
     * @param data The GraphableData to add
     */
    void addGraphableData(GraphableData data);

    /**
     * Sets the data to be displayed on the graph, if the data is hidden
     * @param data The data to show
     */
    void showData(GraphableData data);

    /**
     * Sets the data to be hidden on the graph, if the data is shown
     * @param data The data to hide
     */
    void hideData(GraphableData data);

    /**
     * Returns a boolean indicating whether the given GraphableData is displayed
     * @param data The data to check
     * @return A boolean indicating whether the given GraphableData is displayed
     */
    boolean isDisplayed(GraphableData data);

    /**
     * Removes a GraphableData from the graph
     * @param graphSet The set to remove
     */
    void deleteGraphableData(GraphableData<?> graphSet);

    /**
     * Replace a given graphable data set on the graph with a new graphable data set
     * @param graphSet old graphable data set to replace
     * @param newGraphSet new graphable data set used to replace the old data set
     */
    void replaceDataOnGraph(GraphableData<?> graphSet, GraphableData<?> newGraphSet);

    /**
     * Gets all the data sets that have been computed
     * @return All the data sets that have been computed
     */
    Iterable<GraphableData> getAllData();

    /**
     * Gets only the displayed data sets
     * @return Only the displayed data sets
     */
    Iterable<GraphableData> getDisplayedData();



}
