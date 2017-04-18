package com.metsci.laproc.action;

import com.metsci.laproc.tools.GraphDisplayManager;
import com.metsci.laproc.uicomponents.graphfeatures.GraphFeature;
import com.metsci.laproc.utils.IAction;

/**
 * This action allows a user to remove a feature from the Graph
 * Created by robinsat on 1/31/2017.
 */
public class RemoveGraphFeatureAction implements IAction<GraphFeature> {

    /** Stores a reference to the Graph display manager */
    private GraphDisplayManager displayManager;

    /**
     * Constructor
     * @param displayManager A reference to the Graph's display manager
     */
    public RemoveGraphFeatureAction(GraphDisplayManager displayManager) {
        this.displayManager = displayManager;
    }

    /**
     * Perform some action for some on click interaction
     * @param feature The feature to remove from the Graph
     */
    public void doAction(GraphFeature feature) {
        this.displayManager.disableGraphFeature(feature);
    }
}
