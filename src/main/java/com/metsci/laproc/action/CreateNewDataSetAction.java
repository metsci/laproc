package com.metsci.laproc.action;

import com.metsci.laproc.datareference.InputDataReference;
import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.datareference.OutputDataReference;
import com.metsci.laproc.tools.EvaluationSetPanel;
import com.metsci.laproc.plotting.GraphableData;
import com.metsci.laproc.plotting.GraphableFunction;
import com.metsci.laproc.plotting.ROCCurveFunction;
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
    private GraphableFunction graphableFunction;
    private int currentAddedIndex = 1;

    /**
     * Default constructor
     * @param inref the input data reference to use for input data
     * @param outref the output data reference to use for output data
     * @param graphFunc the graphable function to create graphable data
     */
    public CreateNewDataSetAction(InputDataReference inref, OutputDataReference outref, GraphableFunction graphFunc) {
        inputDataReference = inref;
        outputDataReference = outref;
        graphableFunction = graphFunc;
    }

    /**
     * Create new classifier data set in the data reference object and the graph object
     * @param evaluationSetPanel data sheet panel to get selected tags
     */
    public void doAction(EvaluationSetPanel evaluationSetPanel) {
    	String dataName = evaluationSetPanel.getNameText();
    	if(dataName.length() < 1){
    		dataName = "New Data Set " + currentAddedIndex++;
    	}
        ClassifierDataSet dataSetGroup = new ClassifierDataSet(new ArrayList<List<String>>(), dataName);
        List<List<String>> tags = evaluationSetPanel.getSelectedTags();
        List<ClassifierDataSet> evalSets = inputDataReference.getEvaluationSets();

        Filterer.filterAndUnion(dataSetGroup, tags, evalSets);

        GraphableData output = graphableFunction.compute(dataSetGroup);
        output.setName(dataName);

        outputDataReference.addGraphableData(output);
        inputDataReference.addDataSetGroup(dataSetGroup);
        inputDataReference.addToDataSetGraphMap(dataSetGroup,output);
        
        evaluationSetPanel.setSelectedDataSet(dataSetGroup);

    }
}
