package com.metsci.laproc.action;

import com.metsci.laproc.datareference.DataReference;
import com.metsci.laproc.plotting.GraphableData;
import com.metsci.laproc.utils.IAction;

/**
 * Action to hide a dataset on the graph
 * Created by malinocr on 12/21/2016.
 */
public class HideGraphDataAction implements IAction<GraphableData<?>> {
    private DataReference reference;

    /**
     * Default construction
     * @param ref data reference to affect
     */
    public HideGraphDataAction(DataReference ref){
        this.reference = ref;
    }

    /**
     * Set data display on graph
     * @param data to set the display
     */
    public void doAction(GraphableData<?> data) {
        this.reference.setDataDisplayOnGraph(data,false);
    }
}
