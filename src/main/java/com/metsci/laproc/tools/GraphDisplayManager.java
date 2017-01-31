package com.metsci.laproc.tools;

import com.metsci.laproc.datareference.GraphReference;
import com.metsci.laproc.plotting.CompositeFunction;
import com.metsci.laproc.plotting.Graph;
import com.metsci.laproc.plotting.GraphableData;
import com.metsci.laproc.utils.IObserver;
import com.metsci.laproc.utils.Observable;

import java.util.Collection;

/**
 * Created by robinsat on 1/30/2017.
 */
public class GraphDisplayManager extends Observable implements IObserver<GraphReference> {

    private Collection<CompositeFunction> compositeFunctions;

    public void enableCompositeFunction(CompositeFunction func) {
        this.compositeFunctions.add(func);
    }

    public void disableCompositeFunction(CompositeFunction func) {
        this.compositeFunctions.remove(func);
    }

    /**
     * Method called whenever the observed object updates
     * @param graphReference The observed object
     */
    public void update(GraphReference graphReference) {
        Graph graph = graphReference.getGraph();
        Iterable<GraphableData> data = graph.getData();
        for(CompositeFunction function : this.compositeFunctions) {
            //TODO finish this
        }
        notifyObservers();
    }
}
