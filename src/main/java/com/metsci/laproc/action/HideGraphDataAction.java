package com.metsci.laproc.action;

import com.metsci.laproc.datareference.OutputDataReference;
import com.metsci.laproc.plotting.GraphableData;
import com.metsci.laproc.utils.IAction;

/**
 * Action to hide a graphable data set on the graph
 * Created by malinocr on 12/21/2016.
 */
public class HideGraphDataAction implements IAction<GraphableData<?>> {
    private OutputDataReference reference;

    /**
     * Default construction
     * @param ref output data reference for the application
     */
    public HideGraphDataAction(OutputDataReference ref){
        this.reference = ref;
    }

    /**
     * Set the graphable data to hide on the graph
     * @param data graphable data to hide o the graph
     */
    public void doAction(GraphableData<?> data) {
        this.reference.hideData(data);
    }
}
