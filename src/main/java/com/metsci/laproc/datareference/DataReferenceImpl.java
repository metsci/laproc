package com.metsci.laproc.datareference;

import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.data.TagHeader;
import com.metsci.laproc.plotting.BasicGraph;
import com.metsci.laproc.plotting.Graph;
import com.metsci.laproc.plotting.GraphableData;
import com.metsci.laproc.utils.Observable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * TODO: Add javadocs for additional methods
 * This reference allows tools to access the graph and associated data
 * Created by porterjc on 12/14/2016.
 */
public class DataReferenceImpl extends Observable implements DataReference {
    private Graph graph;
    private List<ClassifierDataSet> evalSets;
    //TODO:using a normal classifier data set to store groups is slow
    private List<ClassifierDataSet> dataSetGroups;
    private HashMap<ClassifierDataSet, GraphableData> dataSetGraphMap;
    private List<TagHeader> tagHeaders;

    /**
     * Constructor
     */
    public DataReferenceImpl(List<TagHeader> tagHeaders) {
        super();
        // Default to an empty graph to prevent Null Pointer exceptions
        graph = new BasicGraph();
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
        this.evalSets.add(dataSet);
        notifyObservers();
    }

    /**
     * Removes a ClassifierDataSet from the collection of Evaluation Sets
     * @param dataSet The set to remove
     */
    public void removeEvalSet(ClassifierDataSet dataSet) {
        this.evalSets.remove(dataSet);
        notifyObservers();
    }

    /**
     * Getter for the Graph object
     * @return The Graph object
     */
    public Graph getGraph() {
        return this.graph;
    }

    /**
     * Gets a list of all evaluation sets
     * @return A list of all evaluation sets
     */
    public List<ClassifierDataSet> getEvaluationSets() {
        return this.evalSets;
    }

	public void addDataToGraph(GraphableData<?> graphSet, boolean display) {
		this.graph.addData(graphSet,display);
		notifyObservers();
	}

    public void setDataDisplayOnGraph(GraphableData<?> graphSet, boolean display) {
        this.graph.setDataDisplay(graphSet,display);
        notifyObservers();
    }

	public void removeDataFromGraph(GraphableData<?> graphSet) {
		this.graph.removeData(graphSet);
		notifyObservers();
	}

    public void replaceDataOnGraph(GraphableData<?> graphSet, GraphableData<?> newGraphSet) {
        this.graph.replaceData(graphSet, newGraphSet);
        notifyObservers();
    }

	public void addDataSetGroup(ClassifierDataSet dataSetGroup){
		this.dataSetGroups.add(dataSetGroup);
		notifyObservers();
	}

	public void addToDataSetGraphMap(ClassifierDataSet dataSetGraph, GraphableData<?> graphSet){
        this.dataSetGraphMap.put(dataSetGraph, graphSet);
        notifyObservers();
    }

    public GraphableData<?> getGraphfromDataSet(ClassifierDataSet dataSetGraph){
        return this.dataSetGraphMap.get(dataSetGraph);
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
