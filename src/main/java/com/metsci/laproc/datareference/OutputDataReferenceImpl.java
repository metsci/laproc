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

        this.allData = new LinkedHashMap<GraphableData, Boolean>();

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
        graphUpdated();
        this.notifyObservers();
    }

    public void deleteGraphableData(GraphableData<?> data) {
        allData.remove(data);
        graphUpdated();
        notifyObservers();
    }

    public void replaceDataOnGraph(GraphableData<?> graphSet, GraphableData<?> newGraphSet) {
        boolean display = allData.get(graphSet);
        allData.remove(graphSet);
        allData.put(newGraphSet, display);
        graphUpdated();
        notifyObservers();
    }

    /**
     * Sets the given GraphableData object as displayed or active
     * @param data The data to set as displayed or active
     */
    public void showData(GraphableData data) {
        if(allData.containsKey(data))
            allData.put(data, true);
        graphUpdated();
        notifyObservers();
    }

    /**
     * Sets the given GraphableData object as hidden or inactive
     * @param data The data to set as hidden or inactive
     */
    public void hideData(GraphableData data) {
        if(allData.containsKey(data))
            allData.put(data, false);
        graphUpdated();
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

    public void useAxisFunctions(ParametricFunction x, ParametricFunction y) {
        this.graph.useAxisFunctions(x, y);
        this.notifyObservers();

    }

    public void graphUpdated() {
        BasicGraph graph = new BasicGraph();
        for(GraphableData data : this.getAllData()) {
            data.useAxes(this.xAxisFunc, yAxisFunc);
            graph.addData(data);
        }
        this.graph = graph;
    }


    /**
     * Creates a Graph using the given functions as axes
     * @return A new Graph object using the given axes, displaying the appropriate data.
     */
    public Graph createGraph() {
        //TODO add error check here
        Graph graph = new BasicGraph();
        for(GraphableData data : this.getDisplayedData()) {
            data.useAxes(this.xAxisFunc, yAxisFunc);
            graph.addData(data);
        }
        return graph;
    }

}
