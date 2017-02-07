package com.metsci.laproc.action;

import com.metsci.laproc.tools.GraphDisplayManager;
import com.metsci.laproc.uicomponents.graphfeatures.GraphFeature;
import com.metsci.laproc.utils.IAction;

/**
 * This action allows a user to add a feature to the graph
 * Created by robinsat on 12/22/2016.
 */
public class AddGraphFeatureAction implements IAction<GraphFeature> {

    /** Stores a reference to the Graph display manager */
    private GraphDisplayManager displayManager;

    /**
     * Constructor
     * @param displayManager A reference to the Graph's display manager
     */
    public AddGraphFeatureAction(GraphDisplayManager displayManager) {
        this.displayManager = displayManager;
    }

    /**
     * Perform some action for some on click interaction
     * @param feature Enables some Graph Feature in the display manager
     */
    public void doAction(GraphFeature feature) {
        this.displayManager.enableGraphFeature(feature);
    }
}
