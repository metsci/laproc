package com.metsci.laproc.action;

import com.metsci.laproc.tools.DataReference;
import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.uicomponents.TableDisplayer;
import com.metsci.laproc.plotting.GraphableData;
import com.metsci.laproc.plotting.GraphableFunction;
import com.metsci.laproc.plotting.ROCCurve;
import com.metsci.laproc.utils.IAction;

import javax.swing.*;

/**
 * Created by porterjc on 12/17/2016.
 */
public class CreateNewDataSetAction implements IAction<TableDisplayer> {
    private DataReference reference;
    private int currentAddedIndex = 1;

    /**
     * Basic constructor that takes a data reference object
     * @param ref
     */
    public CreateNewDataSetAction(DataReference ref) {
        reference = ref;
    }

    /**
     * add new evalset to the data reference object and the graph object
     *
     * @param tableDisplayer
     */
    public void doAction(TableDisplayer tableDisplayer) {
        JTable table = tableDisplayer.getTable();
        int[] indexes = table.getSelectedRows();
        ClassifierDataSet data = new ClassifierDataSet();
        for(int i = 0; i < indexes.length; i++){
            data.add(tableDisplayer.getDataPointAtIndex(i));
        }
        GraphableFunction func = new ROCCurve(data);
        GraphableData output = func.compute();
        String dataName = "New Data Set " + currentAddedIndex++;
        output.setName(dataName);
        reference.addEvalSet(data);
        reference.getGraph().addData(output);
        reference.notifyObservers();
    }
}
