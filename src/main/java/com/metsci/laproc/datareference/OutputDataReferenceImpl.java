package com.metsci.laproc.datareference;

import com.metsci.laproc.plotting.BasicGraph;
import com.metsci.laproc.plotting.Graph;
import com.metsci.laproc.plotting.GraphableData;
import com.metsci.laproc.pointmetrics.FalsePositiveRate;
import com.metsci.laproc.pointmetrics.ParametricFunction;
import com.metsci.laproc.pointmetrics.TruePositiveRate;
import com.metsci.laproc.utils.Observable;

import java.util.HashMap;
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

    public void addDataToGraph(GraphableData<?> graphSet, boolean display) {
        this.graph.addData(graphSet,display);
        notifyObservers();
    }

    public void setDataDisplayOnGraph(GraphableData<?> graphSet, boolean display) {
        this.graph.setDataDisplay(graphSet,display);
        notifyObservers();
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
}
