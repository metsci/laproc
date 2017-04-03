package com.metsci.laproc.action;

import com.metsci.laproc.plotting.CompositeFunction;
import com.metsci.laproc.tools.GraphDisplayManager;
import com.metsci.laproc.utils.IAction;

/**
 * This action allows a user to remove a composite function from the graph
 * Created by robinsat on 1/31/2017.
 */
public class RemoveCompositeFunctionAction implements IAction<CompositeFunction> {

    /** Stores a reference to the Graph display manager */
    private GraphDisplayManager displayManager;

    /**
     * Constructor
     * @param displayManager A reference to the Graph's display manager
     */
    public RemoveCompositeFunctionAction(GraphDisplayManager displayManager) {
        this.displayManager = displayManager;
    }

    /**
     * Remove composite function from the graph
     * @param compositeFunction The function to remove from the graph
     */
    public void doAction(CompositeFunction compositeFunction) {
        this.displayManager.disableCompositeFunction(compositeFunction);
    }
}
