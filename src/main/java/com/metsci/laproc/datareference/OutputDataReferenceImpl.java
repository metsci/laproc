package com.metsci.laproc.datareference;

import com.metsci.laproc.plotting.BasicGraph;
import com.metsci.laproc.plotting.Graph;
import com.metsci.laproc.plotting.GraphableData;
import com.metsci.laproc.pointmetrics.FalsePositiveRate;
import com.metsci.laproc.pointmetrics.ParametricFunction;
import com.metsci.laproc.pointmetrics.TruePositiveRate;
import com.metsci.laproc.utils.Observable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Created by robinsat on 1/23/2017.
 */
public class OutputDataReferenceImpl extends Observable implements OutputDataReference {

    private Graph graph;

    /** The internal collection of GraphableData */
    private Map<GraphableData, Boolean> allData;

    private ParametricFunction xAxisFunc;
    private ParametricFunction yAxisFunc;

    public OutputDataReferenceImpl() {
        graph = new BasicGraph();

        this.allData = new HashMap<GraphableData, Boolean>();

        xAxisFunc = new FalsePositiveRate();
        yAxisFunc = new TruePositiveRate();
    }

    public Graph getGraph() {
        return this.graph;
    }

    /**
     * Adds a GraphableData object to this global set
     * @param data The GraphableData to add
     */
    public void addGraphableData(GraphableData data) {
        allData.put(data, true);
        this.graph.addData(data, true);
        this.notifyObservers();
    }

    public void removeDataFromGraph(GraphableData<?> graphSet) {
        this.graph.removeData(graphSet);
        notifyObservers();
    }

    public void replaceDataOnGraph(GraphableData<?> graphSet, GraphableData<?> newGraphSet) {
        this.graph.replaceData(graphSet, newGraphSet);
        notifyObservers();
    }

    /**
     * Sets the given GraphableData object as displayed or active
     * @param data The data to set as displayed or active
     */
    public void showData(GraphableData data) {
        if(allData.containsKey(data))
            allData.put(data, true);
        notifyObservers();
    }

    /**
     * Sets the given GraphableData object as hidden or inactive
     * @param data The data to set as hidden or inactive
     */
    public void hideData(GraphableData data) {
        if(allData.containsKey(data))
            allData.put(data, false);
        notifyObservers();
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
     * Gets only the displayed data sets
     * @return Only the displayed data sets
     */
    public Iterable<GraphableData> getDisplayedData() {
        return helpGetData(true);
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
}
