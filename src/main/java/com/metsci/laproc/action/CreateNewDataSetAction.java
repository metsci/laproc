package com.metsci.laproc.action;

import com.metsci.laproc.datareference.DataReference;
import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.tools.EvaluationSetPanel;
import com.metsci.laproc.uicomponents.TableDisplayer;
import com.metsci.laproc.plotting.GraphableData;
import com.metsci.laproc.plotting.GraphableFunction;
import com.metsci.laproc.plotting.ROCCurve;
import com.metsci.laproc.utils.Filterer;
import com.metsci.laproc.utils.IAction;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

/**
 * Action that creates a classifier data set
 * Created by porterjc on 12/17/2016.
 */
public class CreateNewDataSetAction implements IAction<EvaluationSetPanel> {
    private DataReference reference;
    private int currentAddedIndex = 1;

    /**
     * Default constructor
     * @param ref data reference to affect
     */
    public CreateNewDataSetAction(DataReference ref) {
        reference = ref;
    }

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
        List<ClassifierDataSet> evalSets = reference.getEvaluationSets();

        Filterer.filterAndUnion(dataSetGroup, tags, evalSets);

        GraphableFunction func = new ROCCurve(dataSetGroup);
        GraphableData output = func.compute();
        output.setName(dataName);
        
        reference.addDataToGraph(output,true);
        reference.addDataSetGroup(dataSetGroup);
        reference.addToDataSetGraphMap(dataSetGroup,output);
        
        dataSheetPanel.setSelectedDataSet(dataSetGroup);
    }
}
