package com.metsci.laproc.utils;

import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.data.DataPoint;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Example class to handle filtering evaluation sets into classifier sets
 *
 * Created by malinocr on 1/14/2017.
 */
public class Filterer {
    /**
     * Filter given evaluation sets to include only the sets with the corresponding tags and union them into the update set.
     * Tags in the same list of tags will be will be unioned together while tags in separate lists will be intersected.
     * Example: List 1 has a, b and list 2 has c. The result will contain (a V b) /\ (c)
     * @param updateSet set to populate with the results of the filter
     * @param tags tags to filter eval sets
     * @param evalSets eval sets to be filtered
     */
    public static void filterAndUnion(ClassifierDataSet updateSet, List<List<String>> tags, List<ClassifierDataSet> evalSets){
        //Set operations will keep track of the set operations performed.
        String setOperation = "";
        if(!tags.isEmpty()) {
            //Only evaluate eval sets that are not currently in the update set.
            List<ClassifierDataSet> newEvalSets = new ArrayList<ClassifierDataSet>();
            for(ClassifierDataSet set: evalSets){
                if(!updateSet.getTags().contains(set.getTags().get(0))){
                    newEvalSets.add(set);
                }
            }
            evalSets = newEvalSets;

            HashSet<ClassifierDataSet> initialSets = new HashSet<ClassifierDataSet>();
            //Find the first set of tags that is not empty.
            int startingIndex = 0;
            while (startingIndex < tags.size() && tags.get(startingIndex).isEmpty()) {
                startingIndex++;
            }

            //If all the sets of tags are empty include add all of the eval sets data points to the update set
            if(startingIndex == tags.size()){
                setOperation = "( all )";
                for(ClassifierDataSet eval : evalSets){
                    for(DataPoint point: eval.getAllPoints()){
                        updateSet.add(point);
                    }
                    updateSet.addTagSet(eval.getTags().get(0));
                }
            } else {
                //If there is a non-empty set of tags, add all of the eval sets that contain any of the tags to
                //the sets considered to be added to the update set.
                for (String tag : tags.get(startingIndex)) {
                    if (setOperation.equals("")) {
                        setOperation += "( " + tag;
                    } else {
                        setOperation += " V " + tag;
                    }
                    for (ClassifierDataSet set : evalSets) {
                        if (set.getTags().get(0).contains(tag)) {
                            initialSets.add(set);
                        }
                    }
                }
                //Remove all sets form initial sets that don't have the tags from other sets of tags
                for (int i = startingIndex + 1; i < tags.size(); i++) {
                    if (!tags.get(i).isEmpty()) {
                        setOperation += " ) /\\ ( ";
                        for (String tag : tags.get(i)) {
                            setOperation += tag + " V ";
                        }
                        setOperation = setOperation.substring(0, setOperation.length() - 3);
                    }

                    HashSet<ClassifierDataSet> removeSets = new HashSet<ClassifierDataSet>();
                    for (ClassifierDataSet set : initialSets) {
                        if (!tags.get(i).isEmpty()) {
                            boolean containsTag = false;
                            for (String tag : tags.get(i)) {
                                if (set.getTags().get(0).contains(tag)) {
                                    containsTag = true;
                                    break;
                                }
                            }
                            if (!containsTag) {
                                removeSets.add(set);
                            }
                        }
                    }
                    for (ClassifierDataSet set : removeSets) {
                        initialSets.remove(set);
                    }
                }
                //Add all points from the initial sets to the update set
                setOperation += " )";
                for (ClassifierDataSet set : initialSets) {
                    for (DataPoint point : set.getAllPoints()) {
                        updateSet.add(point);
                    }
                    updateSet.addTagSet(set.getTags().get(0));
                }
            }
        }
        if(!setOperation.equals("")){
            if(updateSet.getSetOperations().equals("")){
                updateSet.setSetOperation(setOperation);
            } else {
                updateSet.setSetOperation("[ " + updateSet.getSetOperations() + " ] V [ " + setOperation + " ]");
            }
        }
    }

}
