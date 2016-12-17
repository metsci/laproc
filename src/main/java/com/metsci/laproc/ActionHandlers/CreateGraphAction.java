package com.metsci.laproc.ActionHandlers;

import com.metsci.laproc.application.DataReference;
import com.metsci.laproc.plotting.BasicGraph;
import com.metsci.laproc.plotting.Graph;
import com.metsci.laproc.plotting.GraphableData;

import com.metsci.laproc.display.DataSetTable;
import com.metsci.laproc.utils.IAction;

/**
 * Created by porterjc on 12/16/2016.
 */
public class CreateGraphAction implements IAction<DataSetTable> {
    private DataReference reference;

    public CreateGraphAction(DataReference ref) {
        this.reference = ref;
    }

    public void doAction(DataSetTable table) {
        Graph graph = new BasicGraph();
        for(GraphableData data : table.getSelectedValues()){
            graph.addData(data);
        }
        reference.newGraph(graph);
    }
}
