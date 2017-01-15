package com.metsci.laproc.datareference;

import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.data.TagHeader;
import com.metsci.laproc.plotting.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This reference allows tools to access the graph and associated data
 * Created by porterjc on 12/14/2016.
 */
public class DataReferenceImpl implements DataReference {
    private Graph graph;
    private GraphableDataSet dataSet;
    private List<ClassifierDataSet> evalSets;
    //TODO:using a normal classifier data set to store groups is slow
    private List<ClassifierDataSet> dataSetGroups;
    private List<TagHeader> tagHeaders;

    /* ---------- Observers ---------- */
    private List<DataObserver> graphObservers;
    private List<DataObserver> graphableDataSetObservers;
    private List<DataObserver> dataSetGroupObservers;
    private List<DataObserver> evalSetObservers;

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

        graphObservers = new ArrayList<DataObserver>();
        graphableDataSetObservers = new ArrayList<DataObserver>();
        dataSetGroupObservers = new ArrayList<DataObserver>();
        evalSetObservers = new ArrayList<DataObserver>();
    }

    public void addEvalSet(ClassifierDataSet dataSet) {
        this.evalSets.add(dataSet);
        updateEvalSets();
    }

    public void removeEvalSet(ClassifierDataSet dataSet) {
        this.evalSets.remove(dataSet);
        updateEvalSets();
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
		updateDataSetGroups();
	}

	public void removeDataSetGroup(ClassifierDataSet dataSetGroup){
		this.dataSetGroups.remove(dataSetGroup);
		updateDataSetGroups();
	}

	public List<ClassifierDataSet> getDataSetGroups(){
		return this.dataSetGroups;
	}

	public List<TagHeader> getTagHeaders() {
		return this.tagHeaders;
	}

    /**
     * Allows some DataObserver to observe updates to the Graph
     * @param observer The object that will observe updates to the Graph
     */
    public void addGraphObserver(DataObserver observer) {
        this.graphObservers.add(observer);
    }

    /**
     * Allows some DataObserver to observe updates to the GraphableDataSet
     * @param observer The object that will observe updates to the GraphableDataSet
     */
    public void addGraphableDataSetObserver(DataObserver observer) {
        this.graphableDataSetObservers.add(observer);
    }

    /**
     * Allows some DataObserver to observe updates to the DataSetGroup
     * @param observer The object that will observe updates to the DataSetGroup
     */
    public void addDataSetGroupsObserver(DataObserver observer) {
        this.dataSetGroupObservers.add(observer);
    }

    /**
     * Allows some DataObserver to observe updates to the Eval sets
     * @param observer The object that will observe updates to the Eval sets
     */
    public void addEvalSetObserver(DataObserver observer) {
        this.evalSetObservers.add(observer);
    }

    /**
     * Called whenever Graph is updated
     */
    public void updateGraph() {
        notifyObservers(graphObservers);
    }

    /**
     * Called whenever the GraphableDataSet is updated
     */
    public void updateGraphableDataSet() {
        notifyObservers(graphableDataSetObservers);
    }

    /**
     * Called whenever the DataSetGroup is updated
     */
    public void updateDataSetGroups() {
        notifyObservers(dataSetGroupObservers);
    }

    /**
     * Called whenever the eval sets are updated
     */
    public void updateEvalSets() {
        notifyObservers(evalSetObservers);
    }

    /**
     * A helper method to notify the applicable observers when an update occurs
     * @param observers The observers to notify
     */
    private void notifyObservers(List<DataObserver> observers) {
        for(DataObserver obs : observers) {
            obs.update(this);
        }
    }

}
