package com.metsci.laproc.datareference;

import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.data.TagHeader;
import com.metsci.laproc.plotting.BasicGraph;
import com.metsci.laproc.plotting.Graph;
import com.metsci.laproc.plotting.GraphableData;
import com.metsci.laproc.utils.Observable;

import java.util.ArrayList;
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
    private List<GraphableData<?>> graphSets;
    private List<ClassifierDataSet> dataSetGroups;
    private List<TagHeader> tagHeaders;

    /**
     * Constructor
     */
    public DataReferenceImpl(List<TagHeader> tagHeaders) {
        super();
        // Default to an empty graph to prevent Null Pointer exceptions
        graph = new BasicGraph();
        evalSets = new ArrayList<ClassifierDataSet>();
        graphSets = new ArrayList<GraphableData<?>>();
        dataSetGroups = new ArrayList<ClassifierDataSet>();
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
    
    /**
     * Gets a list of all graph sets
     * @return A list of all graph sets
     */
    public List<GraphableData<?>> getGraphSets() {
        return this.graphSets;
    }
    
    /**
     * Adds a GraphableData to the collection of Graph Sets
     * @param graphSet The set to add
     */
    public void addGraphSet(GraphableData<?> graphSet) {
        this.graphSets.add(graphSet);
        notifyObservers();
    }

    /**
     * Removes a GraphableData from the collection of Graph Sets
     * @param graphSet The set to remove
     */
    public void removeGraphSet(GraphableData<?> graphSet) {
        this.graphSets.remove(graphSet);
        notifyObservers();
    }

	public void addDataToGraph(GraphableData<?> graphSet) {
		this.graph.addData(graphSet);
		notifyObservers();
	}

	public void removeDataFromGraph(GraphableData<?> graphSet) {
		this.graph.removeData(graphSet);
		notifyObservers();
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
