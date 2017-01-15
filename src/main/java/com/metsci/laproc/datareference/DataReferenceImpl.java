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
public class DataReferenceImpl extends Observable implements DataReference {
    private Graph graph;
    private GraphableDataSet dataSet;
    private List<ClassifierDataSet> evalSets;
    //TODO:using a normal classifier data set to store groups is slow
    private List<ClassifierDataSet> dataSetGroups;
    private List<TagHeader> tagHeaders;

    /**
     * Constructor for the Default Data Reference
     * @param tagHeaders TagHeaders for the data reference
     */
    public DataReferenceImpl(List<TagHeader> tagHeaders) {
        super();
        // Default to an empty graph to prevent Null Pointer exceptions
        graph = new BasicGraph();
        dataSet = new BasicGraphableDataSet();
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

    public Graph getGraph() {
        return this.graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public GraphableDataSet getGraphableDataSet() {
        return this.dataSet;
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
