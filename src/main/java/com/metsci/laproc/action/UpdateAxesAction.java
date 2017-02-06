package com.metsci.laproc.action;

import com.metsci.laproc.datareference.OutputDataReference;
import com.metsci.laproc.pointmetrics.ParametricFunction;
import com.metsci.laproc.utils.IAction;

/**
 * Action to update axis in the graph object
 * Created by porterjc on 12/16/2016.
 */
public class UpdateAxesAction implements IAction<ParametricFunction[]> {
    private OutputDataReference reference;


    /**
     * Basic constructor that takes a data reference object
     * @param reference References
     */
    public UpdateAxesAction(OutputDataReference reference){
        this.reference = reference;
    }

    /**
     * Provides updated axes to the graph object and notifies data reference observers
     *
     * @param argument array of peremetric function to use for the graph where index
     *                 index 0 is the xaxis and index 1 is the yaxis
     */
    public void doAction(ParametricFunction[] argument) {
        this.reference.useAxisFunctions(argument[0], argument[1]);
    }
}
