package com.metsci.laproc.ActionHandlers;

import com.metsci.laproc.application.DataReference;
import com.metsci.laproc.plotting.Graph;
import com.metsci.laproc.pointmetrics.ParametricFunction;
import com.metsci.laproc.utils.IAction;

/**
 * Created by porterjc on 12/16/2016.
 */
public class UpdateAxesAction implements IAction<ParametricFunction[]> {
    private DataReference reference;


    /**
     * Basic constructor that takes a data reference object
     * @param reference
     */
    public UpdateAxesAction(DataReference reference){
        this.reference = reference;
    }

    /**
     * Provides updated axes to the graph object and notifies data reference observers
     *
     * @param argument
     */
    public void doAction(ParametricFunction[] argument) {
        this.reference.getGraph().useAxisFunctions(argument[0], argument[1]);
        this.reference.notifyObservers();
    }
}
