package com.metsci.laproc.action;

import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.data.DataPoint;
import com.metsci.laproc.datareference.DataReference;
import com.metsci.laproc.plotting.GraphableData;
import com.metsci.laproc.plotting.GraphableFunction;
import com.metsci.laproc.plotting.ROCCurve;
import com.metsci.laproc.tools.EvaluationSetPanel;
import com.metsci.laproc.utils.Filterer;
import com.metsci.laproc.utils.IAction;

import java.util.HashSet;
import java.util.List;

/**
 * Action to union the selected evaluation sets into the selected classifier sets in a datasheet panel
 * Created by malinocr on 1/3/2017.
 */
public class FilterDataSetAction implements IAction<EvaluationSetPanel> {
    private DataReference reference;


    /**
     * Basic constructor that takes a data reference object
     * @param reference References
     */
    public FilterDataSetAction(DataReference reference){
        this.reference = reference;
    }

    /**
     * Union all selected evaluation set into the selected classifier set in the datasheet panel
     * @param dataSheetPanel panel used to determine selected evaluation sets and classifier sets
     */
    public void doAction(EvaluationSetPanel dataSheetPanel) {
        ClassifierDataSet updateSet = dataSheetPanel.getSelectedDataSet();
        List<List<String>> tags = dataSheetPanel.getSelectedTags();
        List<ClassifierDataSet> evalSets = reference.getEvaluationSets();

        Filterer.filterAndUnion(updateSet,tags,evalSets);

        GraphableData<?> oldGraph = this.reference.getGraphfromDataSet(updateSet);
        GraphableFunction func = new ROCCurve(updateSet);
        GraphableData output = func.compute();
        output.setName(updateSet.getName());
        this.reference.replaceDataOnGraph(oldGraph, output);
        this.reference.addToDataSetGraphMap(updateSet, output);
        //TODO: Is this bad?
    }
}

