package com.metsci.laproc.action;

import com.metsci.laproc.tools.DataReference;
import com.metsci.laproc.plotting.BasicGraph;
import com.metsci.laproc.plotting.Graph;
import com.metsci.laproc.plotting.GraphableData;

import com.metsci.laproc.uicomponents.DataSetTable;
import com.metsci.laproc.utils.IAction;

/**
 * Created by porterjc on 12/16/2016.
 */
public class CreateGraphAction implements IAction<DataSetTable> {
    private DataReference reference;

    /**
     * Basic constructor for this action
     * @param ref
     */
    public CreateGraphAction(DataReference ref) {
        this.reference = ref;
    }

    /**
     * Creates a new graph object
     *
     * @param table
     */
    public void doAction(DataSetTable table) {
        Graph graph = new BasicGraph();
        for(GraphableData data : table.getSelectedValues()){
            graph.addData(data);
        }
        reference.newGraph(graph);
    }
}
