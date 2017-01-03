package com.metsci.laproc.datareference;

import java.util.List;

import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.data.TagHeader;
import com.metsci.laproc.plotting.Graph;
import com.metsci.laproc.plotting.GraphableData;
import com.metsci.laproc.utils.IObservable;

/**
 * TODO: Add javadocs for classifierdatasetgroup stuff
 * This reference allows tools to access the graph and associated data
 * Created by porterjc on 12/14/2016.
 */
public interface DataReference extends IObservable {
    
    /**
     * Getter for the Graph object
     * @return The Graph object
     */
    Graph getGraph();  
    
    /**
     * Adds a ClassifierDataSet to the collection of Evaluation Sets
     * @param dataSet The set to add
     */
    void addEvalSet(ClassifierDataSet dataSet);

    /**
     * Removes a ClassifierDataSet from the collection of Evaluation Sets
     * @param dataSet The set to remove
     */
    void removeEvalSet(ClassifierDataSet dataSet);
    
    /**
     * Gets a list of all evaluation sets
     * @return A list of all evaluation sets
     */
    List<ClassifierDataSet> getEvaluationSets();

    /**
     * Adds a GraphableData to the graph
     * @param graphSet The set to graph
     * @param display True if the set should be displayed
     */
    void addDataToGraph(GraphableData<?> graphSet, boolean display);

    /**
     * Sets the given data set to be displayed or hidden
     * @param graphSet The set to update
     * @param display True if the set should be displayed
     */
    void setDataDisplayOnGraph(GraphableData<?> graphSet, boolean display);
    
    /**
     * Removes a GraphableData from the graph
     * @param graphSet The set to remove
     */
    void removeDataFromGraph(GraphableData<?> graphSet);

    void replaceDataOnGraph(GraphableData<?> graphSet, GraphableData<?> newGraphSet);
    
	void addDataSetGroup(ClassifierDataSet dataSetGroup);

    void addToDataSetGraphMap(ClassifierDataSet dataSetGraph, GraphableData<?> graphSet);

    GraphableData<?> getGraphfromDataSet(ClassifierDataSet dataSetGraph);
	
	void removeDataSetGroup(ClassifierDataSet dataSetGroup);
	
	List<ClassifierDataSet> getDataSetGroups();
	
	List<TagHeader> getTagHeaders();
	
}
