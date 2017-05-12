package com.metsci.laproc.action;

import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.datareference.InputDataReference;
import com.metsci.laproc.datareference.OutputDataReference;
import com.metsci.laproc.plotting.GraphableData;
import com.metsci.laproc.plotting.GraphableFunction;
import com.metsci.laproc.tools.EvaluationSetPanel;
import com.metsci.laproc.utils.Filterer;
import com.metsci.laproc.utils.IAction;

import java.util.List;

/**
 * Action to union the selected evaluation sets into the selected classifier sets in a data sheet panel
 * Created by malinocr on 1/3/2017.
 */
public class FilterDataSetAction implements IAction<EvaluationSetPanel> {
    private InputDataReference inputDataReference;
    private OutputDataReference outputDataReference;
    private GraphableFunction<ClassifierDataSet> graphableFunction;


    /**
     * Basic constructor that takes data reference objects
     * @param inref input data reference for the application
     * @param outref output data reference for the application
     */
    public FilterDataSetAction(InputDataReference inref, OutputDataReference outref,
                               GraphableFunction<ClassifierDataSet> graphFunc){
        this.inputDataReference = inref;
        this.outputDataReference = outref;
        this.graphableFunction = graphFunc;
    }

    /**
     * Union all selected evaluation set into the selected classifier set in the datasheet panel
     * @param dataSheetPanel panel used to determine selected evaluation sets and classifier sets
     */
    public void doAction(EvaluationSetPanel dataSheetPanel) {
        //Find the selected classifier data set, evaluation sets, and tags to filter
        ClassifierDataSet updateSet = dataSheetPanel.getSelectedDataSet();
        List<List<String>> tags = dataSheetPanel.getSelectedTags();
        List<ClassifierDataSet> evalSets = inputDataReference.getEvaluationSets();

        //Filter the selected values
        Filterer.filterAndUnion(updateSet,tags,evalSets);

        //Update data references to include the new classifier data set
        GraphableData<?> oldGraph = this.inputDataReference.getGraphFromDataSet(updateSet);

        GraphableData output = graphableFunction.compute(updateSet);
        output.setName(updateSet.getName());
        this.outputDataReference.replaceDataOnGraph(oldGraph, output);
        this.inputDataReference.replaceDataSetGraphMap(updateSet, output);
    }
}