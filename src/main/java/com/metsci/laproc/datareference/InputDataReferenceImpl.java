package com.metsci.laproc.datareference;

import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.data.TagHeader;
import com.metsci.laproc.plotting.GraphableData;
import com.metsci.laproc.utils.Observable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This example reference allows tools to access the raw input data represented as ClassifierDataSets
 * Created by porterjc on 12/14/2016.
 */
public class InputDataReferenceImpl extends Observable implements InputDataReference {
    /** A list of all the raw classifier data sets that comprise the initial program input */
    private List<ClassifierDataSet> evalSets;
    /** A list of all data sets that have been created.
     * These sets contain some subset of the initial data, and correspond to some graph element. */
    private List<ClassifierDataSet> dataSetGroups;
    /** A mapping of data set groups to GraphableFunction output. */
    private HashMap<ClassifierDataSet, GraphableData> dataSetGraphMap;
    /** A list of tag headers in the initial data set */
    private List<TagHeader> tagHeaders;

    /**
     * Constructor for the Default Data Reference
     * @param tagHeaders TagHeaders for the data reference
     */
    public InputDataReferenceImpl(List<TagHeader> tagHeaders) {
        super();
        evalSets = new ArrayList<ClassifierDataSet>();
        dataSetGroups = new ArrayList<ClassifierDataSet>();
        dataSetGraphMap = new HashMap<ClassifierDataSet, GraphableData>();
        this.tagHeaders = tagHeaders;
    }

    /**
     * Adds a ClassifierDataSet to the collection of Evaluation Sets
     * @param dataSet The set to add
     */
    public void addEvalSet(ClassifierDataSet dataSet) {
        if(!this.evalSets.contains(dataSet)){
            this.evalSets.add(dataSet);
            notifyObservers();
        }
    }

    /**
     * Removes a ClassifierDataSet from the collection of Evaluation Sets
     * @param dataSet The set to remove
     */
    public void removeEvalSet(ClassifierDataSet dataSet) {
        if(this.evalSets.contains(dataSet)) {
            this.evalSets.remove(dataSet);
            notifyObservers();
        }
    }

    /**
     * Gets a list of all evaluation sets
     * @return A list of all evaluation sets
     */
    public List<ClassifierDataSet> getEvaluationSets() {
        return this.evalSets;
    }

    /**
     * Add a data set group to the data reference
     * @param dataSetGroup new group to add
     */
	public void addDataSetGroup(ClassifierDataSet dataSetGroup){
        if(!this.dataSetGroups.contains(dataSetGroup)) {
            this.dataSetGroups.add(dataSetGroup);
            notifyObservers();
        }
	}

    /**
     * Remove a data set group from the data reference
     * @param dataSetGroup group to remove
     */
    public void removeDataSetGroup(ClassifierDataSet dataSetGroup){
        if(this.dataSetGroups.remove(dataSetGroup)) {
            this.dataSetGroups.remove(dataSetGroup);
            notifyObservers();
        }
    }

    /**
     * Getter from the data set groups in the data reference
     * @return data set groups
     */
    public List<ClassifierDataSet> getDataSetGroups(){
        return this.dataSetGroups;
    }

    /**
     * Add a data set group/graph set pair to the map that associates the two kinds of sets
     * @param dataSetGroup data set to associate with the graph set
     * @param graphSet graph set to associate with the data set
     */
	public void addToDataSetGraphMap(ClassifierDataSet dataSetGroup, GraphableData<?> graphSet){
        if(!this.dataSetGraphMap.containsKey(dataSetGroup)) {
            this.dataSetGraphMap.put(dataSetGroup, graphSet);
            notifyObservers();
        }
    }

    /**
     * Updates a data set group/graph set pair to the map that associates the two kinds of sets
     * @param dataSetGroup data set to update with a new graph set
     * @param graphSet new graph set to associate with the data set
     */
    public void replaceDataSetGraphMap(ClassifierDataSet dataSetGroup, GraphableData<?> graphSet){
        if(this.dataSetGraphMap.containsKey(dataSetGroup) && !this.dataSetGraphMap.get(dataSetGroup).equals(graphSet)) {
            this.dataSetGraphMap.put(dataSetGroup, graphSet);
            notifyObservers();
        }
    }

    /**
     * Get the associated graph set for a given data set group
     * @param dataSetGroup data set group to get the associate graph for
     * @return associated graph for the data set group
     */
    public GraphableData<?> getGraphFromDataSet(ClassifierDataSet dataSetGroup){
        return this.dataSetGraphMap.get(dataSetGroup);
    }

    /**
     * Getter for the tag headers in the data reference
     * @return tag headers
     */
	public List<TagHeader> getTagHeaders() {
		return this.tagHeaders;
	}

}
