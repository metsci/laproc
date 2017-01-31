package com.metsci.laproc.action;

import com.metsci.laproc.datareference.DataReference;
import com.metsci.laproc.pointmetrics.ParametricFunction;
import com.metsci.laproc.utils.IAction;

/**
 * Action to update axis in the graph object
 * Created by porterjc on 12/16/2016.
 */
public class UpdateAxesAction implements IAction<ParametricFunction[]> {
    private DataReference reference;


    /**
     * Basic constructor that takes a data reference object
     * @param reference References
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
        this.reference.useAxisFunctions(argument[0], argument[1]);
    }
}
