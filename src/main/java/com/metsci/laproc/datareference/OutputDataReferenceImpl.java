package com.metsci.laproc.datareference;

import com.metsci.laproc.plotting.BasicGraph;
import com.metsci.laproc.plotting.Graph;
import com.metsci.laproc.plotting.GraphableData;
import com.metsci.laproc.pointmetrics.FalsePositiveRate;
import com.metsci.laproc.pointmetrics.IdentityFunction;
import com.metsci.laproc.pointmetrics.ParametricFunction;
import com.metsci.laproc.pointmetrics.TruePositiveRate;
import com.metsci.laproc.utils.Observable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by robinsat on 1/20/2017.
 */
public class OutputDataReferenceImpl extends Observable implements OutputDataReference{

    /** The internal collection of GraphableData */
    private Map<GraphableData, Boolean> allData;

    private ParametricFunction xAxisFunc;
    private ParametricFunction yAxisFunc;

    /** Constructor */
    public OutputDataReferenceImpl() {
        this.allData = new HashMap<GraphableData, Boolean>();

        xAxisFunc = new FalsePositiveRate();
        yAxisFunc = new TruePositiveRate();
    }

    /**
     * Adds a GraphableData object to this global set
     * @param data The GraphableData to add
     */
    public void addGraphableData(GraphableData data) {
        allData.put(data, true);
        this.notifyObservers();
    }

    /**
     * Deletes a GraphableData object from this global set
     * @param data The GraphableData to remove
     */
    public void deleteGraphableData(GraphableData data) {
        allData.remove(data);
        this.notifyObservers();
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
     * Returns a list of all possible axes to use for this graph
     * @return The list of axes that can be used for this graph
     */
    public Iterable<ParametricFunction> getAxisFunctions() {
        // This implementation uses a map to easily union all possible axes
        Map<String, ParametricFunction> functionUnion = new HashMap<String, ParametricFunction>();
        for(GraphableData d : this.allData.keySet()) {
            if(allData.get(d)) { // Only consider the functions that apply to currently displayed data sets
                List<ParametricFunction> axisFunctions = d.getAxes();
                for (ParametricFunction f : axisFunctions) {
                    functionUnion.put(f.getDescriptor(), f);
                }
            }
        }
        return functionUnion.values();
    }

    /**
     * Sets the current X and Y axis functions
     * @param xAxisFunction The X axis function
     * @param yAxisFunction The Y axis function
     */
    public void setAxisFunctions(ParametricFunction xAxisFunction, ParametricFunction yAxisFunction) {
        this.xAxisFunc = xAxisFunction;
        this.yAxisFunc = yAxisFunction;
        this.notifyObservers();
    }

    /**
     * Creates a Graph using the given functions as axes
     * @return A new Graph object using the given axes, displaying the appropriate data.
     */
    public Graph createGraph() {
        //TODO add error check here
        Graph graph = new BasicGraph();
        for(GraphableData data : this.getDisplayedData()) {
            System.out.println("data: " + data.getSize());
            data.useAxes(this.xAxisFunc, yAxisFunc);
            graph.addData(data);
        }
        return graph;
    }

}
