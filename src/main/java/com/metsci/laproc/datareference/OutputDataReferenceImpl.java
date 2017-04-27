package com.metsci.laproc.datareference;

import com.metsci.laproc.plotting.BasicGraph;
import com.metsci.laproc.plotting.Graph;
import com.metsci.laproc.plotting.GraphableData;
import com.metsci.laproc.pointmetrics.FalsePositiveRate;
import com.metsci.laproc.pointmetrics.ParametricFunction;
import com.metsci.laproc.pointmetrics.TruePositiveRate;
import com.metsci.laproc.utils.Observable;

import java.util.*;

/**
 * This class allows tools to access the graphable data calculated as the result of executing a function
 *
 * Created by robinsat on 1/23/2017.
 */
public class OutputDataReferenceImpl extends Observable implements OutputDataReference {

    /** The internal collection of GraphableData */
    private Map<GraphableData, Boolean> allData;

    private ParametricFunction xAxisFunc;
    private ParametricFunction yAxisFunc;

    /**
     * Default constructor for an output data reference
     */
    public OutputDataReferenceImpl() {
        this.allData = new LinkedHashMap<GraphableData, Boolean>();

        xAxisFunc = new FalsePositiveRate();
        yAxisFunc = new TruePositiveRate();
    }

    /**
     * Adds a GraphableData object to this global set
     * @param data The GraphableData to add
     */
    public void addGraphableData(GraphableData data) {
        if(!allData.containsKey(data)) {
            allData.put(data, true);
            this.notifyObservers();
        }
    }

    public void deleteGraphableData(GraphableData<?> data) {
        if(allData.containsKey(data)) {
            allData.remove(data);
            notifyObservers();
        }
    }

    public void replaceDataOnGraph(GraphableData<?> graphSet, GraphableData<?> newGraphSet) {
        if(!allData.keySet().contains(newGraphSet) && allData.keySet().contains(graphSet)) {
            boolean display = allData.get(graphSet);
            allData.remove(graphSet);
            allData.put(newGraphSet, display);
            notifyObservers();
        }
    }

    /**
     * Sets the given GraphableData object as displayed or active
     * @param data The data to set as displayed or active
     */
    public void showData(GraphableData data) {
        if(allData.containsKey(data) && !allData.get(data)) {
            allData.put(data, true);
            notifyObservers();
        }
    }

    /**
     * Sets the given GraphableData object as hidden or inactive
     * @param data The data to set as hidden or inactive
     */
    public void hideData(GraphableData data) {
        if(allData.containsKey(data)  && allData.get(data)) {
            allData.put(data, false);
            notifyObservers();
        }
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
     * Gets all the data sets that have been computed
     * @return All the data sets that have been computed
     */
    public Iterable<GraphableData> getAllData() {
        return this.allData.keySet();
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

    /**
     * Returns a list of all possible axes to use for this graph
     * @return The list of axes that can be used for this graph
     */
    public Collection<ParametricFunction> getAxisFunctions() {
        // This implementation uses a map to easily union all possible axes
        Map<String, ParametricFunction> functionUnion = new HashMap<String, ParametricFunction>();
        for(GraphableData d : this.getAllData()) {
            List<ParametricFunction> axisFunctions = d.getAxes();
            for(ParametricFunction f : axisFunctions) {
                functionUnion.put(f.getDescriptor(), f);
            }
        }
        return functionUnion.values();
    }

    /**
     * Gets the function currently being used for the X axis
     * @return The function currently being used for the X axis
     */
    public ParametricFunction getXAxisFunction() {
        return this.xAxisFunc;
    }

    /**
     * Gets the function currently being used for the Y axis
     * @return The function currently being used for the Y axis
     */
    public ParametricFunction getYAxisFunction() {
        return this.yAxisFunc;
    }

    public void useAxisFunctions(ParametricFunction x, ParametricFunction y) {
        if(!(x.equals(this.xAxisFunc) && y.equals(this.yAxisFunc))) {
            this.xAxisFunc = x;
            this.yAxisFunc = y;
            this.notifyObservers();
        }

    }

    /**
     * Creates a Graph using the given functions as axes
     * @return A new Graph object using the given axes, displaying the appropriate data.
     */
    public Graph createGraph() {
        //TODO add error check here
        Graph graph = new BasicGraph();
        for(GraphableData data : this.getDisplayedData()) {
            data.useAxes(this.xAxisFunc, this.yAxisFunc);
            graph.addData(data);
        }
        graph.setXAxisDescriptor(this.xAxisFunc.getDescriptor());
        graph.setYAxisDescriptor(this.yAxisFunc.getDescriptor());
        return graph;
    }

}
