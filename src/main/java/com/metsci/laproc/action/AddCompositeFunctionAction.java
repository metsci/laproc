package com.metsci.laproc.action;

import com.metsci.laproc.plotting.CompositeFunction;
import com.metsci.laproc.tools.GraphDisplayManager;
import com.metsci.laproc.utils.IAction;

/**
 * This action allows a user to add a composite function to the graph
 * Created by robinsat on 12/22/2016.
 */
public class AddCompositeFunctionAction implements IAction<CompositeFunction> {

    /** Stores a reference to the Graph display manager */
    private GraphDisplayManager displayManager;

    /**
     * Constructor
     * @param displayManager A reference to the Graph's display manager
     */
    public AddCompositeFunctionAction(GraphDisplayManager displayManager) {
        this.displayManager = displayManager;
    }

    /**
     * Perform some action for some on click interaction
     * @param compositeFunction The function to add to the graph
     */
    public void doAction(CompositeFunction compositeFunction) {
        this.displayManager.enableCompositeFunction(compositeFunction);
    }
}
