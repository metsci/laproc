package com.metsci.laproc.ActionHandlers;

import com.metsci.laproc.application.DataReference;
import com.metsci.laproc.plotting.BasicGraph;
import com.metsci.laproc.plotting.Graph;
import com.metsci.laproc.plotting.GraphableData;

import com.metsci.laproc.display.DataSetTable;

/**
 * Created by porterjc on 12/16/2016.
 */
public class CreateGraphAction implements Action {
    private DataSetTable table;
    private DataReference reference;

    public CreateGraphAction(DataSetTable table, DataReference ref) {
        this.table = table;
        this.reference = ref;
    }

    public void doAction() {
        Graph graph = new BasicGraph();
        for(GraphableData data : table.getSelectedValues()){
            graph.addData(data);
        }
    }
}
