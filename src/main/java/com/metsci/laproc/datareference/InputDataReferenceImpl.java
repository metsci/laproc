package com.metsci.laproc.datareference;

import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.data.TagHeader;
import com.metsci.laproc.plotting.*;
import com.metsci.laproc.utils.Observable;

import java.util.ArrayList;
import java.util.List;

/**
 * This reference allows tools to access the graph and associated data
 * Created by porterjc on 12/14/2016.
 */
public class InputDataReferenceImpl extends Observable implements InputDataReference {
    private List<ClassifierDataSet> evalSets;
    //TODO:using a normal classifier data set to store groups is slow
    private List<ClassifierDataSet> dataSetGroups;
    private List<TagHeader> tagHeaders;

    /**
     * Constructor for the Default Data Reference
     * @param tagHeaders TagHeaders for the data reference
     */
    public InputDataReferenceImpl(List<TagHeader> tagHeaders) {
        super();
        evalSets = new ArrayList<ClassifierDataSet>();
        dataSetGroups = new ArrayList<ClassifierDataSet>();
        this.tagHeaders = tagHeaders;
    }

    public void addEvalSet(ClassifierDataSet dataSet) {
        this.evalSets.add(dataSet);
        notifyObservers();
    }

    public void removeEvalSet(ClassifierDataSet dataSet) {
        this.evalSets.remove(dataSet);
        notifyObservers();
    }

    public List<ClassifierDataSet> getEvaluationSets() {
        return this.evalSets;
    }

	public void addDataSetGroup(ClassifierDataSet dataSetGroup){
		this.dataSetGroups.add(dataSetGroup);
        notifyObservers();
	}

	public void removeDataSetGroup(ClassifierDataSet dataSetGroup){
		this.dataSetGroups.remove(dataSetGroup);
        notifyObservers();
	}

	public List<ClassifierDataSet> getDataSetGroups(){
		return this.dataSetGroups;
	}

	public List<TagHeader> getTagHeaders() {
		return this.tagHeaders;
	}



}
