package com.metsci.laproc.action;

import com.metsci.laproc.datareference.OutputDataReference;
import com.metsci.laproc.plotting.GraphableData;
import com.metsci.laproc.utils.IAction;

/**
 * Action to display a graphable data object on the graph
 * Created by malinocr on 12/21/2016.
 */
public class DisplayGraphDataAction implements IAction<GraphableData<?>> {
    private OutputDataReference reference;

    /**
     * Default construction
     * @param ref output data reference for the application
     */
    public DisplayGraphDataAction(OutputDataReference ref){
        this.reference = ref;
    }

    /**
     * Set graphable data to display on graph
     * @param data data to display on the graph
     */
    public void doAction(GraphableData<?> data) {
        this.reference.showData(data);
    }
}
