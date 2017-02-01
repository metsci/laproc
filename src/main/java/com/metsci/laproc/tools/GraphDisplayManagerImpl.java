package com.metsci.laproc.tools;

import com.google.common.collect.Lists;
import com.metsci.laproc.datareference.GraphReference;
import com.metsci.laproc.plotting.CompositeFunction;
import com.metsci.laproc.plotting.Graph;
import com.metsci.laproc.plotting.GraphableData;
import com.metsci.laproc.utils.IObserver;
import com.metsci.laproc.utils.Observable;

import java.util.*;

/**
 * Applies composite functions to the Graph
 * Created by robinsat on 1/30/2017.
 */
public class GraphDisplayManagerImpl extends Observable implements GraphDisplayManager {

    /** The set of composite functions that will be applied to the graph */
    private Collection<CompositeFunction> compositeFunctions;
    /** A field to store the current Graph object */
    private Graph graph;

    /**
     * Constructor
     * @param graphReference The GraphReference that this object will observe
     * @param graphDisplay The object observing the graph that will observe this
     */
    public GraphDisplayManagerImpl(GraphReference graphReference, IObserver<GraphReference> graphDisplay) {
        compositeFunctions = new ArrayList<CompositeFunction>();
        graphReference.addObserver(this);
        graphReference.removeObserver(graphDisplay);
        this.addObserver(graphDisplay);
    }

    /**
     * Adds a composite function to the set of functions applied to the graph
     * @param func The function to apply
     */
    public void enableCompositeFunction(CompositeFunction func) {
        this.compositeFunctions.add(func);
    }

    /**
     * Removes a composite function to the set of functions applied to the graph
     * @param func The function to disable
     */
    public void disableCompositeFunction(CompositeFunction func) {
        this.compositeFunctions.remove(func);
    }

    /**
     * Method called whenever the observed object updates
     * @param graphReference The observed object
     */
    public void update(GraphReference graphReference) {
        graph = graphReference.getGraph();
        Iterable<GraphableData> data = graph.getData();
        List<GraphableData> dataList = Lists.newArrayList(data);
        for(CompositeFunction function : this.compositeFunctions) {
            graph.addData(function.compute(dataList));
        }
        notifyObservers();
    }

    /**
     * Getter for the Graph object
     * @return The Graph object
     */
    public Graph getGraph() {
        return this.graph;
    }
}
