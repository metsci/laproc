package com.metsci.laproc.action;

import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.data.DataPoint;
import com.metsci.laproc.datareference.DataReference;
import com.metsci.laproc.plotting.GraphableData;
import com.metsci.laproc.plotting.GraphableFunction;
import com.metsci.laproc.plotting.ROCCurve;
import com.metsci.laproc.pointmetrics.ParametricFunction;
import com.metsci.laproc.tools.DataSheetPanel;
import com.metsci.laproc.utils.IAction;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * Created by malinocr on 1/3/2017.
 */
public class FilterDataSetAction implements IAction<DataSheetPanel> {
    private DataReference reference;


    /**
     * Basic constructor that takes a data reference object
     * @param reference References
     */
    public FilterDataSetAction(DataReference reference){
        this.reference = reference;
    }

    /**
     * Union all the values of the given data sets into the first data set (which is one in the reference)
     * @param dataSheetPanel dataSets to union
     */
    public void doAction(DataSheetPanel dataSheetPanel) {
        ClassifierDataSet updateSet = dataSheetPanel.getSelectedDataSet();
        List<List<String>> tags = dataSheetPanel.getSelectedTags();
        List<ClassifierDataSet> evalSets = reference.getEvaluationSets();
        String setOperation = "";

        if(!tags.isEmpty()){
            HashSet<ClassifierDataSet> initialSets = new HashSet<ClassifierDataSet>();
            int startingIndex = 0;
            while(tags.get(startingIndex).isEmpty() && startingIndex < tags.size()){
                startingIndex++;
            }
            for(String tag : tags.get(startingIndex)){
                if(setOperation.equals("")){
                    setOperation += "( " + tag;
                } else {
                    setOperation += " V " + tag;
                }
                for(ClassifierDataSet set: evalSets){
                    if(set.getTags().contains(tag)){
                        initialSets.add(set);
                    }
                }
            }
            for(int i = startingIndex + 1; i < tags.size(); i++){
                if(!tags.get(i).isEmpty()) {
                    setOperation += " ) Λ ( ";
                    for (String tag : tags.get(i)) {
                        setOperation += tag + " V ";
                    }
                    setOperation = setOperation.substring(0,setOperation.length()-3);
                }

                HashSet<ClassifierDataSet> removeSets = new HashSet<ClassifierDataSet>();
                for(ClassifierDataSet set: initialSets){
                    if(!tags.get(i).isEmpty()) {
                        boolean containsTag = false;
                        for (String tag : tags.get(i)) {
                            if (set.getTags().contains(tag)) {
                                containsTag = true;
                                break;
                            }
                        }
                        if (!containsTag) {
                            removeSets.add(set);
                        }
                    }
                }
                for(ClassifierDataSet set: removeSets){
                    initialSets.remove(set);
                }
            }
            setOperation += " )";
            for(ClassifierDataSet set: initialSets){
                for(DataPoint point: set.getAllPoints()){
                    updateSet.add(point);
                }
            }
        }
        if(!setOperation.equals("")){
            if(updateSet.getSetOperations().equals("")){
                updateSet.setSetOperation(setOperation);
            } else {
                updateSet.setSetOperation("[ " + updateSet.getSetOperations() + " ] V " + setOperation);
            }
        }
        GraphableData<?> oldGraph = this.reference.getGraphfromDataSet(updateSet);
        GraphableFunction func = new ROCCurve(updateSet);
        GraphableData output = func.compute();
        output.setName(updateSet.getName());
        this.reference.replaceDataOnGraph(oldGraph, output);
        this.reference.addToDataSetGraphMap(updateSet, output);
        //TODO: Is this bad?
    }
}

