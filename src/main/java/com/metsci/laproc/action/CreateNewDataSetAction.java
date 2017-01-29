package com.metsci.laproc.action;

import com.metsci.laproc.datareference.InputDataReference;
import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.datareference.OutputDataReference;
import com.metsci.laproc.tools.EvaluationSetPanel;
import com.metsci.laproc.plotting.GraphableData;
import com.metsci.laproc.plotting.GraphableFunction;
import com.metsci.laproc.plotting.ROCCurve;
import com.metsci.laproc.utils.Filterer;
import com.metsci.laproc.utils.IAction;

import java.util.ArrayList;
import java.util.List;

/**
 * Action that creates a classifier data set
 * Created by porterjc on 12/17/2016.
 */

public class CreateNewDataSetAction implements IAction<EvaluationSetPanel> {
    private InputDataReference inputDataReference;
    private OutputDataReference outputDataReference;
    private int currentAddedIndex = 1;

    /**
     * Default constructor
     * @param inref the input data reference to use for input data
     * @param outref the output data reference to use for output data
     */
    public CreateNewDataSetAction(InputDataReference inref, OutputDataReference outref) {
        inputDataReference = inref;
        outputDataReference = outref;
    }

    //TODO:Issues with doAction needing an Object
    //TODO: This is not the appropriate place to determine what kind of function we need.
    /**
     * Create new classifier data set in the data reference object and the graph object
     * @param dataSheetPanel data sheet panel to get selected tags
     */
    public void doAction(EvaluationSetPanel dataSheetPanel) {
    	String dataName;
    	if(dataSheetPanel.getNameText().equals("") || dataSheetPanel.getNameText().length() < 1){
    		dataName = "New Data Set " + currentAddedIndex++;
    	}else{
    		dataName = dataSheetPanel.getNameText();
    	}
        ClassifierDataSet dataSetGroup = new ClassifierDataSet(new ArrayList<String>(), dataName);
        List<List<String>> tags = dataSheetPanel.getSelectedTags();
        List<ClassifierDataSet> evalSets = inputDataReference.getEvaluationSets();

        Filterer.filterAndUnion(dataSetGroup, tags, evalSets);

        GraphableFunction func = new ROCCurve(dataSetGroup);
        GraphableData output = func.compute();
        output.setName(dataName);

        outputDataReference.addGraphableData(output);
        inputDataReference.addDataSetGroup(dataSetGroup);
        inputDataReference.addToDataSetGraphMap(dataSetGroup,output);
        
        dataSheetPanel.setSelectedDataSet(dataSetGroup);

    }
}
