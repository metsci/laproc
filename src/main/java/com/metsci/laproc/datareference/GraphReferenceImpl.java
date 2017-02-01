package com.metsci.laproc.datareference;

import com.metsci.laproc.plotting.Graph;
import com.metsci.laproc.utils.Observable;

/**
 * An implementation of GraphReference that allows tools to observe a graph and receive updates
 * Created by robinsat on 1/30/2017.
 */
public class GraphReferenceImpl extends Observable implements GraphReference {

    /** The graph referenced by this object */
    private Graph graph;

    public GraphReferenceImpl(OutputDataReference reference) {
        reference.addObserver(this);
    }

    /**
     * Getter for the Graph object
     * @return The Graph object
     */
    public Graph getGraph() {
        return this.graph;
    }

    /**
     * Method called whenever the observed object updates
     * @param data The observed object
     */
    public void update(OutputDataReference data) {
        this.graph = data.createGraph();
        notifyObservers();
    }
}
