package com.metsci.laproc.utils;

import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.data.DataPoint;

import java.util.HashSet;
import java.util.List;

/**
 *
 * Created by malinocr on 1/14/2017.
 */
public class Filterer {
    /**
     * Filter evalsets to include only the sets with the corresponding tags and union them into the update set
     * @param updateSet set to union the filtered eval sets not
     * @param tags tags to filter eval sets
     * @param evalSets eval sets to be filtered
     */
    public static void filterAndUnion(ClassifierDataSet updateSet, List<List<String>> tags, List<ClassifierDataSet> evalSets){
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
        } else {
            for(ClassifierDataSet eval : evalSets){
                for(DataPoint point: eval.getAllPoints()){
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
    }

}
