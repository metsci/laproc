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
    private List<ClassifierDataSet> evalSets;
    private List<ClassifierDataSet> dataSetGroups;
    private HashMap<ClassifierDataSet, GraphableData> dataSetGraphMap;
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

    public void addEvalSet(ClassifierDataSet dataSet) {
        if(!this.evalSets.contains(dataSet)){
            this.evalSets.add(dataSet);
            notifyObservers();
        }
    }

    public void removeEvalSet(ClassifierDataSet dataSet) {
        if(this.evalSets.contains(dataSet)) {
            this.evalSets.remove(dataSet);
            notifyObservers();
        }
    }

    public List<ClassifierDataSet> getEvaluationSets() {
        return this.evalSets;
    }


	public void addDataSetGroup(ClassifierDataSet dataSetGroup){
        if(!this.dataSetGroups.contains(dataSetGroup)) {
            this.dataSetGroups.add(dataSetGroup);
            notifyObservers();
        }
	}

    public void removeDataSetGroup(ClassifierDataSet dataSetGroup){
        if(this.dataSetGroups.remove(dataSetGroup)) {
            this.dataSetGroups.remove(dataSetGroup);
            notifyObservers();
        }
    }

    public List<ClassifierDataSet> getDataSetGroups(){
        return this.dataSetGroups;
    }

	public void addToDataSetGraphMap(ClassifierDataSet dataSetGroup, GraphableData<?> graphSet){
        if(!this.dataSetGraphMap.containsKey(dataSetGroup)) {
            this.dataSetGraphMap.put(dataSetGroup, graphSet);
            notifyObservers();
        }
    }

    public void replaceDataSetGraphMap(ClassifierDataSet dataSetGroup, GraphableData<?> graphSet){
        if(this.dataSetGraphMap.containsKey(dataSetGroup) && !this.dataSetGraphMap.get(dataSetGroup).equals(graphSet)) {
            this.dataSetGraphMap.put(dataSetGroup, graphSet);
            notifyObservers();
        }
    }

    public GraphableData<?> getGraphfromDataSet(ClassifierDataSet dataSetGroup){
        return this.dataSetGraphMap.get(dataSetGroup);
    }

	public List<TagHeader> getTagHeaders() {
		return this.tagHeaders;
	}

}
