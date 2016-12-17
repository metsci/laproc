package com.metsci.laproc.ActionHandlers;

import com.metsci.laproc.application.DataReference;
import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.display.TableDisplayer;
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


    public CreateNewDataSetAction(DataReference ref) {
        reference = ref;
    }

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
    }
}
