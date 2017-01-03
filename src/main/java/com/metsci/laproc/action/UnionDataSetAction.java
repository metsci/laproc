package com.metsci.laproc.action;

import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.data.DataPoint;
import com.metsci.laproc.datareference.DataReference;
import com.metsci.laproc.pointmetrics.ParametricFunction;
import com.metsci.laproc.tools.DataSheetPanel;
import com.metsci.laproc.utils.IAction;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * Created by malinocr on 1/3/2017.
 */
public class UnionDataSetAction implements IAction<DataSheetPanel> {
    private DataReference reference;


    /**
     * Basic constructor that takes a data reference object
     * @param reference References
     */
    public UnionDataSetAction(DataReference reference){
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

        if(!tags.isEmpty()){
            HashSet<ClassifierDataSet> initialSets = new HashSet<ClassifierDataSet>();
            int startingIndex = 0;
            while(tags.get(startingIndex).isEmpty() && startingIndex < tags.size()){
                startingIndex++;
            }
            for(String tag : tags.get(startingIndex)){
                for(ClassifierDataSet set: evalSets){
                    if(set.getTags().contains(tag)){
                        initialSets.add(set);
                    }
                }
            }
            for(int i = startingIndex + 1; i < tags.size(); i++){
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
            for(ClassifierDataSet set: initialSets){
                for(DataPoint point: set.getAllPoints()){
                    updateSet.add(point);
                }
            }
        }
        this.reference.notifyObservers();
        //TODO: Is this bad?
    }
}

