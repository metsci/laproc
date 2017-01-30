package com.metsci.laproc.action;

import com.metsci.laproc.datareference.OutputDataReference;
import com.metsci.laproc.plotting.CompositeFunction;
import com.metsci.laproc.plotting.Graph;
import com.metsci.laproc.plotting.GraphableData;
import com.metsci.laproc.utils.IAction;

/**
 * This action allows a user to add a composite function to the graph
 * Created by robinsat on 12/22/2016.
 */
public class AddCompositeFunctionAction implements IAction<CompositeFunction> {

    /** Stores a reference to the application's vital data */
    private OutputDataReference dataReference;

    /**
     * Constructor
     * @param reference A reference to all the vital data used by the application
     */
    public AddCompositeFunctionAction(OutputDataReference reference) {
        this.dataReference = reference;
    }

    /**
     * Perform some action for some on click interaction
     * @param compositeFunction The function to add to the graph
     */
    public void doAction(CompositeFunction compositeFunction) {
        Graph graph = dataReference.createGraph();
        for(GraphableData data : graph.getData()) {
            compositeFunction.addData(data);
        }
        GraphableData compositeData = compositeFunction.compute();
        graph.addData(compositeData);
        dataReference.notifyObservers();
    }
}
