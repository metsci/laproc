package com.metsci.laproc.plotting;

import com.metsci.laproc.pointmetrics.ParametricFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Implementation of the GraphableDataSet interface
 * Created by robinsat on 1/14/2017.
 */
public class BasicGraphableDataSet implements GraphableDataSet {

    /** The internal collection of GraphableData */
    private Map<GraphableData, Boolean> allData;

    /**
     * Adds a GraphableData object to this global set
     * @param data The GraphableData to add
     */
    public void addGraphableData(GraphableData data) {
        allData.put(data, true);
    }

    /**
     * Deletes a GraphableData object from this global set
     * @param data The GraphableData to remove
     */
    public void deleteGraphableData(GraphableData data) {
        allData.remove(data);
    }

    /**
     * Gets all GraphableData stored in this set
     * @return All GraphableData stored in this set
     */
    public Iterable<GraphableData> getAllData() {
        return allData.keySet();
    }

    /**
     * Gets only the displayed data sets
     * @return Only the displayed data sets
     */
    public Iterable<GraphableData> getDisplayedData() {
        return helpGetData(true);
    }

    /**
     * Gets only the hidden data sets
     * @return Only the hidden data sets
     */
    public Iterable<GraphableData> getHiddenData() {
        return helpGetData(false);
    }

    /**
     * A private helper function that returns the data with the given boolean value in the map
     * @param shown The boolean value for which to retrieve the data
     * @return The set of data matching the boolean parameter
     */
    private Iterable<GraphableData> helpGetData(boolean shown) {
        List<GraphableData> dataList = new ArrayList<GraphableData>();
        for(GraphableData d : allData.keySet()) {
            if(allData.get(d) == shown)
                dataList.add(d);
        }
        return dataList;
    }

    /**
     * Sets the given GraphableData object as displayed or active
     * @param data The data to set as displayed or active
     */
    public void showData(GraphableData data) {
        if(allData.containsKey(data))
            allData.put(data, true);
    }

    /**
     * Sets the given GraphableData object as hidden or inactive
     * @param data The data to set as hidden or inactive
     */
    public void hideData(GraphableData data) {
        if(allData.containsKey(data))
            allData.put(data, false);
    }

    /**
     * Returns a boolean indicating whether the given GraphableData is displayed
     * @param data The data to check
     * @return A boolean indicating whether the given GraphableData is displayed
     */
    public boolean isDisplayed(GraphableData data) {
        return allData.get(data);
    }

    /**
     * Creates a Graph using the given functions as axes
     *
     * @param xAxis The function to use for the xAxis
     * @param yAxis The function to use for the yAxis
     * @return A new Graph object using the given axes, displaying the appropriate data.
     */
    public Graph createGraph(ParametricFunction xAxis, ParametricFunction yAxis) {
        Graph graph = new BasicGraph();
        for(GraphableData data : this.getDisplayedData()) {
            data.useAxes(xAxis, yAxis);
            graph.addData(data, true);
        }
        return graph;
    }
}
