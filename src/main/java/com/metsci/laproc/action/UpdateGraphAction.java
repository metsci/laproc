package com.metsci.laproc.action;

import com.metsci.laproc.datareference.OutputDataReference;
import com.metsci.laproc.pointmetrics.ParametricFunction;
import com.metsci.laproc.utils.IAction;

/**
 * Action to update axis in the graph
 * Created by porterjc on 12/16/2016.
 */
public class UpdateGraphAction implements IAction<ParametricFunction[]> {

    /** An object that stores references to all graphable data sets */
    private OutputDataReference reference;

    /**
     * Basic constructor that takes a data reference object
     * @param reference output date reference for the application
     */
    public UpdateGraphAction(OutputDataReference reference){
        this.reference = reference;
    }

    /**
     * Provides updated axes to the graph object and notifies data reference observers
     * @param argument array of parametric function to use for the graph where index
     *                 index 0 is the X axis and index 1 is the Y axis
     */
    public void doAction(ParametricFunction[] argument) {
        this.reference.updateGraphWithAxes(argument[0], argument[1]);
    }
}
