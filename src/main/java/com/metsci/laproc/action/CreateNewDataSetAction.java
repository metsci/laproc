package com.metsci.laproc.action;

import com.metsci.laproc.datareference.DataReference;
import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.uicomponents.TableDisplayer;
import com.metsci.laproc.plotting.GraphableData;
import com.metsci.laproc.plotting.GraphableFunction;
import com.metsci.laproc.plotting.ROCCurve;
import com.metsci.laproc.utils.IAction;

import java.util.ArrayList;

import javax.swing.*;

/**
 * TODO: update javadocs
 * Created by porterjc on 12/17/2016.
 */
public class CreateNewDataSetAction implements IAction {
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
    public void doAction(Object o) {
    	String dataName = "New Data Set " + currentAddedIndex++;
        ClassifierDataSet dataSetGroup = new ClassifierDataSet(new ArrayList<String>(), dataName);
        GraphableFunction func = new ROCCurve(dataSetGroup);
        GraphableData output = func.compute();
        output.setName(dataName);
        reference.addGraphSet(output);
        reference.addDataToGraph(output);
        reference.addDataSetGroup(dataSetGroup);
    }
}
