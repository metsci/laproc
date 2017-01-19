package com.metsci.laproc.action;

import com.metsci.laproc.datareference.DataReference;
import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.plotting.GraphableData;
import com.metsci.laproc.plotting.GraphableFunction;
import com.metsci.laproc.plotting.ROCCurve;
import com.metsci.laproc.utils.IAction;

import java.util.ArrayList;

/**
 * Action that creates a classifier data set
 * Created by porterjc on 12/17/2016.
 */
public class CreateNewDataSetAction implements IAction<Object> {
    private DataReference reference;
    private int currentAddedIndex = 1;

    /**
     * Default constructor
     * @param ref data reference to affect
     */
    public CreateNewDataSetAction(DataReference ref) {
        reference = ref;
    }

    //TODO:Issues with doAction needing an Object
    /**
     * Create new classifier data set in the data reference object and the graph object
     *
     * @param o No object needed
     */
    public void doAction(Object o) {
        String dataName = "New Data Set " + currentAddedIndex++;
        ClassifierDataSet dataSetGroup = new ClassifierDataSet(new ArrayList<String>(), dataName);
        GraphableFunction func = new ROCCurve(dataSetGroup);
        GraphableData output = func.compute();
        output.setName(dataName);
        reference.addDataToGraph(output,true);
        reference.addDataSetGroup(dataSetGroup);
        reference.addToDataSetGraphMap(dataSetGroup,output);
    }
}
