package com.metsci.laproc.datareference;

import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.plotting.BasicGraph;
import com.metsci.laproc.plotting.Graph;
import com.metsci.laproc.plotting.GraphableData;
import com.metsci.laproc.utils.Observable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This reference allows tools to access the graph and associated data
 * Created by porterjc on 12/14/2016.
 */
public class DataReferenceImpl extends Observable implements DataReference {
    private Graph graph;
    private List<ClassifierDataSet> evalSets;

    /**
     * Constructor
     */
    public DataReferenceImpl() {
        super();
        // Default to an empty graph to prevent Null Pointer exceptions
        graph = new BasicGraph();
        evalSets = new ArrayList<ClassifierDataSet>();
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

}
