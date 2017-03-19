package com.metsci.laproc.action;

import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.datareference.InputDataReference;
import com.metsci.laproc.datareference.OutputDataReference;
import com.metsci.laproc.plotting.GraphableData;
import com.metsci.laproc.plotting.GraphableFunction;
import com.metsci.laproc.plotting.ROCCurveFunction;
import com.metsci.laproc.tools.EvaluationSetPanel;
import com.metsci.laproc.utils.Filterer;
import com.metsci.laproc.utils.IAction;

import java.util.List;

/**
 * Action to union the selected evaluation sets into the selected classifier sets in a datasheet panel
 * Created by malinocr on 1/3/2017.
 */
public class FilterDataSetAction implements IAction<EvaluationSetPanel> {
    private InputDataReference inputDataReference;
    private OutputDataReference outputDataReference;
    private GraphableFunction graphableFunction;


    /**
     * Basic constructor that takes data reference objects
     */
    public FilterDataSetAction(InputDataReference inref, OutputDataReference outref, GraphableFunction graphFunc){
        this.inputDataReference = inref;
        this.outputDataReference = outref;
        this.graphableFunction = graphFunc;
    }

    /**
     * Union all selected evaluation set into the selected classifier set in the datasheet panel
     * @param evaluationSetPanel panel used to determine selected evaluation sets and classifier sets
     */
    public void doAction(EvaluationSetPanel evaluationSetPanel) {
        ClassifierDataSet updateSet = evaluationSetPanel.getSelectedDataSet();
        List<List<String>> tags = evaluationSetPanel.getSelectedTags();

        List<ClassifierDataSet> evalSets = inputDataReference.getEvaluationSets();
        Filterer.filterAndUnion(updateSet,tags,evalSets);
        GraphableData<?> oldGraph = this.inputDataReference.getGraphfromDataSet(updateSet);

        GraphableData output = graphableFunction.compute(updateSet);
        output.setName(updateSet.getName());
        this.outputDataReference.replaceDataOnGraph(oldGraph, output);
        this.inputDataReference.replaceDataSetGraphMap(updateSet, output);
    }
}
